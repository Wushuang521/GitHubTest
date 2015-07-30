package com.zrc.android.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import com.zrc.android.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {
    private ListView mListView1;
    private ListView mListView2;
    private ListView mListView3;
	private ListView mListView4;
    private String[] mStringArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_main_activity);

        findViewsById();

        mStringArray = getResources().getStringArray(R.array.listItems);

        setAdapters();
    }

    private void findViewsById() {
        mListView1 = (ListView) findViewById(R.id.listView1);
        mListView2 = (ListView) findViewById(R.id.listView2);
        mListView3 = (ListView) findViewById(R.id.listView3);
    }

    private void setAdapters() {
        setAdapterForListView1();
        setAdapterForListView2();
        setAdapterForListView3();
    }

    
    private void setAdapterForListView1() {
        // 可以将所有的数据存放在一个数组里，所以使用ArrayAdapter
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStringArray);
        mListView1.setAdapter(arrayAdapter);
    }

    private void setAdapterForListView2() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        
        int i = 0;
        if(true) {
            i++;
        }

        // 所有的ListItem都放在一个Map里
        // 所有的ListItem的对应行的key必须相同
        
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("row1", "hi");
        item1.put("row2", "儿童节快乐");

        Map<String, Object> item2 = new HashMap<String, Object>();
        item2.put("row1", "hello");
        item2.put("row2", "哆啦A梦上映了");
        
        Map<String, Object> item3 = new HashMap<String, Object>();
        item3.put("row1", "great");
        item3.put("row2", "Brillo发布了");
        
        Map<String, Object> item4 = new HashMap<String, Object>();
        item4.put("row1", "hoho");
        item4.put("row2", "AndroidM来了");
        
        // 约定优于配置  Conventions Over Configuarations

        // 把所有的ListItem对应的Map都放在一个List中
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);

        // 通过映射关系把ListView中的所有ListItem显示出来
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list,
                android.R.layout.simple_list_item_2,
                new String[] {
                        "row1", "row2"
                },
                new int[] {
                        android.R.id.text1, android.R.id.text2
                });

        mListView2.setAdapter(simpleAdapter);
    }

    @SuppressWarnings("deprecation")
    private void setAdapterForListView3() {
        // 从联系人数据库中查询到的结果集
        Cursor cursor = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        startManagingCursor(cursor);

        // 通过映射关系把ListView中的所有ListItem显示出来
        ListAdapter listAdapter = new SimpleCursorAdapter(
                this,
                android.R.layout.two_line_list_item,
                cursor,
                new String[] {
                        ContactsContract.Contacts._ID, ContactsContract.Contacts.DISPLAY_NAME
                },
                new int[] {
                        android.R.id.text1, android.R.id.text2
                });
        
        mListView3.setAdapter(listAdapter);
    }

}
