package vn.edu.tlu.ex16;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtmalop, edttenlop, edtsiso;
    Button btninsert, btndelete, btnupdate, btnquery;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khai báo các biến giao diện
        edtmalop = findViewById(R.id.edtmalop);
        edttenlop = findViewById(R.id.edttenlop);
        edtsiso = findViewById(R.id.edtsiso);
        btninsert = findViewById(R.id.btninsert);
        btndelete = findViewById(R.id.btndelete);
        btnupdate = findViewById(R.id.btnupdate);
        btnquery = findViewById(R.id.btnquery);
        lv = findViewById(R.id.lv);

        // Tạo ListView
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        // Tạo và mở cơ sở dữ liệu SQLite
        mydatabase = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);

        // Tạo bảng tbllop nếu chưa tồn tại
        try {
            String sql = "CREATE TABLE IF NOT EXISTS tbllop(malop TEXT PRIMARY KEY, tenlop TEXT, siso INTEGER)";
            mydatabase.execSQL(sql);
        } catch (Exception e) {
            Log.e("Error", "Table đã tồn tại");
        }

        // Thêm dữ liệu
        btninsert.setOnClickListener(view -> {
            String malop = edtmalop.getText().toString();
            String tenlop = edttenlop.getText().toString();
            int siso = Integer.parseInt(edtsiso.getText().toString());

            ContentValues myvalue = new ContentValues();
            myvalue.put("malop", malop);
            myvalue.put("tenlop", tenlop);
            myvalue.put("siso", siso);

            String msg;
            if (mydatabase.insert("tbllop", null, myvalue) == -1) {
                msg = "Thêm thất bại!";
            } else {
                msg = "Thêm thành công!";
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });

        // Xóa dữ liệu
        btndelete.setOnClickListener(view -> {
            String malop = edtmalop.getText().toString();
            int n = mydatabase.delete("tbllop", "malop = ?", new String[]{malop});
            String msg;
            if (n == 0) {
                msg = "Không có dữ liệu để xóa";
            } else {
                msg = n + " bản ghi đã bị xóa";
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });

        // Cập nhật dữ liệu
        btnupdate.setOnClickListener(view -> {
            String malop = edtmalop.getText().toString();
            int siso = Integer.parseInt(edtsiso.getText().toString());

            ContentValues myvalue = new ContentValues();
            myvalue.put("siso", siso);

            int n = mydatabase.update("tbllop", myvalue, "malop = ?", new String[]{malop});
            String msg;
            if (n == 0) {
                msg = "Không có bản ghi để cập nhật";
            } else {
                msg = n + " bản ghi đã được cập nhật";
            }
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        });

        // Hiển thị danh sách lớp
        btnquery.setOnClickListener(view -> {
            mylist.clear();
            Cursor c = mydatabase.query("tbllop", null, null, null, null, null, null);
            c.moveToNext();
            String data;
            while (!c.isAfterLast()) {
                data = c.getString(0) + " - " + c.getString(1) + " - " + c.getInt(2);
                mylist.add(data);
                c.moveToNext();
            }
            c.close();
            myadapter.notifyDataSetChanged();
        });
    }
}
