package hung.multilayoutlistview;

/**
 * Created by Hung on 2016/4/10.
 */
public class ListItems {
    private String textMessage;
    private String textInfo;
    private int image;
    private int type;

    public ListItems(String textMessage, String textInfo, int image, int type) {
        this.textMessage=textMessage;
        this.textInfo=textInfo;
        this.image=image;
        this.type=type;
    }
    public ListItems(String textMessage, String textInfo, int type) {
        this.textMessage=textMessage;
        this.textInfo=textInfo;
        this.type=type;
    }
    public String getMessage() {
        return textMessage;
    }
    public String getInfo() {
        return textInfo;
    }
    public int getImage() {
        return image;
    }
    public int getType() {
        return type;
    }
}
