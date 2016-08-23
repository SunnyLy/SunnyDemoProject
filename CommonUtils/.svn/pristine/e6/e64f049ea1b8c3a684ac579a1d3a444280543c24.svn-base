/*
 * -----------------------------------------------------------------
 * Copyright (C) 2012-2013, by Het, ShenZhen, All rights reserved.
 * -----------------------------------------------------------------
 *
 * File: Configs.java
 * Author: clark
 * Version: 1.0
 * Create: 2013-8-29
 *
 * Changes (from 2013-8-29)
 * -----------------------------------------------------------------
 * 2013-8-29 : 创建 Configs.java (clark);
 * -----------------------------------------------------------------
 */

package com.het.common.constant;

import android.os.Environment;

import org.apache.http.protocol.HTTP;

import java.io.File;

/**
 * @ClassName: Configs
 * @Description: 配置常量
 * @Author: clark
 * @Create: 2013-8-29
 */

public class Configs {

	/**
	 * @ClassName: Dir
	 * @Description: 目录的配置信�?
	 * @Author: clark
	 * @Create: 2013-5-25
	 */
	public static final class Dir {

		/**
		 * 存储卡的路径.
		 */
		public static final String STORAGE_PATH = Environment
				.getExternalStorageDirectory().getPath();
		
		public static final String SDCARD_PATH = "/mnt/sdcard/";

		/**
		 * 插件目录
		 */
		public static String PLUGIN_DIR = "HetPlugin";
		/**
		 * SD卡上保存数据的目录名.
		 */
		public static final String APP_DIR_NAME = "Het";

		/**
		 * 保存日志的目录名.
		 */
		public static final String LOGS_DIR_NAME = "Logs";

		/**
		 * 公共日志的目录名.
		 */
		public static final String COMMON_LOGS_DIR_NAME = "Common";

		/**
		 * 保存图片的目录名.
		 */
		public static final String IMAGES_DIR_NAME = "Images";

		/**
		 * 保存头像图片的目录名.
		 */
		public static final String AVATAR_DIR_NAME = "Avatar";

		/**
		 * 日志目录的路�?
		 */
		public static final String LOG_DIR = Dir.STORAGE_PATH + File.separator
				+ Dir.APP_DIR_NAME + File.separator + Dir.LOGS_DIR_NAME;

		/**
		 * 头像目录的路�?
		 */
		public static final String AVATAR_DIR = Dir.STORAGE_PATH
				+ File.separator + Dir.APP_DIR_NAME + File.separator
				+ Dir.IMAGES_DIR_NAME + File.separator + Dir.AVATAR_DIR_NAME;

		/**
		 * 头像临时文件�?
		 */
		public static final String AVATAR_TEMP_NAME = "temp.jpg";
	}

	/**
	 * @ClassName: Log
	 * @Description: 日志的配置信�?
	 * @Author: clark
	 * @Create: 2013-5-25
	 */
	public static final class Log {

		/**
		 * 日志的开关标识符，true表示日志关闭，false表示日志打开.
		 */
		public static final boolean OFF = false;

		/**
		 * 写日志的等级.
		 */
		public static final int WRITE_LEVEL = android.util.Log.INFO;
	}

	/**
	 * @ClassName: Net
	 * @Description: 网络的配置信�?
	 * @Author: clark
	 * @Create: 2013-5-25
	 */
	public static final class Net {

		/**
		 * 网络通信中字符集类型.
		 */
		public static final String NET_CHARSET = HTTP.UTF_8;

		/**
		 * 用户代理.
		 */

		public static final String USER_AGENT = "C-life";
		/**
		 * 连接池的�?��连接�?
		 */
		public static final int MAX_TOTAL_CONNECTIONS = 800;

		/**
		 * 连接池中取连接的超时时间.
		 */
		public static final int TIMEOUT_GET_CONNECTION = 3000;

		/**
		 * 移动网络连接超时时间.
		 */
		public static final int TIMEOUT_MOBILE_CONNECTION = 6000;

		/**
		 * Wifi连接超时时间.
		 */
		public static final int TIMEOUT_WIFI_CONNECTION = 5000;

		/**
		 * Socket连接超时时间.
		 */
		public static final int TIMEOUT_SOCKET_CONNECTION = 7000;

		/**
		 * Http模式.
		 */
		public static final String SCHEME_HTTP = "http";

