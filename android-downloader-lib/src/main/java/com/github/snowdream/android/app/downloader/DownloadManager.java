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

import android.app.Activity;
import android.content.Context;
import com.github.snowdream.android.app.downloader.dao.ISql;
import com.github.snowdream.android.app.downloader.dao.ISqlImpl;
import com.github.snowdream.android.util.Log;

import java.io.File;
import java.sql.SQLException;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @version v1.0
 * @date Sep 29, 2013
 */
public class DownloadManager {
    Context context = null;

    private DownloadManager() {
    }

    public DownloadManager(Context context) {
        this.context = context;
    }

    /**
     * deal with the result
     *
     * @param message  POST_MESSAGE
     * @param listener DownloadListener
     * @param code     code
     */
    @SuppressWarnings("rawtypes")
    private void OnResult(final POST_MESSAGE message, final DownloadTask task, final DownloadListener listener, final Integer code) {
        if (context == null || !(context instanceof Activity)) {
            Log.w("The context is null or invalid!");
            return;
        }

        ((Activity) context).runOnUiThread(new Runnable() {

            public void run() {
                if (listener == null) {
                    return;
                }

                switch (message) {
                    case ADD:
                        listener.onAdd(task);
                        break;
                    case DELETE:
                        listener.onDelete(task);
                        break;
                    case START:
                        listener.onStart();
                        break;
                    case FINISH:
                        listener.onFinish();
                        break;
                    case STOP:
                        listener.onStop(task);
                        break;
                    case ERROR:
                        listener.onError(new DownloadException(code));
                        break;
                }
            }
        });
    }

    /**
     * Add Task
     *
     * @param task DownloadTask
     * @return
     */
    public void add(DownloadTask task, DownloadListener listener) {
        Log.i("Add Task");

        if (task == null || !task.isValid()) {
            OnResult(POST_MESSAGE.ERROR, task, listener, DownloadException.DOWNLOAD_TASK_NOT_VALID);
            return;
        }

        if (task.getContext() == null) {
            task.setContext(context);
        }

        ISql iSql = new ISqlImpl(context);
        DownloadTask temptask = null;

        try {
            temptask = iSql.queryDownloadTask(task);

            if (temptask == null || !temptask.isValid() || !temptask.isComplete()) {
                if (task.isComplete()) {
                    iSql.addDownloadTask(task);
                    OnResult(POST_MESSAGE.ADD, task, listener, -1);
                    Log.i("The Task is stored in the sqlite.");
                } else {
                    task.start(context, listener, true);
                }
            } else {
                task.setDownloadTask(temptask);
                OnResult(POST_MESSAGE.ADD, task, listener, -1);
                Log.i("The Task is already stored in the sqlite.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Task <BR />
     * just set the task status to DownloadStatus.STATUS_DELETED
     *
     * @param task DownloadTask
     * @return
     */
    public void delete(DownloadTask task, DownloadListener listener) {
        Log.i("Delete Task");

        if (task == null || !task.isValid()) {
            OnResult(POST_MESSAGE.ERROR, task, listener, DownloadException.DOWNLOAD_TASK_NOT_VALID);
            return;
        }

        if (task.getContext() == null) {
            task.setContext(context);
        }

        switch (task.getStatus()) {
            case DownloadStatus.STATUS_DELETED:
                OnResult(POST_MESSAGE.DELETE, task, listener, -1);
                Log.i("The Task is already Deleted.");
                break;
            default:
                task.delete();
                OnResult(POST_MESSAGE.DELETE, task, listener, -1);
                break;
        }
    }

    /**
     * Delete Task <BR />
     * delete the task ,and the file of the task too.
     *
     * @param task DownloadTask
     * @return
     */
    public void deleteforever(DownloadTask task, DownloadListener listener) {
        Log.i("Delete Task forever");

        if (task == null || !task.isValid()) {
            OnResult(POST_MESSAGE.ERROR, task, listener, DownloadException.DOWNLOAD_TASK_NOT_VALID);
            return;
        }

        if (task.getContext() == null) {
            task.setContext(context);
        }

        ISql iSql = new ISqlImpl(context);
        try {
            iSql.deleteDownloadTask(task);
            File file = new File(task.getPath());

            if (file.exists()) {
                file.delete();
                OnResult(POST_MESSAGE.DELETE, task, listener, -1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Start Task
     *
     * @param task
     * @param listener
     * @return
     */
    @SuppressWarnings("rawtypes")
    public boolean start(DownloadTask task, DownloadListener listener) {
        Log.i("Start Task");

        boolean ret = false;

        if (task == null) {
            OnResult(POST_MESSAGE.ERROR, task, listener, DownloadException.DOWNLOAD_TASK_NOT_VALID);
            return ret;
        }

        if (task.getContext() == null) {
            task.setContext(context);
        }

        ISql iSql = new ISqlImpl(context);

        DownloadTask temptask = null;

        try {
            temptask = iSql.queryDownloadTask(task);

            if (temptask == null) {
                add(task, listener);
            } else if (!temptask.equals(task)) {
                task.setDownloadTask(temptask);
            }

            switch (task.getStatus()) {
                case DownloadStatus.STATUS_RUNNING:
                    OnResult(POST_MESSAGE.START, task, listener, -1);
                    OnResult(POST_MESSAGE.FINISH, task, listener, -1);
                    Log.i("The Task is already Running.");
                    break;
                default:
                    if (listener != null) {
                        task.start(context, listener, false);
                    }
                    break;
            }

            ret = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    /**
     * Stop Task<BR />
     * if the task status is not
     * DownloadStatus.STATUS_PAUSED,DownloadStatus.STATUS_STOPPED or
     * DownloadStatus.STATUS_RUNNING, then
     * exceptions(DownloadException.OPERATION_NOT_VALID) will be thrown.
     *
     * @param task DownloadTask
     * @return
     */
    public void stop(DownloadTask task, DownloadListener listener) {
        Log.i("Stop Task");

        if (task == null) {
            OnResult(POST_MESSAGE.ERROR, task, listener, DownloadException.DOWNLOAD_TASK_NOT_VALID);
            return;
        }

        if (task.getContext() == null) {
            task.setContext(context);
        }

        switch (task.getStatus()) {
            case DownloadStatus.STATUS_STOPPED:
                OnResult(POST_MESSAGE.STOP, task, listener, -1);
                Log.i("The Task is already Stopped.");
                break;
            case DownloadStatus.STATUS_RUNNING:
                task.setStatus(DownloadStatus.STATUS_STOPPED);

                ISql iSql = new ISqlImpl(context);
                try {
                    iSql.updateDownloadTask(task);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                task.stop();
                OnResult(POST_MESSAGE.STOP, task, listener, -1);
                break;
            default:
                OnResult(POST_MESSAGE.ERROR, task, listener, DownloadException.DOWNLOAD_TASK_NOT_VALID);
                break;
        }
    }

    private enum POST_MESSAGE {
        ADD, DELETE, ERROR, START, STOP, FINISH
    }
}
