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
 * The type of the DownloadTask
 *
 * @author snowdream <yanghui1986527@gmail.com>
 * @version v1.0
 * @date Sep 29, 2013
 */
public class DownloadType {
    /**
     * Unknown Type
     */
    public static final int TYPE_UNKNOWN = 0x00000000;

    /**
     * Text
     */
    public static final int TYPE_TEXT = 0x00000001;

    /**
     * Image
     */
    public static final int TYPE_IMAGE = 0x00000002;

    /**
     * Music
     */
    public static final int TYPE_MUSIC = 0x00000003;

    /**
     * Video
     */
    public static final int TYPE_VIDEO = 0x00000004;

    /**
     * App
     */
    public static final int TYPE_APP = 0x00000005;
}
