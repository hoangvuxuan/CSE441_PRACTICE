package com.example.th3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class activity_add_student extends AppCompatActivity {

    private EditText editTextTen, editTextTenDem, editTextHo, editTextNgaySinh, editTextEmail,
            editTextDiaChi, editTextChuyenNganh, editTextGPA, editTextNamHoc;
    private RadioGroup radioGroupGioiTinh;
    private Button buttonLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Khởi tạo các trường nhập liệu
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

        // Thêm sự kiện cho nút Lưu
        buttonLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveStudent();


            }
        });
    }

    private void saveStudent() {
        // Lấy giá trị từ các trường nhập liệu
        String firstName = editTextTen.getText().toString().trim();
        String middleName = editTextTenDem.getText().toString().trim();
        String lastName = editTextHo.getText().toString().trim();
        String birthDate = editTextNgaySinh.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String address = editTextDiaChi.getText().toString().trim();
        String major = editTextChuyenNganh.getText().toString().trim();
        String gpa = editTextGPA.getText().toString().trim();
        String year = editTextNamHoc.getText().toString().trim();

        if (firstName.isEmpty()) {
            editTextTen.setError("Tên phải được nhập đầy đủ");
            editTextTen.requestFocus();
            return;
        }

        if (middleName.isEmpty()) {
            editTextTenDem.setError("Tên đệm phải được nhập đầy đủ");
            editTextTenDem.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            editTextHo.setError("Họ phải được nhập đầy đủ");
            editTextHo.requestFocus();
            return;
        }

        if (birthDate.isEmpty()) {
            editTextNgaySinh.setError("Ngày sinh phải được nhập đầy đủ");
            editTextNgaySinh.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email phải được nhập đầy đủ");
            editTextEmail.requestFocus();
            return;
        }

        if (address.isEmpty()) {
            editTextDiaChi.setError("Địa chỉ phải được nhập đầy đủ");
            editTextDiaChi.requestFocus();
            return;
        }

        if (major.isEmpty()) {
            editTextChuyenNganh.setError("Chuyên ngành phải được nhập đầy đủ");
            editTextChuyenNganh.requestFocus();
            return;
        }

        if (gpa.isEmpty()) {
            editTextGPA.setError("GPA phải được nhập đầy đủ");
            editTextGPA.requestFocus();
            return;
        }

        if (year.isEmpty()) {
            editTextNamHoc.setError("Năm học phải được nhập đầy đủ");
            editTextNamHoc.requestFocus();
            return;
        }

        int selectedId = radioGroupGioiTinh.getCheckedRadioButtonId();
        String gender = null;
        if (selectedId == R.id.radioButton_nam) {
            gender = "Male";
        } else if (selectedId == R.id.radioButton_nu) {
            gender = "Female";
        }

        if (gender == null) {
            Toast.makeText(this, "Bạn phải chọn giới tính", Toast.LENGTH_LONG).show();
            return;
        }



        try {
            Student student = new Student
                    (
                            address, birthDate, email,
                            new Student.FullName(firstName, lastName, middleName),
                            gender, Double.parseDouble(gpa),
                            12, major, Integer.parseInt(year));

            Intent intent = new Intent();
            intent.putExtra("new_student", student);
            setResult(RESULT_OK, intent);
            finish();

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Lỗi khi lưu thông tin sinh viên", Toast.LENGTH_SHORT).show();
        }
    }





}
