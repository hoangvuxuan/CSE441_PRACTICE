package vn.edu.tlu.custom_gridview;

public class Image {
    private int img;
    private String name;

    public Image(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }
}
