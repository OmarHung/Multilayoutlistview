package hung.multilayoutlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hung on 2016/4/10.
 */
public class MultiLayoutAdapter extends ArrayAdapter {
    public static final int TYPE_LEFT_TXT = 0;
    public static final int TYPE_LEFT_IMG = 1;
    public static final int TYPE_RIGHT_TXT = 2;
    public static final int TYPE_RIGHT_IMG = 3;

    private ListItems[] objects;

    public MultiLayoutAdapter(Context context, int resource, ListItems[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return objects[position].getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        ListItems listItems = objects[position];
        int listViewItemType = getItemViewType(position);
        if (convertView == null) {
            if (listViewItemType == TYPE_LEFT_TXT) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_left_text, null);
                TextView textView = (TextView) convertView.findViewById(R.id.txt);
                viewHolder = new ViewHolder(textView);
                convertView.setTag(viewHolder);
            } else if (listViewItemType == TYPE_LEFT_IMG) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_left_image, null);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.img);
                viewHolder = new ViewHolder(imageView);
                convertView.setTag(viewHolder);
            } else if (listViewItemType == TYPE_RIGHT_TXT) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_right_text, null);
                TextView textView = (TextView) convertView.findViewById(R.id.txt);
                viewHolder = new ViewHolder(textView);
                convertView.setTag(viewHolder);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_right_image, null);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.img);
                viewHolder = new ViewHolder(imageView);
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(listViewItemType==TYPE_LEFT_TXT || listViewItemType==TYPE_RIGHT_TXT) viewHolder.getText().setText(listItems.getText());
        else viewHolder.getImage().setImageResource(listItems.getImage());
        return convertView;
    }

    public class ViewHolder {
        TextView text;
        ImageView img;
        public ViewHolder(TextView text) {
            this.text = text;
        }
        public ViewHolder(ImageView img) {
            this.img = img;
        }

        public TextView getText() {
            return text;
        }

        public ImageView getImage() {
            return img;
        }

        public void setText(TextView text) {
            this.text = text;
        }

        public void setImage(ImageView img) {
            this.img = img;
        }

    }

}
