package hung.multilayoutlistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Hung on 2016/4/10.
 */
public class MultiLayoutAdapter extends ArrayAdapter {
    public static final int TYPE_YOU = 0;
    public static final int TYPE_ME_IMAGE = 1;
    public static final int TYPE_ME = 2;
    private final List<ListItems> objects;

    public MultiLayoutAdapter(Context context, int resource, List<ListItems> objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return objects.get(position).getType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        ListItems listItems = objects.get(position);
        int listViewItemType = getItemViewType(position);
        if (convertView == null) {
            if (listViewItemType == TYPE_YOU) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_text_you, null);
                TextView txtMessage = (TextView) convertView.findViewById(R.id.txtMessage);
                TextView txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.img_you);
                viewHolder = new ViewHolder(txtMessage, txtInfo, imageView);
                convertView.setTag(viewHolder);
            }else if (listViewItemType == TYPE_ME) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_text_me, null);
                TextView txtMessage = (TextView) convertView.findViewById(R.id.txtMessage);
                TextView txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);
                viewHolder = new ViewHolder(txtMessage, txtInfo);
                convertView.setTag(viewHolder);
            }else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.message_img_me, null);
                ImageView imageView = (ImageView) convertView.findViewById(R.id.img_sned);
                TextView txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);
                viewHolder = new ViewHolder(imageView, txtInfo);
                convertView.setTag(viewHolder);
            }
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(listViewItemType == TYPE_YOU) {
            viewHolder.getMessage().setText(listItems.getMessage());
            viewHolder.getInfo().setText(listItems.getInfo());
            viewHolder.getHead().setImageResource(listItems.getHead());
            final String finalStrText = listItems.getMessage();
            viewHolder.getMessage().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), finalStrText, Toast.LENGTH_SHORT).show();
                }
            });
        }else if(listViewItemType == TYPE_ME){
            viewHolder.getMessage().setText(listItems.getMessage());
            viewHolder.getInfo().setText(listItems.getInfo());
            final String finalStrText = listItems.getMessage();
            viewHolder.getMessage().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(), finalStrText, Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            viewHolder.getImage().setImageDrawable(listItems.getImage());
            viewHolder.getInfo().setText(listItems.getInfo());
        }
        return convertView;
    }

    public class ViewHolder {
        TextView textMessage;
        TextView textInfo;
        ImageView img;
        ImageView imgHead;
        public ViewHolder(TextView textMessage, TextView textInfo, ImageView imgHead) {
            this.textMessage = textMessage;
            this.textInfo = textInfo;
            this.imgHead = imgHead;
        }
        public ViewHolder(TextView textMessage, TextView textInfo) {
            this.textMessage = textMessage;
            this.textInfo = textInfo;
        }
        public ViewHolder(ImageView img, TextView textInfo) {
            this.img = img;
            this.textInfo = textInfo;
        }
        public TextView getMessage() {
            return textMessage;
        }
        public TextView getInfo() {
            return textInfo;
        }
        public ImageView getImage() {
            return img;
        }
        public ImageView getHead() {
            return imgHead;
        }
    }

}
