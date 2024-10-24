package vn.edu.tlu.autocompleteexampleapp;


import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Mảng chứa danh sách các tỉnh thành Việt Nam
    String[] provinces = {"Hà Nội", "Huế", "Hà Giang", "Hà Nam", "Hải Phòng", "Hưng Yên",
            "Hậu Giang", "Hòa Bình", "Hồ Chí Minh", "Hải Dương", "Hà Tĩnh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các thành phần trong giao diện
        TextView selection = findViewById(R.id.selection);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.editauto);
        MultiAutoCompleteTextView multiAutoCompleteTextView = findViewById(R.id.multiAutoCompleteTextView1);

        // Tạo ArrayAdapter để gán dữ liệu cho AutoCompleteTextView và MultiAutoCompleteTextView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, provinces);

        // Gán adapter cho AutoCompleteTextView
        autoCompleteTextView.setAdapter(adapter);

        // Gán adapter cho MultiAutoCompleteTextView và thiết lập Tokenizer
        multiAutoCompleteTextView.setAdapter(adapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        // Sự kiện khi chọn một mục trong AutoCompleteTextView
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            // Hiển thị tỉnh thành đã chọn vào TextView
            selection.setText("Tỉnh thành đã chọn: " + parent.getItemAtPosition(position));
        });
    }
}
