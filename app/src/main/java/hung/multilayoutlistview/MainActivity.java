package hung.multilayoutlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        final ListItems[] items = new ListItems[40];

        for (int i = 0; i < items.length; i++) {
            if (i == 4) {
                items[i] = new ListItems(R.mipmap.ic_launcher, MultiLayoutAdapter.TYPE_RIGHT_IMG);
            } else if (i == 9) {
                items[i] = new ListItems(R.mipmap.ic_launcher, MultiLayoutAdapter.TYPE_LEFT_IMG);
            } else if (i % 2 == 0) {
                items[i] = new ListItems("Left text " + i, MultiLayoutAdapter.TYPE_LEFT_TXT);
            } else {
                items[i] = new ListItems("Right text " + i, MultiLayoutAdapter.TYPE_RIGHT_TXT);
            }
        }
        MultiLayoutAdapter customAdapter = new MultiLayoutAdapter(this, R.id.text, items);
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), items[i].getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
