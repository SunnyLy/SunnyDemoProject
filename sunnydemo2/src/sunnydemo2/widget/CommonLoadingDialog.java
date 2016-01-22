package sunnydemo2.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartbracelet.sunny.sunnydemo2.R;

public class CommonLoadingDialog {

    public static Class<LoadingDialog> mLoadingDialog;

    private static LoadingDialog getDefault() {
        return new CLifeLoadingDialog();
    }

    public static void registerStyle(Class loadingDialog) {
        mLoadingDialog = loadingDialog;
    }

    private CommonLoadingDialog() {
    }

    public interface LoadingDialog {
        public void show(String msg);

        public void hide();

        public void setContent(String content);

        public boolean isShowing();

        public void setContext(Context context);
    }

    public static LoadingDialog newInstance(Context context) {
        try {
            if (mLoadingDialog == null) {
                registerStyle(CLifeLoadingDialog.class);
            }
            LoadingDialog loadingDialog = mLoadingDialog.newInstance();
            loadingDialog.setContext(context);
            return loadingDialog;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return getDefault();
    }

    static class CLifeLoadingDialog implements LoadingDialog {


        private Context mContext;
        private TextView mTipTv;
        private ImageView loading_animation;
        private AnimationDrawable mAnimationDrawable;
        private Dialog mDialog;


        @Override
        public void show(String msg) {
            startAnimation();
            mTipTv.setText(TextUtils.isEmpty(msg)?"正在加载……":msg);
            if(!((Activity)mContext).isFinishing() && mDialog != null)
            mDialog.show();
        }

        @Override
        public void hide() {
            if (mContext != null && mDialog !=null && !((Activity)mContext).isFinishing()) {
                stopAnimation();
                mDialog.dismiss();
            }
        }

        @Override
        public void setContent(String content) {
            mTipTv.setText(content);
        }


        @Override
        public boolean isShowing() {
            return mDialog.isShowing();
        }

        @Override
        public void setContext(Context context) {
            mContext = context;
            mDialog = new Dialog(mContext, R.style.DialogFadeIn);
            initUI();
        }

        // 初始化dialog基础UI
        private void initUI() {
            View dlgView = LayoutInflater.from(mContext).inflate(R.layout.widget_process_loading_view, null);
            loading_animation = (ImageView) dlgView.findViewById(R.id.loading_animation);
            mAnimationDrawable = (AnimationDrawable) loading_animation.getDrawable();
            mTipTv = (TextView) dlgView.findViewById(R.id.process_loading_view_text);
            mDialog.setContentView(dlgView);
            mDialog.setCanceledOnTouchOutside(false);
        }

        /**
         * 开始动画
         */
        private void startAnimation(){
            if(mAnimationDrawable != null && !mAnimationDrawable.isRunning()){
                mAnimationDrawable.start();
            }
        }

        /**
         * 结束动画
         */
        private void stopAnimation(){
            if(mAnimationDrawable != null && mAnimationDrawable.isRunning()){
                mAnimationDrawable.stop();
            }
        }
    }


}
