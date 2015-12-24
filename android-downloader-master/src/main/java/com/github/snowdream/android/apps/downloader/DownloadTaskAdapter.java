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

package com.github.snowdream.android.apps.downloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;

public class DownloadTaskAdapter extends BaseAdapter {
    private Context mContext;
    private List<DownloadTask> mItems;

    public DownloadTaskAdapter(Context context, List<DownloadTask> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (mItems != null) {
            count = mItems.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        Object obj = null;
        if (position >= 0 && position < getCount()) {
            obj = mItems.get(position);
        }
        return obj;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Object item = getItem(position);

        if (item == null || !(item instanceof DownloadTask)) {
            Log.e("Error");
            return null;
        }

        DownloadTask task = (DownloadTask) item;


        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(R.layout.list_row_downloadtask, parent, false);
        }

        TextView title = (TextView) v.findViewById(R.id.title);
        TextView size = (TextView) v.findViewById(R.id.size);
        ProgressBar bar = (ProgressBar) v.findViewById(R.id.progress);

        long filesize = 0l;
        File file = new File(task.getPath());
        if (file.exists()) {
            filesize = file.length();
        }

        title.setText(task.getName());
        size.setText(filesize + "/" + task.getSize());

        int progress = 0;
        if (task.getSize() > 0) {
            progress = (int) (filesize * 100 / task.getSize());
        }

        bar.setProgress(progress);

        return v;
    }

    public void destory() {
        if (mContext == null) {
            return;
        }

        DownloadManager downloadManager = new DownloadManager(mContext);
        if (mItems != null) {
            for (DownloadTask task : mItems) {
                downloadManager.stop(task, null);
            }
        }
    }
}