		/**
		 * Https模式.
		 */
		public static final String SCHEME_HTTPS = "https";

		/**
		 * Http端口.
		 */
		public static final int PORT_HTTP = 80;

		/**
		 * Https端口.
		 */
		public static final int PORT_HTTPS = 8443;

		/**
		 * 密钥库密�?
		 */
		public static final String KEY_STORE_PASSWORD = "Het5";

		/**
		 * 信任库密�?
		 */
		public static final String TRUST_STORE_PASSWORD = "Het3";

		/**
		 * 网络异常时重试次�?
		 */
		public static final int RETRY_COUNT = 3;
	}

	/**
	 * @ClassName: Time
	 * @Description: 时间相关的配置信�?
	 * @Author: clark
	 * @Create: 2013-11-5
	 */
	public static final class Time {

		/**
		 * 配对麦步V2计步器界面显示提示信息的�?��等待时间 （单位：毫秒�?
		 */
		public static final int WAIT_BIND_PEDOMETER_REMIND = 3000;

		/**
		 * 每日详情界面显示提示修改身高体重信息的等待时�?（单位：毫秒�?
		 */
		public static final int WAIT_ALERT_MODIFY_WEIGHT_HEIGHT = 2000;

		/**
		 * 主题界面的最短停留时�?（单位：毫秒�?
		 */
		public static final int WAIT_THEME_ACTIVITY = 1000;

		/**
		 * 等待设备标识的最长时间（单位：秒�?
		 */
		public static final int WAIT_DEVICE_ID = 5;

		/**
		 * 等待数据服务器存储运动数据的时间（单位：毫秒�?
		 */
		public static final int WAIT_SERVER_SAVE_SPORT_DATA = 5000;

		/**
		 * 每日详情网络请求的时间间隔（单位：分钟）.
		 */
		public static final int INTERVAL_SPORT_DATA_REQUEST = 5;

		/**
		 * 好友排名网络请求的时间间隔（单位：分钟）.
		 */
		public static final int INTERVAL_FRIEND_RANKING_REQUEST = 30;

		/**
		 * 用户运动统计信息请求的时间间隔（单位：分钟）.
		 */
		public static final int INTERVAL_USER_SPORT_DATA_STATISTICS_REQUEST = 1;

	}

	/**
	 * @ClassName: Database
	 * @Description: 数据库的配置信息
	 * @Author: clark
	 * @Create: 2013-6-4
	 */
	public static final class Database {

		/**
		 * 数据库的版本�? 数据库版本 如果更改了安装的时候会执行 onupgrade方法
		 */
		public static final int VERSION = 9;

		/**
		 * 数据库的扩展�?
		 */
		public static final String EXTENSION = ".db";
	}

	/**
	 * @ClassName: About
	 * @Description: 关于的配置信�?
	 * @Author: clark
	 * @Create: 2013-8-15
	 */
	public static final class About {

		/**
		 * 麦步客服电话.
		 */
		public static final String TEL = "4000260012";

		/**
		 * 微信AppId.
		 */
		public static final String WEIXIN_APP_ID = "wx908606a8df0abedb";

		/**
		 * APP安装来源.
		 */
		public static final String APP_SOURCE = About.APP_VERSION_Het;

		/**
		 * APP平台
		 */
		public static final String APP_PLATFORM = "android";

		/**
		 * 360.
		 */
		public static final String APP_VERSION_360 = "360";

		/**
		 * 应用�?
		 */
		public static final String APP_VERSION_APPCHINA = "AppChina";

		/**
		 * 安卓市场.
		 */
		public static final String APP_VERSION_ANDROID_MARKET = "android Market";

		/**
		 * Google Play.
		 */
		public static final String APP_VERSION_GOOGLE_MARKET = "google play";

		/**
		 * 91.
		 */
		public static final String APP_VERSION_91 = "91";

		/**
		 * 豌豆�?
		 */
		public static final String APP_VERSION_WANDOUJIA = "wandoujia";

		/**
		 * 三星
		 */
		public static final String APP_VERSION_SAMSUNG = "Samsung Apps";

		/**
		 * 麦步.
		 */
		public static final String APP_VERSION_Het = "Het";
	}

