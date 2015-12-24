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

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.github.snowdream.android.app.downloader.DownloadListener;
import com.github.snowdream.android.app.downloader.DownloadManager;
import com.github.snowdream.android.app.downloader.DownloadStatus;
import com.github.snowdream.android.app.downloader.DownloadTask;
import com.github.snowdream.android.util.Log;
import net.simonvt.menudrawer.MenuDrawer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @version v1.0
 * @date Sep 29, 2013
 */
//@EActivity(R.layout.activity_main)
public class MainActivity extends ListActivity implements MenuAdapter.MenuListener {
    private MenuDrawer mDrawer;
    private MenuAdapter mAdapter;
    private ListView mList;
    private int mActivePosition = 0;
    private DownloadManager downloadManager = null;
    private List<DownloadTask> list = null;
    private DownloadTaskAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Log.setPath("/mnt/sdcard/snowdream/log", "log", "log");
        // Log.setPolicy(Log.LOG_ERROR_TO_FILE);
        Log.setPolicy(Log.LOG_ALL_TO_FILE);

        //setContentView(R.layout.activity_main);
        mDrawer = MenuDrawer.attach(this);
        mDrawer.setSlideDrawable(R.drawable.ic_drawer);
        mDrawer.setMenuSize(250);


        mDrawer.setDrawerIndicatorEnabled(true);

        List<Object> items = new ArrayList<Object>();
        items.add(new MenuItem("All", -1));
        items.add(new MenuItem("Downloading", -1));
        items.add(new MenuItem("Finished", -1));
        items.add(new MenuItem("Trash", -1));

        mList = new ListView(this);
        mAdapter = new MenuAdapter(this, items);
        mAdapter.setListener(this);
        mAdapter.setActivePosition(mActivePosition);

        mList.setAdapter(mAdapter);
        mDrawer.setMenuView(mList);
        mList.setOnItemClickListener(mItemClickListener);

        TextView content = new TextView(this);
        content.setText("This is a sample of an overlayed left drawer.");
        content.setGravity(Gravity.CENTER);
        mDrawer.setContentView(content);
        mDrawer.setSlideDrawable(R.drawable.ic_drawer);
        mDrawer.setDrawerIndicatorEnabled(true);

        List<String> items1;
        items1 = new ArrayList<String>();
        for (int i = 1; i <= 20; i++) {
            items1.add("MenuItem " + i);
        }

        downloadManager = new DownloadManager(this);
        list = new ArrayList<DownloadTask>();

        adapter = new DownloadTaskAdapter(this, list);
        setListAdapter(adapter);

        DownloadTask task = new DownloadTask(this);
        task.setUrl("https://raw.githubusercontent.com/snowdream/android-autoupdater/master/docs/test/android-autoupdater-v2.0-release.apk");
        downloadManager.add(task, listener);
    }

    @Override
    protected void onDestroy() {
        adapter.destory();
        super.onDestroy();
    }

    private DownloadListener listener = new DownloadListener<Integer, DownloadTask>() {
        /**
         * The download task has been added to the sqlite.
         * <p/>
         * operation of UI allowed.
         *
         * @param downloadTask the download task which has been added to the sqlite.
         */
        @Override
        public void onAdd(DownloadTask downloadTask) {
            super.onAdd(downloadTask);
            Log.i("onAdd()");
            list.add(downloadTask);
            Log.i(""+downloadTask);
            adapter.notifyDataSetChanged();
        }

        /**
         * The download task has been delete from the sqlite
         * <p/>
         * operation of UI allowed.
         *
         * @param downloadTask the download task which has been deleted to the sqlite.
         */
        @Override
        public void onDelete(DownloadTask downloadTask) {
            super.onDelete(downloadTask);
            Log.i("onDelete()");
        }

        /**
         * The download task is stop
         * <p/>
         * operation of UI allowed.
         *
         * @param downloadTask the download task which has been stopped.
         */
        @Override
        public void onStop(DownloadTask downloadTask) {
            super.onStop(downloadTask);
            Log.i("onStop()");
        }

        /**
         * Runs on the UI thread before doInBackground(Params...).
         */
        @Override
        public void onStart() {
            super.onStart();
            Log.i("onStart()");
        }

        /**
         * Runs on the UI thread after publishProgress(Progress...) is invoked. The
         * specified values are the values passed to publishProgress(Progress...).
         *
         * @param values The values indicating progress.
         */
        @Override
        public void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ((DownloadTaskAdapter) getListAdapter()).notifyDataSetChanged();
            Log.i("onProgressUpdate");
        }

        /**
         * Runs on the UI thread after doInBackground(Params...). The specified
         * result is the value returned by doInBackground(Params...). This method
         * won't be invoked if the task was cancelled.
         *
         * @param downloadTask The result of the operation computed by
         *                     doInBackground(Params...).
         */
        @Override
        public void onSuccess(DownloadTask downloadTask) {
            super.onSuccess(downloadTask);
            Log.i("onSuccess()");
        }

        /**
         * Applications should preferably override onCancelled(Object). This method
         * is invoked by the default implementation of onCancelled(Object). Runs on
         * the UI thread after cancel(boolean) is invoked and
         * doInBackground(Object[]) has finished.
         */
        @Override
        public void onCancelled() {
            super.onCancelled();
            Log.i("onCancelled()");
        }

        @Override
        public void onError(Throwable thr) {
            super.onError(thr);
            Log.i("onError()");
        }

        /**
         * Runs on the UI thread after doInBackground(Params...) when the task is
         * finished or cancelled.
         */
        @Override
        public void onFinish() {
            super.onFinish();
            Log.i("onFinish()");
        }
    };


    private AdapterView.OnItemClickListener mItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mActivePosition = position;
            mDrawer.setActiveView(view, position);
            mAdapter.setActivePosition(position);
            mDrawer.closeMenu();
        }
    };

    @Override
    public void setContentView(int layoutResID) {
        // This override is only needed when using MENU_DRAG_CONTENT.
        mDrawer.setContentView(layoutResID);
        onContentChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        DownloadTask task = (DownloadTask) getListAdapter().getItem(position);

        if (task == null) {
            return;
        }

        switch (task.getStatus()) {
            case DownloadStatus.STATUS_PENDING:
            case DownloadStatus.STATUS_FAILED:
            case DownloadStatus.STATUS_STOPPED:
            case DownloadStatus.STATUS_FINISHED:
                downloadManager.start(task, listener);
                break;
            case DownloadStatus.STATUS_RUNNING:
                downloadManager.stop(task, listener);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                mDrawer.toggleMenu();
                return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        final int drawerState = mDrawer.getDrawerState();
        if (drawerState == MenuDrawer.STATE_OPEN || drawerState == MenuDrawer.STATE_OPENING) {
            mDrawer.closeMenu();
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void onActiveViewChanged(View v) {
        mDrawer.setActiveView(v, mActivePosition);
    }
}
