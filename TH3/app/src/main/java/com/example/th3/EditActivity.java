package com.example.th3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class EditActivity extends AppCompatActivity {

    private EditText editTextTen, editTextTenDem, editTextHo, editTextNgaySinh, editTextEmail, editTextDiaChi, editTextChuyenNganh, editTextGPA, editTextNamHoc;
    private RadioGroup radioGroupGioiTinh;
    private Button buttonLuu;
    private Student student;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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
        editTextTen = findViewById(R.id.editText_ten);
        editTextTenDem = findViewById(R.id.editText_tenDem);
        editTextHo = findViewById(R.id.editText_ho);
        editTextNgaySinh = findViewById(R.id.editText_ngaySinh);
        editTextEmail = findViewById(R.id.editText_email);
        editTextDiaChi = findViewById(R.id.editText_diaChi);
        editTextChuyenNganh = findViewById(R.id.editText_chuyenNganh);
        editTextGPA = findViewById(R.id.editText_gpa);
        editTextNamHoc = findViewById(R.id.editText_namHoc);
        radioGroupGioiTinh = findViewById(R.id.radioGroup_gioiTinh);
        buttonLuu = findViewById(R.id.button_luu);
    }

    private void populateData() {
        editTextTen.setText(student.getFull_name().getFirst());
        editTextTenDem.setText(student.getFull_name().getMidd());
        editTextHo.setText(student.getFull_name().getLast());
        editTextNgaySinh.setText(student.getBirth_date());
        editTextEmail.setText(student.getEmail());
        editTextDiaChi.setText(student.getAddress());
        editTextChuyenNganh.setText(student.getMajor());
        editTextGPA.setText(String.valueOf(student.getGpa()));
        editTextNamHoc.setText(String.valueOf(student.getYear()));

        if (student.getGender().equalsIgnoreCase("Male")) {
            radioGroupGioiTinh.check(R.id.radioButton_nam);
        } else {
            radioGroupGioiTinh.check(R.id.radioButton_nu);
        }
    }

    private void saveStudent() {
        // Cập nhật thông tin sinh viên
        student.getFull_name().setFirst(editTextTen.getText().toString().trim());
        student.getFull_name().setMidd(editTextTenDem.getText().toString().trim());
        student.getFull_name().setLast(editTextHo.getText().toString().trim());
        student.setBirth_date(editTextNgaySinh.getText().toString().trim());
        student.setEmail(editTextEmail.getText().toString().trim());
        student.setAddress(editTextDiaChi.getText().toString().trim());
        student.setMajor(editTextChuyenNganh.getText().toString().trim());
        student.setGpa(Double.parseDouble(editTextGPA.getText().toString().trim()));
        student.setYear(Integer.parseInt(editTextNamHoc.getText().toString().trim()));

        int selectedId = radioGroupGioiTinh.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButton_nam) {
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