	/**
	 * Loaclytics统计信息标签
	 * 
	 * @ClassName: LoaclyticsEventTag
	 * @Description: TODO
	 * @Author: LiuFeng
	 * @Create: 2013-12-2
	 */
	public static final class LoaclyticsEventTag {
		//
		// public static final String ADD_FRIENDS = "添加好友" ;
		//
		// public static final String HAND_SYNC = "手动同步";
		//
		// public static final String TODAY_DETAIL = "今日详情";
		//
		// public static final String TODAY_DETAIL_YESTERDAY = "今日详情昨天界面";
		//
		// public static final String TODAY_DETAIL_BEFORE_YESTERDAY =
		// "今日详情前天界面";
		//
		// public static final String FEEDBACK = "建议反馈";
		//
		// public static final String SHARE_WX = "分享微信";
		//
		// public static final String SHARE_PYQ = "分享朋友�?;
		//
		// public static final String RANKING_LIST = "排行�?;
		//
		// public static final String HISTORY_TREND = "历史趋势";
		//
		// public static final String HISTORY_WEEK_BEFORE = "历史趋势�?天前数据";
		//
		// public static final String JOIN_US = "加入我们";
		//
		// public static final String HISTORY_TO_DAY_DETIL = "历史趋势点击查看天详�?;
		//
		// public static final String PERSONALINFO = "个人资料";
		//
		// public static final String DELETE_FRIEND = "删除好友";
		//
		// public static final String FRIEND_DETAIL = "好友详情";
		//
		// public static final String BANDING_DEVICE = "绑定设备";
		//
		// public static final String OPEN_AUTO_SYNC = "自动同步的打�?��闭设�?;
		//
		// public static final String SET_IDO_MENU = "计步器显示设�?;
		//
		// public static final String USER_HEADICON = "用户名头�?;
		//
		// public static final String MODIFY_PASS = "修改密码";
		//
		// public static final String SET_HEIGHT_WEIGHT = "身高体重";
		//
		// public static final String BUY_IDO = "去天猫购买麦�?;

		/**
		 * 打开APP
		 */
		public static final String APP_OPEN = "android-APP opens";
		/**
		 * 每日详情
		 */
		public static final String Datily_details = "android-Datily details";

		/**
		 * 同步数据
		 */
		public static final String Data_SYNC = "android-Data_SYNC";

		/**
		 * 运动详情�?
		 */
		public static final String Movement_details_page = "android-Movement details page";
		/**
		 * 历史趋势
		 */
		public static final String Historical_trend = "android-Historical trend";
		/**
		 * 点击排行�?
		 */
		public static final String Click_rank_list = "android-Click rank list";
		/**
		 * 好友详情�?
		 */
		public static final String Friends_details_page = "android-Friends details page";
		/**
		 * 七天步数PK功能
		 */
		public static final String PK_in_seven_days = "android-PK in seven days";
		/**
		 * 个人资料
		 */
		public static final String The_personal_data = "android-The personal data";
		/**
		 * 添加好友
		 */
		public static final String Add_friend = "android-Add friend";
		/**
		 * 删除好友
		 */
		public static final String Remove_friend = "android-Remove friend";
		/**
		 * 分享方式
		 */
		public static final String Way_to_share = "android-Way to share";
		/**
		 * 分享�?
		 */
		public static final String Share_to = "android-Share to";
		/**
		 * 建议&反馈
		 */
		public static final String Advice_feedback = "android-Advice & feedback";
		/**
		 * 加入我们
		 */
		public static final String Join_us = "android-Join us";
		/**
		 * 给我们评�?
		 */
		public static final String Rate_us = "android-Rate us";
		/**
		 * 去天猫购买麦�?
		 */
		public static final String Tmall = "android-Tmall";
		/**
		 * V2显示设置
		 */
		public static final String V2_display_Settings = "android-V2 display Settings";
		/**
		 * v3显示设置
		 */
		public static final String V3_display_Settings = "android-V3 display Settings";
		/**
		 * 下载来源
		 */
		public static final String DownLoad_the_channels = "android-DownLoad the channels";

	}

	/**
	 * @ClassName: APP_OPEN_EventProperty
	 * @Description: 打开APP属�?
	 * @Author: LiuFeng
	 * @Create: 2014-1-7
	 */
	public static final class APP_OPEN_EventProperty {
		public static final String APP = "App";

		public static final String APP_ANDROID = "android";
	}

