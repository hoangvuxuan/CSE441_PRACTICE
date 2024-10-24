package vn.edu.tlu.spinnerexampleapp;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    // Khai báo mảng chứa các loại hàng hóa
    String arr1[] = {"Hàng Điện tử", "Hàng Hóa Chất", "Hàng Gia dụng", "Hàng Xây dựng"};
    TextView txt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần trong giao diện
        txt1 = findViewById(R.id.txt1);
        Spinner spin1 = findViewById(R.id.spinner1);

        // Tạo ArrayAdapter để kết nối dữ liệu với Spinner
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(
                MainActivity.this, android.R.layout.simple_spinner_item, arr1);

        // Thiết lập layout cho Dropdown của Spinner
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        // Gán Adapter cho Spinner
        spin1.setAdapter(adapter1);

        // Thiết lập sự kiện khi chọn một mục trong Spinner
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Khi người dùng chọn một mục trong Spinner, hiển thị nó trong TextView
                txt1.setText(arr1[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì khi không có mục nào được chọn
            }
        });
    }
}
