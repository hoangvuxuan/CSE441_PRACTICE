package vn.edu.tlu.gridviewproject;



import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Mảng chứa danh sách sản phẩm
    String[] arr = {
            "Ipad", "Iphone", "New Ipad", "SamSung", "Nokia", "Sony Ericson",
            "LG", "Q-Mobile", "HTC", "Blackberry", "G Phone", "FPT - Phone", "HK Phone"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần giao diện
        final TextView selection = findViewById(R.id.selection);
        final GridView gridView = findViewById(R.id.gridView1);

        // Gán DataSource vào ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);

        // Gán adapter cho GridView
        gridView.setAdapter(adapter);

        // Thiết lập sự kiện khi chọn phần tử trong GridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Hiển thị phần tử được chọn lên TextView
                selection.setText("Sản phẩm đã chọn: " + arr[position]);
            }
        });
    }
}