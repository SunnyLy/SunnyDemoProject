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

package com.github.snowdream.android.app.downloader.dao;

import com.github.snowdream.android.app.downloader.DownloadTask;

import java.sql.SQLException;

/**
 * @author snowdream <yanghui1986527@gmail.com>
 * @version v1.0
 * @date Oct 7, 2013
 */
public interface ISql {
    public void addDownloadTask(DownloadTask task) throws SQLException;

    public void updateDownloadTask(DownloadTask task) throws SQLException;

    public DownloadTask queryDownloadTask(DownloadTask task) throws SQLException;

    //public List<DownloadTask> queryDownloadTasksFromAlbum(Album album)throws SQLException;

    public void deleteDownloadTask(DownloadTask task) throws SQLException;
}
