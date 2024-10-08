package com.example.th3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

public class EditActivity extends AppCompatActivity {

    private EditText editTextTen, editTextTenDem, editTextHo, editTextGPA;
    private RadioGroup radioGroupGioiTinh;
    private Button buttonLuu;
    private Student student;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student); // Thiết kế layout chỉnh sửa sinh viên

        initView();

        // Lấy dữ liệu sinh viên và vị trí từ Intent
        String studentJson = getIntent().getStringExtra("student_data");
        student = new Gson().fromJson(studentJson, Student.class);
        position = getIntent().getIntExtra("position", -1);

        populateData();

        buttonLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();
            }
        });
    }

    private void initView() {
        editTextTen = findViewById(R.id.editTextTen);
        editTextTenDem = findViewById(R.id.editTextTenDem);
        editTextHo = findViewById(R.id.editTextHo);
        editTextGPA = findViewById(R.id.editTextGPA);
        radioGroupGioiTinh = findViewById(R.id.radioGroupGioiTinh);
        buttonLuu = findViewById(R.id.buttonLuu);
    }

    private void populateData() {
        editTextTen.setText(student.getFull_name().getFirst());
        editTextTenDem.setText(student.getFull_name().getMidd());
        editTextHo.setText(student.getFull_name().getLast());
        editTextGPA.setText(String.valueOf(student.getGpa()));

        if (student.getGender().equalsIgnoreCase("Male")) {
            radioGroupGioiTinh.check(R.id.radioButtonNam);
        } else {
            radioGroupGioiTinh.check(R.id.radioButtonNu);
        }
    }

    private void saveStudent() {
        // Cập nhật thông tin sinh viên
        student.getFull_name().setFirst(editTextTen.getText().toString().trim());
        student.getFull_name().setMidd(editTextTenDem.getText().toString().trim());
        student.getFull_name().setLast(editTextHo.getText().toString().trim());
        student.setGpa(Double.parseDouble(editTextGPA.getText().toString().trim()));

        int selectedId = radioGroupGioiTinh.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButtonNam) {
            student.setGender("Male");
        } else {
            student.setGender("Female");
        }

        // Trả dữ liệu về MainActivity
        Intent intent = new Intent();
        intent.putExtra("edited_student", new Gson().toJson(student));
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
