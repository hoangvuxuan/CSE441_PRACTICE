package vn.edu.tlu.worknoteapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity {

    // Khai báo các biến
    ListView lv;
    ArrayList<String> arraywork;
    ArrayAdapter<String> arrAdapater;
    EditText edtwork, edth, edtm;
    TextView txtdate;
    Button btnwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần trong giao diện
        edth = findViewById(R.id.edthour);
        edtm = findViewById(R.id.edtmi);
        edtwork = findViewById(R.id.edtwork);
        btnwork = findViewById(R.id.btnadd);
        lv = findViewById(R.id.listView1);
        txtdate = findViewById(R.id.txtdate);

        // Khai báo mảng ArrayList kiểu String rỗng
        arraywork = new ArrayList<>();

        // Khai báo ArrayAdapter, đưa mảng dữ liệu vào ArrayAdapter
        arrAdapater = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arraywork);

        // Đưa Adapter có dữ liệu lên ListView
        lv.setAdapter(arrAdapater);

        // Lấy ngày giờ hệ thống và hiển thị lên TextView
        Date currentDate = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm Nay: " + simpleDateFormat.format(currentDate));

        // Viết phương thức khi Click vào Button btnwork
        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Nếu 1 trong 3 EditText không có nội dung thì hiện lên thông báo bằng hộp thoại
                if (edtwork.getText().toString().equals("") || edth.getText().toString().equals("")
                        || edtm.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Info missing");
                    builder.setMessage("Please enter all information of the work");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Đóng hộp thoại
                        }
                    });
                    builder.show();
                } else {
                    // Lấy nội dung công việc và thời gian ra từ EditText và đưa vào mảng arraywork
                    String str = edtwork.getText().toString() + " - " + edth.getText().toString() + ":" + edtm.getText().toString();

                    // Cập nhật dữ liệu vào mảng và thông báo thay đổi cho adapter
                    arraywork.add(str);
                    arrAdapater.notifyDataSetChanged();

                    // Xóa nội dung đã nhập trong các EditText
                    edth.setText("");
                    edtm.setText("");
                    edtwork.setText("");
                }
            }
        });
    }
}
