package com.het.share.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.het.share.R;
import com.het.share.listener.ICommonShareLinstener;
import com.het.share.manager.CommonSharePlatform;

public class CommonShareDialog extends CommonShareBaseLoadingDialog implements android.view.View.OnClickListener{

    public static final String TAG = "SelectPhotoDialog";

    private ICommonShareLinstener listener;

    /**
     * 简单分享弹出框
     * @param context 上下文
     * @param listener 分享监听
     */
    public CommonShareDialog(Context context, ICommonShareLinstener listener){
    	super(context, R.style.MyDialogStyleBottom);
		this.listener = listener;
        initUI(context,null);
    }


    /**
     * 自定义弹窗的style
     * @param context 上下文
     * @param style 自定义样式
     * @param listener 分享监听
     */
    public CommonShareDialog(Context context, int style, ICommonShareLinstener listener){
        super(context,style);
        this.listener = listener;
        initUI(context,null);
    }

    /**
     * 自定义布局的弹窗
     * @param context 上下文
     * @param contentView 自定义布局
     * @param listener 分享监听
     */
    public CommonShareDialog(Context context, View contentView, ICommonShareLinstener listener){
        super(context,R.style.MyDialogStyleBottom);
        this.listener = listener;
        initUI(context,contentView);
    }

    /**
     * 自定义样式，布局的弹窗
     * @param context 上下文
     * @param style 自定义样式
     * @param contentView 自定义布局
     * @param listener 分享监听
     */
    public CommonShareDialog(Context context, int style, View contentView, ICommonShareLinstener listener){
        super(context,style);
        this.listener = listener;
        initUI(context,contentView);
    }

    private void initUI(Context mContext,View contentView) {
        View dlgView = LayoutInflater.from(mContext).inflate(R.layout.common_vertical_dialog_layout, null);
        LinearLayout mContainer = (LinearLayout) dlgView.findViewById(R.id.common_dialog_content_container);
        setContentView(dlgView);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        window.setWindowAnimations(R.style.AnimBottom); // 设置显示动画
        window.setGravity(Gravity.BOTTOM); // 设置显示位置
        window.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT); // 设置布局大小

        if(contentView == null){
            //如果上层没有传内容View进来，则使用默认的
            View view = LayoutInflater.from(mContext).inflate(
                    R.layout.share_dialog, null);
            RelativeLayout wechatmomentsRe = (RelativeLayout) view.findViewById(R.id.wechatmomentsRe);
            RelativeLayout wechatRe = (RelativeLayout) view.findViewById(R.id.wechatRe);
            RelativeLayout qqRe = (RelativeLayout) view.findViewById(R.id.qqRe);
            RelativeLayout qqzoneRe = (RelativeLayout) view.findViewById(R.id.qqzoneRe);
            Button mBtnCancelShare = (Button) view.findViewById(R.id.cancel_share);
            wechatmomentsRe.setOnClickListener(this);
            wechatRe.setOnClickListener(this);
            qqRe.setOnClickListener(this);
            qqzoneRe.setOnClickListener(this);
            mBtnCancelShare.setOnClickListener(this);
            mContainer.addView(view, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }else{
            mContainer.addView(contentView,new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dismiss();
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

	@Override
	public void onClick(View arg0) {
		if(arg0.getId()==R.id.wechatmomentsRe){
			listener.onStartShare(CommonSharePlatform.WeixinFriendCircle);
			
		}else if(arg0.getId()==R.id.wechatRe){
            listener.onStartShare(CommonSharePlatform.WeixinFriend);
			
		}else if(arg0.getId()==R.id.qqRe){
			listener.onStartShare(CommonSharePlatform.QQ_Friend);
			
		}else if(arg0.getId()==R.id.qqzoneRe){
            listener.onStartShare(CommonSharePlatform.QQ_Zone);
			
		}else if(arg0.getId()==R.id.cancel_share){
			this.dismiss();
		}
	}

}
