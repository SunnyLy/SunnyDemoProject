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


/**
 * The status of The DownloadTaskTask
 *
 * @author snowdream <yanghui1986527@gmail.com>
 * @version v1.0
 * @date Sep 29, 2013
 */
public class DownloadStatus {

    /**
     * the DownloadTask has successfully completed.
     */
    public static final int STATUS_PENDING = 0x00000001;

    /**
     * The DownloadTask is currently running.
     */
    public static final int STATUS_RUNNING = 0x00000002;

    /**
     * The DownloadTask is stopped.
     */
    public static final int STATUS_STOPPED = 0x00000003;

    /**
     * The DownloadTask has successfully completed.
     */
    public static final int STATUS_FINISHED = 0x00000005;

    /**
     * The DownloadTask has failed (and will not be retried).
     */
    public static final int STATUS_FAILED = 0x00000006;

    /**
     * The DownloadTask has been deleted.
     */
    public static final int STATUS_DELETED = 0x00000007;
}
