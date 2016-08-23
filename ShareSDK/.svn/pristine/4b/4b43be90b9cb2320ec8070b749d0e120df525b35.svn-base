package com.het.share.model;

import android.graphics.Bitmap;

import com.het.share.manager.CommonSharePlatform;
import com.tencent.tauth.IUiListener;

import java.io.Serializable;

/**
 * Created by sunny on 2016/1/9.
 * 分享实体类
 * 像title,description,appName,targetUrl,
 * imgUrl,musicUrl,musicDataUrl,bm,shareType,IUiListener等
 */
public class CommonShareBean implements Serializable{

    private String title;
    private String description;
    private String appName;
    private String targetUrl;
    private String imgUrl;
    private String musicUrl;
    private String musicDataUrl;
    private Bitmap bm;
    private CommonSharePlatform sharePlatform;
    private IUiListener uiListener;

    public IUiListener getUiListener() {
        return uiListener;
    }

    public void setUiListener(IUiListener uiListener) {
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

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getMusicDataUrl() {
        return musicDataUrl;
    }

    public void setMusicDataUrl(String musicDataUrl) {
        this.musicDataUrl = musicDataUrl;
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
        return "CommonShareBean{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", appName='" + appName + '\'' +
                ", targetUrl='" + targetUrl + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", musicUrl='" + musicUrl + '\'' +
                ", musicDataUrl='" + musicDataUrl + '\'' +
                ", bm=" + bm +
                ", sharePlatform=" + sharePlatform +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        CommonShareBean that = (CommonShareBean) object;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (appName != null ? !appName.equals(that.appName) : that.appName != null) return false;
        if (targetUrl != null ? !targetUrl.equals(that.targetUrl) : that.targetUrl != null)
            return false;
        if (imgUrl != null ? !imgUrl.equals(that.imgUrl) : that.imgUrl != null) return false;
        if (musicUrl != null ? !musicUrl.equals(that.musicUrl) : that.musicUrl != null)
            return false;
        if (musicDataUrl != null ? !musicDataUrl.equals(that.musicDataUrl) : that.musicDataUrl != null)
            return false;
        if (bm != null ? !bm.equals(that.bm) : that.bm != null) return false;
        return sharePlatform == that.sharePlatform;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (appName != null ? appName.hashCode() : 0);
        result = 31 * result + (targetUrl != null ? targetUrl.hashCode() : 0);
        result = 31 * result + (imgUrl != null ? imgUrl.hashCode() : 0);
        result = 31 * result + (musicUrl != null ? musicUrl.hashCode() : 0);
        result = 31 * result + (musicDataUrl != null ? musicDataUrl.hashCode() : 0);
        result = 31 * result + (bm != null ? bm.hashCode() : 0);
        result = 31 * result + (sharePlatform != null ? sharePlatform.hashCode() : 0);
        return result;
    }
}
