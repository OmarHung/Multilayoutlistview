package hung.multilayoutlistview;

/**
 * Created by Hung on 2016/4/10.
 */
public class ListItems {
    private String text;
    private int image;
    private int type;

    public ListItems(String text, int type) {
        this.text=text;
        this.type=type;
    }

    public ListItems(int image, int type) {
        this.image=image;
        this.type=type;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public int getType() {
        return type;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setType(int type) {
        this.type = type;
    }
}
