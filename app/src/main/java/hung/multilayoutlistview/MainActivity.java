package hung.multilayoutlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private EditText editText;
    private Button button;
    private ArrayList<ListItems> items;
    private MultiLayoutAdapter customAdapter;
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

        button = (Button) findViewById(R.id.chatSendButton);
        button.setOnClickListener(new View.OnClickListener() {
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
    }
    private void scroll() {
        listView.setSelection(listView.getCount() - 1);
    }
}
