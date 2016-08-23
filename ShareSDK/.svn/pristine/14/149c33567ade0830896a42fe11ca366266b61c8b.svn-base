package com.het.share.model;

import android.graphics.drawable.Drawable;
import android.view.View;

import java.io.Serializable;

/**
 * Created by sunny on 2016/1/14.
 * 分享平台适配Model
 * 图标，名称
 */
public class CommonShareItemModel implements Serializable {

    private Drawable mShareDrawable;
    private String mShareTitle;
    private View.OnClickListener mOnClick;

    public CommonShareItemModel() {
    }

    public View.OnClickListener getOnClick() {
        return mOnClick;
    }

    public void setOnClick(View.OnClickListener mOnClick) {
        this.mOnClick = mOnClick;
    }

    public Drawable getmShareDrawable() {
        return mShareDrawable;
    }

    public void setmShareDrawable(Drawable mShareDrawable) {
        this.mShareDrawable = mShareDrawable;
    }

    public String getmShareTitle() {
        return mShareTitle;
    }

    public void setmShareTitle(String mShareTitle) {
        this.mShareTitle = mShareTitle;
    }

    @Override
    public String toString() {
        return "CommonShareItemModel{" +
                "mShareDrawable=" + mShareDrawable +
                ", mShareTitle='" + mShareTitle + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonShareItemModel that = (CommonShareItemModel) o;

        if (mShareDrawable != null ? !mShareDrawable.equals(that.mShareDrawable) : that.mShareDrawable != null)
            return false;
        return mShareTitle != null ? mShareTitle.equals(that.mShareTitle) : that.mShareTitle == null;

    }

    @Override
    public int hashCode() {
        int result = mShareDrawable != null ? mShareDrawable.hashCode() : 0;
        result = 31 * result + (mShareTitle != null ? mShareTitle.hashCode() : 0);
        return result;
    }
}
