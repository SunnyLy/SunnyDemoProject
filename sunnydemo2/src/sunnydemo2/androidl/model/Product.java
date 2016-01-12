package sunnydemo2.androidl.model;

/**
 * Created by sunny on 2016/1/12.
 * Annotion:
 */
public class Product {
    private int img;
    private String title;

    public Product(int img, String title) {
        this.img = img;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