	/**
	 * @ClassName: DailyDetail_EventProperty
	 * @Description:每日详情属�?
	 * @Author: LiuFeng
	 * @Create: 2014-1-7
	 */
	public static final class DailyDetail_EventProperty {
		/**
		 * Property_tag
		 */
		public static final String Open_card_number = "Open card number";

		/**
		 * 今天
		 */
		public static final String today = "today";
		/**
		 * 昨天
		 */
		public static final String yesterday = "yesterday";
		/**
		 * 前天
		 */
		public static final String yesterday1 = "yesterday1";
		/**
		 * 大前�?
		 */
		public static final String yesterday2 = "yesterday2";
		/**
		 * 4天前
		 */
		public static final String yesterday3 = "yesterday3";
		/**
		 * 5天前
		 */
		public static final String yesterday4 = "yesterday4";
		/**
		 * 6天前
		 */
		public static final String yesterday5 = "yesterday5";
		/**
		 * 7天前
		 */
		public static final String other = "other";
	}

	/**
	 * @ClassName: Data_SYNC_EventProperty
	 * @Description: 同步数据属�?
	 * @Author: LiuFeng
	 * @Create: 2014-1-7
	 */
	public static final class Data_SYNC_EventProperty {

		/**
		 * wifi_tag
		 */
		public static final String WiFi = "Wi-Fi";

		/**
		 * 蓝牙同步成功
		 */
		public static final String wifiSuccess = "successful";
		/**
		 * 蓝牙同步失败
		 */
		public static final String wifiFailuer = "failuer";

		/**
		 * bluetooth_tag
		 */
		public static final String bluetooth = "bluetooth";

		/**
		 * 蓝牙同步成功
		 */
		public static final String blueToothSuccess = "successful";
		/**
		 * 蓝牙同步失败
		 */
		public static final String blueToothFailuer = "failuer";
	}

	/**
	 * @ClassName: Historical_trend_EventProperty
	 * @Description:历史趋势属�?
	 * @Author: LiuFeng
	 * @Create: 2014-1-7
	 */
	public static final class Historical_trend_EventProperty {
		/**
		 * operation_tag
		 */
		public static final String operation = "operation";

		/**
		 * 查看天详�?
		 */
		public static final String operation_day_details = "Check the day details";
		/**
		 * �?天前数据
		 */
		public static final String operation_7days_details = "See 7 days prior to the data";
	}

	/**
	 * @ClassName: Click_rank_list_EventProperty
	 * @Description: 点击排行榜，七天步数PK属�?
	 * @Author: LiuFeng
	 * @Create: 2014-1-7
	 */
	public static final class Click_rank_and_PK_EventProperty {
		/**
		 * Gender_tag
		 */
		public static final String Gender = "Gender";
		/**
		 * 男�?
		 */
		public static final String gender_male = "male";
		/**
		 * 女�?
		 */
		public static final String gender_female = "female";
		/**
		 * 未知
		 */
		public static final String gender_nuknown = "unknown";
		// ===================================================
		/**
		 * Age_tag
		 */
		public static final String Age = "Age";
		/**
		 * 20岁以�?
		 */
		public static final String age_20 = "younger than 20";
		/**
		 * 20~30
		 */
		public static final String age_30 = "20 ~ 29";
		/**
		 * 30~40
		 */
		public static final String age_40 = "30 ~ 39";
		/**
		 * 40~50
		 */
		public static final String age_50 = "40 ~ 49";
		/**
		 * 大于50
		 */
		public static final String age_60 = "50 older";
		/**
		 * citise_tag
		 */
		public static final String citise = "Cities";
	}

	/**
	 * @ClassName: The_personal_data_EventProperty
	 * @Description: 个人详情属�?
	 * @Author: LiuFeng
	 * @Create: 2014-1-7
	 */
	public static final class The_personal_data_EventProperty {
		/**
		 * Setting_tag
		 */
		public static final String Setting = "Setting";

		/**
		 * 设置头像姓名
		 */
		public static final String setting_name_avater = "photo & username";
		/**
		 * 设置身高体重
		 */
		public static final String setting_height_weight = "height & weight";
		/**
		 * 重设密码
		 */
		public static final String setting_pass = "change the password";
	}

	/**
	 * @ClassName: Way_to_share_EventProperty
	 * @Description: 分享方式属�?
	 * @Author: LiuFeng
	 * @Create: 2014-1-7
	 */
	public static final class Way_to_share_EventProperty {
		/**
		 * Inforgraphic_tag
		 */
		public static final String Inforgraphic = "Inforgraphic";

