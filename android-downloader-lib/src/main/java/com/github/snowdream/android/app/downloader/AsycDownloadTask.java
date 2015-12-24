
/*
 * Copyright (C) 2013 Snowdream Mobile <yanghui1986527@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.snowdream.android.app.downloader;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.github.snowdream.android.app.downloader.dao.ISql;
import com.github.snowdream.android.app.downloader.dao.ISqlImpl;
import com.github.snowdream.android.util.Log;
import com.github.snowdream.android.util.concurrent.AsyncTask;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

public class AsycDownloadTask extends AsyncTask<DownloadTask, Integer, DownloadTask> {

    private static final int MESSAGE_POST_ERROR = 0x1;

    private static final int MESSAGE_POST_ADD = 0x2;
    /**
     * http default
     */
    private static final int MODE_DEFAULT = 0x1;
    /**
     * http trunked
     */
    private static final int MODE_TRUNKED = 0x2;
    private static final String STORE_PATH = "/mnt/sdcard/snowdream/android/downloader/";
    private static final InternalHandler sHandler = new InternalHandler();
    /**
     * just get head detail message
     */
    private boolean isOnlyGetHead = false;
    private int mode = MODE_DEFAULT;

    public AsycDownloadTask(DownloadListener<Integer, DownloadTask> listener, boolean isOnlyGetHead) {
        super(listener);
        this.isOnlyGetHead = isOnlyGetHead;
    }

    /**
     * throw error
     *
     * @param task task
     * @param code The code of the exception
     */
    private void OnError(DownloadTask task, Integer code) {
        if (listener != null) {
            listener.onError(new DownloadException(code));
        }
    }

    /**
     * inform Add
     *
     * @param task task
     */
    private void OnAdd(DownloadTask task) {
        if (listener != null && listener instanceof DownloadListener) {
            ((DownloadListener) listener).onAdd(task);
        }
    }

    /**
     * Update the status of the DownloadTask,and save it to the sqlite
     *
     * @param task
     * @param status
     */
    private void SaveDownloadTask(DownloadTask task, int status) {
        Context context = task.getContext();

        if (context == null) {
            return;
        }

        task.setStatus(status);

        ISql iSql = new ISqlImpl(context);

        try {
            iSql.addDownloadTask(task);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void SendError(DownloadTask task, Integer code) {
        Log.e("Errors happen while downloading.");
        SaveDownloadTask(task, DownloadStatus.STATUS_FAILED);

        sHandler.obtainMessage(
                MESSAGE_POST_ERROR,
                new AsyncTaskResult(this, task,
                        code)).sendToTarget();
    }

    private void SendAdd(DownloadTask task) {
        sHandler.obtainMessage(
                MESSAGE_POST_ADD,
                new AsyncTaskResult(this, task,
                        -1)).sendToTarget();
    }

    /**
     * TODO if error occurs,carry it out. if (listener != null) {
     * listener.onError(new Throwable()); }
     */
    protected DownloadTask doInBackground(DownloadTask... tasks) {
        if (tasks.length <= 0) {
            Log.e("There is no DownloadTask.");
            return null;
        }

        DownloadTask task = tasks[0];

        if (task == null || !task.isValid()) {
            SendError(task, DownloadException.DOWNLOAD_TASK_NOT_VALID);

            Log.e("The task is not valid,or the url of the task is not valid.");
            return null;
        }

        String path = task.getPath();
        File file = new File(path);

        InputStream in = null;
        RandomAccessFile out = null;
        HttpURLConnection connection = null;

        try {
            long range = file.length();
            long size = task.getSize();
            long curSize = range;
            String filename = task.getName();
            String contentType = task.getMimeType();

            if (task.getStatus() == DownloadStatus.STATUS_FINISHED && size == range) {
                Log.i("The DownloadTask has already been downloaded.");
                return task;
            }

            String urlString = task.getUrl();
            String cookies = null;
            while (true) {
                URL url = new URL(urlString);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent", "Snowdream Mobile");
                connection.setRequestProperty("Connection", "Keep-Alive");
                if (cookies != null && cookies != "") {
                    connection.setRequestProperty("Cookie", cookies);
                }
                connection.setRequestMethod("GET");

                if (range > 0) {
                    connection.setRequestProperty("Range", "bytes=" + range +
                            "-");
                }

                //http auto redirection
                //see: http://www.mkyong.com/java/java-httpurlconnection-follow-redirect-example/
                boolean redirect = false;
                boolean success = false;

                // normally, 3xx is redirect
                int status = connection.getResponseCode();
                Log.i("HTTP STATUS CODE: " + status);

                switch (status) {
                    case HttpURLConnection.HTTP_OK:
                    case HttpURLConnection.HTTP_PARTIAL:
                        success = true;

                        String transfer_encoding = connection.getHeaderField("Transfer-Encoding");
                        if (!TextUtils.isEmpty(transfer_encoding)
                                && transfer_encoding.equalsIgnoreCase("chunked")) {
                            mode = MODE_TRUNKED;
                            Log.i("HTTP MODE: TRUNKED");
                        } else {
                            mode = MODE_DEFAULT;
                            Log.i("HTTP MODE: DEFAULT");
                        }

                        String accept_ranges = connection.getHeaderField("Accept-Ranges");
                        if (!TextUtils.isEmpty(accept_ranges)
                                && accept_ranges.equalsIgnoreCase("bytes")) {
                            Log.i("Accept-Ranges: bytes");
                        } else {
                            range = 0;
                            Log.i("Accept-Ranges: none");
                        }
                        break;
                    case HttpURLConnection.HTTP_MOVED_TEMP:
                    case HttpURLConnection.HTTP_MOVED_PERM:
                    case HttpURLConnection.HTTP_SEE_OTHER:
                        redirect = true;
                        // get redirect url from "location" header field
                        urlString = connection.getHeaderField("Location");

                        // get the cookie if need, for login
                        cookies = connection.getHeaderField("Set-Cookie");

                        Log.i("Redirect Url : " + urlString);
                        break;
                    default:
                        success = false;
                        break;
                }

                if (!redirect) {
                    if (!success) {
                        SendError(task, DownloadException.DOWNLOAD_TASK_FAILED);

                        Log.e("Http Connection error. ");
                        return null;
                    }
                    Log.i("Successed to establish the http connection.Ready to download...");
                    break;
                }
            }

            if (range == 0) {
                //set the whole file size
                size = connection.getContentLength();
                task.setSize(size);

                if (contentType != connection.getContentType()) {
                    contentType = connection.getContentType();
                    task.setMimeType(contentType);
                }

                //auto get filename
                if (TextUtils.isEmpty(filename)) {
                    String disposition = connection.getHeaderField("Content-Disposition");
                    if (disposition != null) {
                        // extracts file name from header field
                        final String FILENAME = "filename=";
                        final int startIdx = disposition.indexOf(FILENAME);
                        final int endIdx = disposition.indexOf(';', startIdx);
                        filename = disposition.substring(startIdx + FILENAME.length(), endIdx > 0 ? endIdx : disposition.length());
                    } else {
                        // extracts file name from URL
                        filename = urlString.substring(urlString.lastIndexOf("/") + 1,
                                urlString.length());
                    }
                    task.setName(filename);
                }

                //auto get filepath
                if (TextUtils.isEmpty(path)) {
                    path = STORE_PATH + filename;

                    file = new File(path);

                    task.setPath(path);
                }

                task.setStartTime(System.currentTimeMillis());

                SaveDownloadTask(task, task.getStatus());
                Log.i("The Task is stored in the sqlite.");

                if (isOnlyGetHead) {
                    SendAdd(task);
                    return null;
                }
            }

            File dir = file.getParentFile();

            if (!dir.exists() && !dir.mkdirs()) {
                SendError(task, DownloadException.DOWNLOAD_TASK_FAILED);
                Log.e("The directory of the file can not be created!");
                return null;
            }
            task.setStatus(DownloadStatus.STATUS_RUNNING);
            SaveDownloadTask(task, task.getStatus());

            Log.i("DownloadTask " + task);

            out = new RandomAccessFile(file, "rw");
            out.seek(range);

            in = new BufferedInputStream(connection.getInputStream());

            byte[] buffer = new byte[1024];
            int nRead = 0;
            int progress = -1;
            boolean isFinishDownloading = true;
            while ((nRead = in.read(buffer, 0, 1024)) > 0) {
                out.write(buffer, 0, nRead);

                curSize += nRead;

                if (size != 0) {
                    progress = (int) ((curSize * 100) / size);
                }

                publishProgress(progress);

                Log.i("cur size:" + (curSize) + "    total size:" + (size) + "    cur progress:" + (progress));

                if (isCancelled()) {
                    task.setStatus(DownloadStatus.STATUS_STOPPED);
                    isFinishDownloading = false;
                    break;
                }

                if (task.getStatus() != DownloadStatus.STATUS_RUNNING) {
                    isFinishDownloading = false;
                    break;
                }
            }

            if (!isFinishDownloading) {
                Log.w("The DownloadTask has not been completely downloaded.");
                SaveDownloadTask(task, task.getStatus());
                return null;
            }

            //when the mode is MODE_TRUNKED,set the latest size.
            if (size == 0 && curSize != 0) {
                task.setSize(curSize);
            }

            range = file.length();
            size = task.getSize();
            Log.i("range: " + range + " size: " + size);

            if (range != 0 && range == size) {
                Log.i("The DownloadTask has been successfully downloaded.");
                task.setFinishTime(System.currentTimeMillis());
                SaveDownloadTask(task, DownloadStatus.STATUS_FINISHED);
                return task;
            } else {
                Log.i("The DownloadTask failed to downloaded.");
                SendError(task, DownloadException.DOWNLOAD_TASK_FAILED);
                return null;
            }
        } catch (MalformedURLException e) {
            SendError(task, DownloadException.DOWNLOAD_TASK_FAILED);

            e.printStackTrace();
        } catch (ProtocolException e) {
            SendError(task, DownloadException.DOWNLOAD_TASK_FAILED);

            e.printStackTrace();
        } catch (FileNotFoundException e) {
            SendError(task, DownloadException.DOWNLOAD_TASK_FAILED);

            e.printStackTrace();
        } catch (IOException e) {
            SendError(task, DownloadException.DOWNLOAD_TASK_FAILED);

            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    private static class AsyncTaskResult {
        final Integer mData;
        final DownloadTask mDownloadTask;
        @SuppressWarnings("rawtypes")
        final AsyncTask mTask;

        AsyncTaskResult(@SuppressWarnings("rawtypes")
                        AsyncTask task, DownloadTask downloadtask, Integer data) {
            mTask = task;
            mData = data;
            mDownloadTask = downloadtask;
        }
    }

    private static class InternalHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            AsyncTaskResult result = (AsyncTaskResult) msg.obj;

            if (result == null || result.mTask == null || result.mTask.isCancelled()) {
                Log.i("The asyncTask is not valid or cancelled!");
                return;
            }

            switch (msg.what) {
                case MESSAGE_POST_ERROR:
                    ((AsycDownloadTask) result.mTask).OnError(result.mDownloadTask, result.mData);
                    break;
                case MESSAGE_POST_ADD:
                    ((AsycDownloadTask) result.mTask).OnAdd(result.mDownloadTask);
                    break;
                default:
                    break;
            }
        }
    }
}
