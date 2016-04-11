package hung.multilayoutlistview;

import android.graphics.drawable.Drawable;

/**
 * Created by Hung on 2016/4/10.
 */
public class ListItems {
    private String textMessage;
    private String textInfo;
    private int imgHead;
    private int type;
    private Drawable image;
    public ListItems(String textMessage, String textInfo, int imgHead, int type) {
        this.textMessage=textMessage;
        this.textInfo=textInfo;
        this.imgHead=imgHead;
        this.type=type;
    }
    public ListItems(String textMessage, String textInfo, int type) {
        this.textMessage=textMessage;
        this.textInfo=textInfo;
        this.type=type;
    }
    public ListItems(Drawable image, String textInfo, int type) {
        this.image=image;
        this.textInfo=textInfo;
        this.type=type;
    }
    public String getMessage() {
        return textMessage;
    }
    public String getInfo() {
        return textInfo;
    }
    public Drawable getImage() {
        return image;
    }
    public int getHead() {
        return imgHead;
    }
    public int getType() {
        return type;
    }
}