		/**
		 * 分享排行�?
		 */
		public static final String share_type_rank = "Ranking";
		/**
		 * 分享每日详情
		 */
		public static final String share_type_day = "the daily details";

		// ========================================================
		/**
		 * Camera_tag
		 */
		public static final String Camera = "Camera";
		/**
		 * 相机模板1
		 */
		public static final String template1 = "template 1";
		/**
		 * 相机模板2
		 */
		public static final String template2 = "template 2";
		/**
		 * 相机模板3
		 */
		public static final String template3 = "template 3";
		/**
		 * 相机模板4
		 */
		public static final String template4 = "template 4";
		/**
		 * 相机模板5
		 */
		public static final String template5 = "template 5";
		/**
		 * 相机模板6
		 */
		public static final String template6 = "template 6";
	}

	/**
	 * @ClassName: Share_to_EventProperty
	 * @Description: 分享到属�?
	 * @Author: LiuFeng
	 * @Create: 2014-1-7
	 */
	public static final class Share_to_EventProperty {
		/**
		 * share_to_tag
		 */
		public static final String Share_to = "Share to";

		/**
		 * 微信
		 */
		public static final String share_to_wx = "Wechat";
		/**
		 * 朋友�?
		 */
		public static final String share_to_pyq = "wechat friends";
		/**
		 * 微博
		 */
		public static final String share_to_weibo = "weibo";
		/**
		 * qq空间
		 */
		public static final String shate_to_Qzone = "Qzone";
	}

	/**
	 * @ClassName: Preferences
	 * @Description: Preferences的配置信�?
	 * @Author: clark
	 * @Create: 2013-9-25
	 */
	public static final class Preferences {

		/**
		 * App信息的Preferences名称.
		 */
		public static final String APP_PREFERENCE = "app";

		/**
		 * 用户登录信息
		 */
		public static final String LOGIN_PREFERENCE = "userInfo";
		/**
		 * 用户名信息
		 */
		public static final String USER_ACCOUNT = "userAccount";
		/**
		 * 用户密码信息
		 */
		public static final String USER_PASS = "userPass";
		/**
		 * 用户密码信息
		 */
		public static final String AUTO_LOGIN = "AUTO_ISCHECK";

		/**
		 * 登录的用户标
		 */
		public static final String LAST_LOGIN_USER_ID = "last_login_user_id";

		/**
		 * 最后登录的用户帐�?
		 */
		public static final String LAST_LOGIN_USER_ACCOUNT = "last_login_user_account";

		/**
		 * 登录用户默认房子ID
		 */
		public static final String DEFAULT_HOUSEID = "default_houseid";
		/**
		 * 登录用户默认房子名称
		 */
		public static final String DEFAULT_HOUSENAME = "default_housename";

		/**
		 * 注册时的随机码
		 */
		public static final String REGISTER_RANDOM = "register_random";

		/**
		 * 修改密码时的随机码
		 */
		public static final String MODIFY_PASSWORD_RANDOM = "modify_password_random";
		/**
		 * 修改密码时的返回码
		 */
		public static final String PASSWORD_RANDOM = "password_random";
		/**
		 * 重置密码时的随机没
		 */
		public static final String RESET_PASSWORD_RANDOM = "reset_password_random";

		/**
		 * 发送给设备的服务器IP
		 */
		public static final String SERVER_IP = "server_ip";
		/**
		 * 发送给设备的服务器端口
		 */
		public static final String SERVER_PORT = "server_port";
	}

	/**
	 * @ClassName: HandlerMsg
	 * @Description: 通用的�?信消�?
	 * @Author: clark
	 * @Create: 2013-10-18
	 */
	public static final class HandlerMsg {

		/**
		 * �?��重新登录消息.
		 */
		public static final int MSG_RELOGIN = 9000;

		/**
		 * 网络错误消息.
		 */
		public static final int MSG_NETWORK_ERROR = 9001;

		/**
		 * 网络超时消息.
		 */
		public static final int MSG_NETWORK_TIMEOUT = 9002;

		/**
		 * 服务器不响应消息.
		 */
		public static final int MSG_SERVER_NOT_RESPONSE = 9003;

		/**
		 * 业务错误消息.
		 */
		public static final int MSG_BUSINESS_ERROR = 9004;

	}

