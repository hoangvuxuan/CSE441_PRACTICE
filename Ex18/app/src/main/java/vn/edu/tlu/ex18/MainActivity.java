package vn.edu.tlu.ex18;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String DB_PATH_SUFFIX = "/databases/";
    public static SQLiteDatabase database = null;
    public static String DATABASE_NAME = "arirang.sqlite";
    EditText edttim;
    ListView lv1, lv2, lv3;
    ArrayList<Item> list1, list2, list3;
    MyArrayAdapter myarray1, myarray2, myarray3;
    TabHost tab;
    ImageButton btnxoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        processCopy(); // Copy CSDL arirang.sqlite

        database = openOrCreateDatabase("arirang.sqlite", MODE_PRIVATE, null);
        addControl(); // Thêm các Controls
        addTim(); // Xử lý tìm kiếm
        addEvents(); // Xử lý sự kiện
    }

    private void addControl() {
        btnxoa = findViewById(R.id.btnxoa);
        tab = findViewById(R.id.tabhost);
        tab.setup();
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.search));
        tab.addTab(tab1);

        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.list));
        tab.addTab(tab2);

        TabHost.TabSpec tab3 = tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("", getResources().getDrawable(R.drawable.favourite));
        tab.addTab(tab3);

        edttim = findViewById(R.id.edttim);
        lv1 = findViewById(R.id.listview1);
        lv2 = findViewById(R.id.listview2);
        lv3 = findViewById(R.id.listview3);
        list1 = new ArrayList<Item>();
        list2 = new ArrayList<Item>();
        list3 = new ArrayList<Item>();
        loadData(); // Load dữ liệu
    }

    private void addTim() {
        edttim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = edttim.getText().toString();
                list1.clear();
                Cursor c = database.rawQuery("SELECT * FROM ArirangSongList WHERE TIEUDE LIKE'%" + searchText + "%'", null);
                while (c.moveToNext()) {
                    Item item = new Item();
                    item.setMaso(c.getString(0));
                    item.setTieude(c.getString(2));
                    item.setThich(c.getInt(6));
                    list1.add(item);
                }
                myarray1.notifyDataSetChanged();
                c.close();
            }
        });
    }

    private void addEvents() {
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list3.clear();
                Cursor c = database.rawQuery("SELECT * FROM ArirangSongList WHERE YEUTHICH=1", null);
                while (c.moveToNext()) {
                    Item item = new Item();
                    item.setMaso(c.getString(0));
                    item.setTieude(c.getString(2));
                    item.setThich(c.getInt(6));
                    list3.add(item);
                }
                myarray3.notifyDataSetChanged();
                c.close();
            }
        });
    }

    private void loadData() {
        Cursor c = database.rawQuery("SELECT * FROM ArirangSongList", null);
        while (c.moveToNext()) {
            Item item = new Item();
            item.setMaso(c.getString(0));
            item.setTieude(c.getString(2));
            item.setThich(c.getInt(6));
            list2.add(item);
        }
        myarray2 = new MyArrayAdapter(this, R.layout.item_list, list2);
        lv2.setAdapter(myarray2);
        c.close();
    }

    private void processCopy() {
        String dbPath = getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
        File file = new File(dbPath);
        if (!file.exists()) {
            try {
                InputStream input = getResources().openRawResource(R.raw.arirang);
                OutputStream output = new FileOutputStream(dbPath);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = input.read(buffer)) > 0) {
                    output.write(buffer, 0, length);
                }
                output.flush();
                output.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
