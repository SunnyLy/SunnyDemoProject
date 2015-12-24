package sunnydemo2.imageloader.model;

/**
 * Created by sunny on 2015/12/22.
 * 本地图片实体类
 */
public class LocalImage {

    public String path;
    public String name;
    public long time;

    public LocalImage(String path, String name, long time) {
        this.path = path;
        this.name = name;
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocalImage that = (LocalImage) o;

        if (time != that.time) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }
}
