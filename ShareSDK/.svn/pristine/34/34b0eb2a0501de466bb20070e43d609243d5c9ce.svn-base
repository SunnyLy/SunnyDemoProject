package com.het.share.model;

/**
 * Created by sunny on 2016/1/13.
 * 网页音乐实体
 */
public class CommonShareMusic extends CommonShareBaseBean {

    /**
     * 音频网页的URL地址
     * 注意：限制长度不超过10KB
     */
    private String musicUrl;
    /**
     * 音频数据的URL地址
     * 注意：限制长度不超过10KB
     **/
    private String musicDataUrl;
    /**
     * 供低带宽环境下使用的音频网页URL地址
     * 注意：限制长度不超过10KB
     */
    private String musicLowBandUrl;
    /**
     * 供低带宽环境下使用的音频数据URL地址
     注意：限制长度不超过10KB
     */
    private String musicLowBandDataUrl;

    public String getMusicLowBandUrl() {
        return musicLowBandUrl;
    }

    public void setMusicLowBandUrl(String musicLowBandUrl) {
        this.musicLowBandUrl = musicLowBandUrl;
    }

    public String getMusicLowBandDataUrl() {
        return musicLowBandDataUrl;
    }

    public void setMusicLowBandDataUrl(String musicLowBandDataUrl) {
        this.musicLowBandDataUrl = musicLowBandDataUrl;
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

    @Override
    public String toString() {
        return "CommonShareMusic{" +
                "musicUrl='" + musicUrl + '\'' +
                ", musicDataUrl='" + musicDataUrl + '\'' +
                ", musicLowBandUrl='" + musicLowBandUrl + '\'' +
                ", musicLowBandDataUrl='" + musicLowBandDataUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CommonShareMusic that = (CommonShareMusic) o;

        if (musicUrl != null ? !musicUrl.equals(that.musicUrl) : that.musicUrl != null)
            return false;
        if (musicDataUrl != null ? !musicDataUrl.equals(that.musicDataUrl) : that.musicDataUrl != null)
            return false;
        if (musicLowBandUrl != null ? !musicLowBandUrl.equals(that.musicLowBandUrl) : that.musicLowBandUrl != null)
            return false;
        return musicLowBandDataUrl != null ? musicLowBandDataUrl.equals(that.musicLowBandDataUrl) : that.musicLowBandDataUrl == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (musicUrl != null ? musicUrl.hashCode() : 0);
        result = 31 * result + (musicDataUrl != null ? musicDataUrl.hashCode() : 0);
        result = 31 * result + (musicLowBandUrl != null ? musicLowBandUrl.hashCode() : 0);
        result = 31 * result + (musicLowBandDataUrl != null ? musicLowBandDataUrl.hashCode() : 0);
        return result;
    }
}
