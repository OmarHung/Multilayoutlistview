package hung.multilayoutlistview;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private Button sendMessage, sendImage;
    private ArrayList<ListItems> items;
    private MultiLayoutAdapter customAdapter;
    private static final int REQUESTCODE_PICK = 0;
    private static final int REQUESTCODE_TAKE = 1;
    private static final int REQUESTCODE_CUTTING = 2;
    private static final String IMAGE_FILE_NAME = "Image.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initMessage();
    }
    private void initMessage() {
        items = new ArrayList<ListItems>();
        items.add(new ListItems("嗨~我是立婷", "2016年4月11日 上午11:10:05", R.drawable.you, MultiLayoutAdapter.TYPE_YOU));
        items.add(new ListItems("你好0.0", "2016年4月11日 上午11:10:30", MultiLayoutAdapter.TYPE_ME));
        items.add(new ListItems("學長，我喜歡你耶", "2016年4月11日 上午11:10:35", R.drawable.you, MultiLayoutAdapter.TYPE_YOU));
        items.add(new ListItems("我知道阿。", "2016年4月11日 上午11:10:55", MultiLayoutAdapter.TYPE_ME));
        items.add(new ListItems("為什麼你知道！", "2016年4月11日 上午11:11:08", R.drawable.you, MultiLayoutAdapter.TYPE_YOU));
        items.add(new ListItems("我長那麼帥大家都馬喜歡我", "2016年4月11日 上午11:11:19", MultiLayoutAdapter.TYPE_ME));
        customAdapter = new MultiLayoutAdapter(this, R.id.text, items);
        listView.setAdapter(customAdapter);
    }
    private void initWidget() {
        listView = (ListView) findViewById(R.id.messagesContainer);
        listView.setDividerHeight(0);

        editText = (EditText) findViewById(R.id.messageEdit);

        sendMessage = (Button) findViewById(R.id.chatSendButton);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editText.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }
                editText.setText("");
                String DateTime = DateFormat.getDateTimeInstance().format(new Date());
                items.add(new ListItems(messageText, DateTime, MultiLayoutAdapter.TYPE_ME));
                listView.setAdapter(customAdapter);
                customAdapter.notifyDataSetChanged();
                scroll();
            }
        });

        sendImage = (Button) findViewById(R.id.sendImageButton);
        sendImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] Items = {"拍照", "從相簿選擇"};
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("傳送圖片")
                        .setItems(Items, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        Intent takeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        takeIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), IMAGE_FILE_NAME)));
                                        startActivityForResult(takeIntent, REQUESTCODE_TAKE);
                                        break;
                                    case 1:
                                        Intent pickIntent = new Intent(Intent.ACTION_PICK, null);
                                        pickIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                                        startActivityForResult(pickIntent, REQUESTCODE_PICK);
                                        break;
                                }
                            }
                        }).show();
                /*
                                String DateTime = DateFormat.getDateTimeInstance().format(new Date());
                                items.add(new ListItems(DateTime, MultiLayoutAdapter.TYPE_ME));
                                listView.setAdapter(customAdapter);
                                customAdapter.notifyDataSetChanged();
                                scroll();
                                */
            }
        });
    }
    private void scroll() {
        listView.setSelection(listView.getCount() - 1);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUESTCODE_PICK:
                try {
                    startPhotoZoom(data.getData());
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                break;
            case REQUESTCODE_TAKE:
                File temp = new File(Environment.getExternalStorageDirectory() + "/" + IMAGE_FILE_NAME);
                startPhotoZoom(Uri.fromFile(temp));
                break;
            case REQUESTCODE_CUTTING:
                if (data != null) {
                    setPicToView(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 3000);
        intent.putExtra("outputY", 3000);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, REQUESTCODE_CUTTING);
    }
    private void setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            Drawable drawable = new BitmapDrawable(null, photo);
            String DateTime = DateFormat.getDateTimeInstance().format(new Date());
            items.add(new ListItems(drawable, DateTime, MultiLayoutAdapter.TYPE_ME_IMAGE));
            listView.setAdapter(customAdapter);
            customAdapter.notifyDataSetChanged();
            scroll();
        }
    }
}
