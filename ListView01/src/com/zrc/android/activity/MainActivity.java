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
	private ListView mListView5;
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
        // 鍙互灏嗘墍鏈夌殑鏁版嵁瀛樻斁鍦ㄤ竴涓暟缁勯噷锛屾墍浠ヤ娇鐢ˋrrayAdapter
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

        // 鎵�鏈夌殑ListItem閮芥斁鍦ㄤ竴涓狹ap閲�
        // 鎵�鏈夌殑ListItem鐨勫搴旇鐨刱ey蹇呴』鐩稿悓
        
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("row1", "hi");
        item1.put("row2", "鍎跨鑺傚揩涔�");

        Map<String, Object> item2 = new HashMap<String, Object>();
        item2.put("row1", "hello");
        item2.put("row2", "鍝嗗暒A姊︿笂鏄犱簡");
        
        Map<String, Object> item3 = new HashMap<String, Object>();
        item3.put("row1", "great");
        item3.put("row2", "Brillo鍙戝竷浜�");
        
        Map<String, Object> item4 = new HashMap<String, Object>();
        item4.put("row1", "hoho");
        item4.put("row2", "AndroidM鏉ヤ簡");
        
        // 绾﹀畾浼樹簬閰嶇疆  Conventions Over Configuarations

        // 鎶婃墍鏈夌殑ListItem瀵瑰簲鐨凪ap閮芥斁鍦ㄤ竴涓狶ist涓�
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);

        // 閫氳繃鏄犲皠鍏崇郴鎶奓istView涓殑鎵�鏈塋istItem鏄剧ず鍑烘潵
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
        // 浠庤仈绯讳汉鏁版嵁搴撲腑鏌ヨ鍒扮殑缁撴灉闆�
        Cursor cursor = this.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        startManagingCursor(cursor);

        // 閫氳繃鏄犲皠鍏崇郴鎶奓istView涓殑鎵�鏈塋istItem鏄剧ず鍑烘潵
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
