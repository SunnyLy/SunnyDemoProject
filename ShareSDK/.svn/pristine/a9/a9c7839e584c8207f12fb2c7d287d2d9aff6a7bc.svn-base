package com.het.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.het.share.manager.CommonSharePlatform;
import com.het.share.manager.CommonShareUtils;
import com.het.share.utils.HetToast;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 单纯的微信分享方法集合类
 * 这些方法都为public权限，可以供外部单独调用
 */
public class WeiXin {

	private static final int THUMB_SIZE = 120;

	private Context mContext;
	public WeiXin(Context context){
		this.mContext = context;
	}
	/**
	 * 分享图片至微信
	 *
	 * @param imgUrl 图片地址：可以是本地图片，也可以是网络图片
	 * @param type   SendMessageToWX.Req.WXSceneTimeline:朋友圈
	 *               SendMessageToWX.Req.WXSceneSession：微信好友
	 * @param iwxapi
	 */
	public void sharePicToWeixin(String imgUrl, String imgDescription, int type, IWXAPI iwxapi) {
		if (TextUtils.isEmpty(imgUrl)) {
			throw new NullPointerException("the imgUrl which will to be shared cannot be null");
		}
		WXMediaMessage msg = new WXMediaMessage();
		msg.description = imgDescription;
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.scene = type;
		req.transaction = CommonShareUtils.buildTransaction("img");
		if (imgUrl.startsWith("http://") || imgUrl.startsWith("https://")) {
			//网络图片
			getNetBitmap(imgUrl, msg, req, iwxapi);
		} else {
			//本地图片：SDCard/res
			getLocalBitmap(imgUrl, msg, req, iwxapi);
		}

	}


	/**
	 * 分享音乐至微信
	 *
	 * @param musicUrl     音频网页的URL地址
	 *                     注意：限制长度不超过10KB
	 * @param musicDataUrl 音频数据的URL地址
	 *                     注意：限制长度不超过10KB
	 * @param bm
	 * @param platform
	 * @param iwxapi
	 */
	public void shareMusicToWeixin(String musicUrl, String musicDataUrl, String title, String description,
								   Bitmap bm, CommonSharePlatform platform, IWXAPI iwxapi) {
		int type = SendMessageToWX.Req.WXSceneSession;
		if(platform == CommonSharePlatform.WeixinFriend){
			type = SendMessageToWX.Req.WXSceneSession;
		}else if(platform == CommonSharePlatform.WeixinFriendCircle){
			type = SendMessageToWX.Req.WXSceneTimeline;
		}
		WXMusicObject music = new WXMusicObject();
		music.musicUrl = musicUrl;
		music.musicDataUrl = musicDataUrl;

		//下面这个在低宽带时使用，
       /* music.musicLowBandUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";
        music.musicLowBandDataUrl = "http://staff2.ustc.edu.cn/~wdw/softdown/index.asp/0042515_05.ANDY.mp3";*/

		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = music;
		msg.title = title;
		msg.description = description;

		if(bm != null){
			Bitmap thumb = Bitmap.createScaledBitmap(bm, THUMB_SIZE, THUMB_SIZE, true);
			msg.setThumbImage(thumb);
		}

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = CommonShareUtils.buildTransaction("music");
		req.message = msg;
		req.scene = type;
		iwxapi.sendReq(req);

	}

	/**
	 * 分享视频，
	 * 目前暂时只有微信可以
	 */
	public void shareVideoToWeixin(String vidioUrl,Bitmap bm,String title,String description,int platform,
								   IWXAPI iwxapi){
		WXVideoObject video = new WXVideoObject();
		video.videoUrl = vidioUrl;

		WXMediaMessage msg = new WXMediaMessage(video);
		msg.title = title;
		msg.description = description;
		if(bm != null){
			Bitmap thumb = Bitmap.createScaledBitmap(bm, THUMB_SIZE, THUMB_SIZE, true);
			msg.setThumbImage(thumb);
		}

		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = CommonShareUtils.buildTransaction("video");
		req.message = msg;
		req.scene = platform;
		iwxapi.sendReq(req);
	}

