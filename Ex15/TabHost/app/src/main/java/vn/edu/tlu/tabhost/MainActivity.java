package vn.edu.tlu.tabhost;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends Activity {
    EditText edta, edtb;
    Button btncong;
    ListView lv1;
    ArrayList<String> list;
    ArrayAdapter<String> myarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl(); // Thiết lập các control
        addEvent();   // Thiết lập sự kiện
    }

    // Hàm thiết lập sự kiện
    private void addEvent() {
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xulycong(); // Xử lý phép tính khi nhấn nút "Tính tổng"
            }

            private void Xulycong() {
                // Lấy số từ EditText và tính tổng
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                String result = a + " + " + b + " = " + (a + b);

                // Thêm kết quả vào danh sách và cập nhật ListView
                list.add(result);
                myarray.notifyDataSetChanged();

                // Xóa nội dung trong EditText sau khi tính
                edta.setText("");
                edtb.setText("");
            }
        });
    }

    // Hàm thiết lập giao diện (Control)
    private void addControl() {
        // Khởi tạo TabHost
        TabHost tab = (TabHost) findViewById(R.id.tabhost);
        tab.setup();

        // Thiết lập Tab1 (tính toán)
        TabHost.TabSpec tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Tính Toán", getResources().getDrawable(R.drawable.ic_launcher_foreground));
        tab.addTab(tab1);

        // Thiết lập Tab2 (lịch sử)
        TabHost.TabSpec tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Lịch Sử", getResources().getDrawable(R.drawable.ic_launcher_background));
        tab.addTab(tab2);

        // Liên kết các View trong Tab1
        edta = (EditText) findViewById(R.id.edta);
        edtb = (EditText) findViewById(R.id.edtb);
        btncong = (Button) findViewById(R.id.btncong);

        // Liên kết ListView và Adapter trong Tab2
        lv1 = (ListView) findViewById(R.id.lv1);
        list = new ArrayList<String>();
        myarray = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
        lv1.setAdapter(myarray);
    }
}
