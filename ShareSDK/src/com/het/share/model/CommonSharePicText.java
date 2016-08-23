package com.het.share.model;

import java.util.Arrays;

/**
 * Created by sunny on 2016/1/13.
 * 分享图文信息实体类
 */
public class CommonSharePicText extends CommonShareBaseBean {

    /**
     * 多张图片
     */
    private String[] imgUrls;

    public String[] getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String[] imgUrls) {
        this.imgUrls = imgUrls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CommonSharePicText that = (CommonSharePicText) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(imgUrls, that.imgUrls);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(imgUrls);
        return result;
    }
}