	public static final String ap_pass = "aaaaa";

	/**
	 * ap信息
	 * 
	 * @author HET
	 */
	public static class APInfo {
		// GANSBAN模块IP
		// public static final String G_AP_IP = "192.168.1.100";
		// public static final int G_AP_PORT = 3000;
		// //汉方模块IP
		// public static final String H_AP_IP = "10.10.100.254";
		// public static final int H_AP_PORT = 8899;

		public static final String AP_IP = "1.10.100.250";
		public static final int AP_PORT = 8080;
		public static final String AP_SSID_FLAG = "het-AP";
		// public static final String AP_SSID_FLAG = "iPhone";
		public static final String AP_PASSWORD = "1234567890";

	}

	/**
	 * 协议信息
	 * 
	 * @author HET
	 */
	public static class ProtocolInfo {

		/**
		 * 终端软件版本占位（byte�?
		 */
		public static final int TERMINAL_SOFTWARE_VERSION_LENGTH = 3;
		/**
		 * 协议版本占位（byte�?
		 */
		public static final int PROTOCOL_VERSION_LENGTH = 2;
		/**
		 * 加密类型占位（byte�?
		 */
		public static final int ENCRYPT_TYPE_LENGTH = 1;
		/**
		 * 报文类型占位
		 */
		public static final int PACKET_TYPE_LENGTH = 1;
		/**
		 * 上行数据实体占位
		 */
		public static final int UPLOAD_ITEM_LENGTH = 1;
		/**
		 * 命令字占�?
		 */
		public static final int COMMAND_LENGTH = 2;
		/**
		 * 设备物理地址占位
		 */
		public static final int MAC_LENGTH = 6;
		/**
		 * 协议时间
		 */
		public static final int CURRENT_TIME_LENGTH = 8;
		/**
		 * 包体长度占位
		 */
		public static final int BODYLENGTH_LENGTH = 2;
		/**
		 * 包头长度
		 */
		public static final int PACKETHEAD_LENGTH = 27;

	}

	/**
	 * 命令�?
	 * 
	 * @author HET
	 */
	public static class CommandConstant {
		/**
		 * 配置设备
		 */
		public static final short CONFIG_DEVICE = 0X0021;
	}

	/**
	 * 服务器信�?
	 * 
	 * @author HET
	 */
	public static class ServerInfo {
		public static final String SERVER_IP = "";
		public static final String SERVER_PORT = "";

		 //测试
//		 public static final String SERVER_HOSE = "http://200.200.200.50";
//		 public static final String USER_SERVER_ADDRESS = "http://200.200.200.50/ws-user";
//		 public static final String FRIDGE_SERVER_ADDRESS = "http://200.200.200.50/ws-smart-fridge";

		// ===================================================================

		public static final String SERVER_HOSE = "https://203.195.138.139:8443";
		public static final String USER_SERVER_ADDRESS = "https://203.195.138.139:8443/ws-user";
		public static final String FRIDGE_SERVER_ADDRESS = "https://203.195.138.139:8443/ws-smart-fridge";

	}

	public static class LocalWifiInfo {
		public static final String LOCAL_WIFI = "LOCAL-WIFI";
		public static final String LOCAL_WIFI_PASS = "WIFI_PASS";
	}

	/**
	 * 智能模式
	 * 
	 * @author clark
	 *         <p/>
	 *         2014年6月20日
	 */
	public static class IntelligentModeInfo {
		public static final String freezeTempr = "";
		public static final String coolingTempr = "";
		public static final String channgeTempr = "";

	}

	/**
	 * 节能模式
	 * 
	 * @author clark
	 *         <p/>
	 *         2014年6月20日
	 */
	public static class EcoModeInfo {
		public static final String freezeTempr = "";
		public static final String coolingTempr = "";
		public static final String channgeTempr = "";
	}

	/**
	 * 速冻模式
	 * 
	 * @author clark
	 *         <p/>
	 *         2014年6月20日
	 */
	public static class QuickFreezeModeInfo {

		public static final String coolingTempr = "";
		public static final String channgeTempr = "";
	}

	/**
	 * 速冷模式
	 * 
	 * @author clark
	 *         <p/>
	 *         2014年6月20日
	 */
	public static class QuickCoolingModeInfo {
		public static final String freezeTempr = "";
		public static final String channgeTempr = "";
	}
}
