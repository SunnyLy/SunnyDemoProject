package sunnydemo2.sharesdk;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.het.share.R;

/**
 * Created by sunny on 2016/3/11.
 * Annotion:
 */
public abstract class BaseDialogFragment extends DialogFragment implements View.OnClickListener{

    private View rootView;
    public Context mContext;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mContext = getActivity();
        Dialog rootDialog = new Dialog(getActivity(),getStyle()==0? R.style.CommonShareDialogStyleBottom:getStyle());
        rootView = LayoutInflater.from(getActivity()).inflate(getLayoutId(),null);
        addView(rootView.findViewById(R.id.common_dialog_content_container));
        rootDialog.setContentView(rootView);
        rootDialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = rootDialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        //一定要设置height,不然默认是match_parent
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        return rootDialog;
    }

    /**
     * 获取Dialog的样式，由子类去实现
     * @return
     */
    protected abstract int getStyle();

    /**
     * 获取布局文件，由子类去实现
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 往里面动态添加分享平台
     * @param rootView
     */
    protected  abstract void addView(View rootView);


    /**
     * 获取跟布局。
     * @return
     */
    public View getRootView(){
        return rootView;
    }

    /**
     * 绑定控件
     * @param resId
     */
    public void bindView(int resId){
        View rootView = getRootView();
        if(rootView != null){
            rootView.findViewById(resId).setOnClickListener(this);
        }

    }

    /**
     * 显示对话框
     */
    public void showDialog(){
        if(!getActivity().isFinishing() && getDialog() != null && !getDialog().isShowing()){
            getDialog().show();
        }
    }

    /**
     * 隐藏对话框
     */
    public void dismissDialog(){
        if(!getActivity().isFinishing() && getDialog() != null && getDialog().isShowing()){
            getDialog().dismiss();
        }
    }

}