	/**
	 * 分享网页至微信
	 * @param webPageUrl
	 * @param title
	 * @param des
	 * @param bitmap
	 * @param type       SendMessageToWX.Req.WXSceneTimeline:朋友圈
	 *                   SendMessageToWX.Req.WXSceneSession：微信好友
	 * @param iwxapi
	 */
	public void shareWebPageToWeixin(String webPageUrl, String title, String des, Bitmap bitmap, CommonSharePlatform type, IWXAPI iwxapi) {
		WXWebpageObject webpage = new WXWebpageObject();
		webpage.webpageUrl = webPageUrl;
		WXMediaMessage msg = new WXMediaMessage(webpage);
		msg.title = title;
		msg.description = des;
		if(bitmap !=null){
			Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, THUMB_SIZE,
					THUMB_SIZE, true);
			msg.setThumbImage(thumbBmp);
		}
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = CommonShareUtils.buildTransaction("webpage");
		req.message = msg;
		if(type == CommonSharePlatform.WeixinFriend){
			req.scene = SendMessageToWX.Req.WXSceneSession;
		}else if(type == CommonSharePlatform.WeixinFriendCircle){
			req.scene = SendMessageToWX.Req.WXSceneTimeline;
		}
		iwxapi.sendReq(req);
	}

	/**
	 * 分享文本至微信好友或朋友圈,可以单一拿来用
	 *
	 * @param content
	 * @param type    SendMessageToWX.Req.WXSceneTimeline:朋友圈
	 *                SendMessageToWX.Req.WXSceneSession：微信好友
	 */
	public void shareTextToWeixin(String content, int type, IWXAPI iwxapi) {
		// 初始化一个WXTextObject对象
		WXTextObject textObj = new WXTextObject();
		textObj.text = content;

		// 用WXTextObject对象初始化一个WXMediaMessage对象
		WXMediaMessage msg = new WXMediaMessage();
		msg.mediaObject = textObj;
		// 发送文本类型的消息时，title字段不起作用
		// msg.title = "Will be ignored";
		msg.description = content;

		// 构造一个Req
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = CommonShareUtils.buildTransaction("text"); // transaction字段用于唯一标识一个请求
		req.message = msg;
		req.scene = type;
		// 调用api接口发送数据到微信
		iwxapi.sendReq(req);
	}

	/**
	 * 获取本地图片
	 *
	 * @param imgUrl
	 * @param msg
	 * @param req
	 * @param iwxapi
	 */
	private void getLocalBitmap(String imgUrl, WXMediaMessage msg,
								SendMessageToWX.Req req, IWXAPI iwxapi) {

		Bitmap bitmap = BitmapFactory.decodeFile(imgUrl);
		if (bitmap != null) {
			WXImageObject wxImageObject = new WXImageObject(bitmap);
			msg.mediaObject = wxImageObject;
			Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
			msg.setThumbImage(thumbBmp);
			req.message = msg;
			iwxapi.sendReq(req);
		}
	}

	/**
	 * 获取网络图片
	 *
	 * @param imgUrl 图片地址
	 * @param msg    WXMediaMes 图片描述
	 * @param iwxapi
	 */
	private void getNetBitmap(String imgUrl, final WXMediaMessage msg,
							  final SendMessageToWX.Req req, final IWXAPI iwxapi) {
		//用Fresco获取bitmap
		ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgUrl))
				.setProgressiveRenderingEnabled(true).build();
		ImagePipeline imagePipeline = Fresco.getImagePipeline();
		DataSource<CloseableReference<CloseableImage>> dataSource =
				imagePipeline.fetchDecodedImage(imageRequest, mContext);
		dataSource.subscribe(new BaseBitmapDataSubscriber() {
			@Override
			protected void onNewResultImpl(@Nullable Bitmap bitmap) {
				if (bitmap != null) {
					try {
						WXImageObject wxImageObject = new WXImageObject(bitmap);
						msg.mediaObject = wxImageObject;
						Bitmap thumbBmp = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
//					msg.thumbData = BitmapUtils.createThumbBitmap(thumbBmp, true);
						msg.setThumbImage(thumbBmp);
						req.message = msg;
						iwxapi.sendReq(req);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			protected void onFailureImpl(DataSource<CloseableReference<CloseableImage>> dataSource) {

			}
		}, CallerThreadExecutor.getInstance());

	}

}
