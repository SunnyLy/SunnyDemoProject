package com.het.share.model;

import android.graphics.Bitmap;

import com.het.share.listener.ICommonShareListener;
import com.het.share.manager.CommonSharePlatform;

import java.io.Serializable;

/**
 * Created by sunny on 2016/1/9.
 * 分享实体基类
 * 像title,description,appName,targetUrl,
 * imgUrl,musicUrl,musicDataUrl,bm,shareType,IShareListener等
 */
public class CommonShareBaseBean implements Serializable{

    private String title;
    private String description;
    private String appName;
    private String targetUrl;
    private String imgUrl;
    private Bitmap bm;
    private CommonSharePlatform sharePlatform;
    private ICommonShareListener uiListener;

    public ICommonShareListener getUiListener() {
        return uiListener;
    }

    public void setUiListener(ICommonShareListener uiListener) {
        this.uiListener = uiListener;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Bitmap getBm() {
        return bm;
    }

    public void setBm(Bitmap bm) {
        this.bm = bm;
    }

    public CommonSharePlatform getSharePlatform() {
        return sharePlatform;
    }

    public void setSharePlatform(CommonSharePlatform sharePlatform) {
        this.sharePlatform = sharePlatform;
    }

    @Override
    public String toString() {
        return "CommonShareBaseBean{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", appName='" + appName + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", bm=" + bm +
                ", sharePlatform=" + sharePlatform +
                ", uiListener=" + uiListener +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommonShareBaseBean that = (CommonShareBaseBean) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
        if (targetUrl != null ? !targetUrl.equals(that.targetUrl) : that.targetUrl != null)
            return false;
        if (imgUrl != null ? !imgUrl.equals(that.imgUrl) : that.imgUrl != null) return false;
        if (bm != null ? !bm.equals(that.bm) : that.bm != null) return false;
        if (sharePlatform != that.sharePlatform) return false;
        return uiListener != null ? uiListener.equals(that.uiListener) : that.uiListener == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        result = 31 * result + (targetUrl != null ? targetUrl.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (bm != null ? bm.hashCode() : 0);
        result = 31 * result + (sharePlatform != null ? sharePlatform.hashCode() : 0);
        result = 31 * result + (uiListener != null ? uiListener.hashCode() : 0);
        return result;
    }
}
