package com.example.prac022;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private EditText edt_id, edt_name, edt_birth, edt_salary;
    private TextView tv_result, tb_notify;
    private Button btn;
    private StaffViewModel staffViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ các view
        edt_id = findViewById(R.id.edt_id);
        edt_name = findViewById(R.id.edt_name);
        edt_birth = findViewById(R.id.edt_birth);
        edt_salary = findViewById(R.id.edt_salary);
        tv_result = findViewById(R.id.tv_result);
        tb_notify = findViewById(R.id.tb_notify);
        btn = findViewById(R.id.btn);

        staffViewModel = new ViewModelProvider(this).get(StaffViewModel.class);

        staffViewModel.getStaffInfo().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String info) {
                tv_result.setText(info);
            }
        });

        staffViewModel.getNotification().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                tb_notify.setText(message);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edt_id.getText().toString();
                String name = edt_name.getText().toString();
                String birthDate = edt_birth.getText().toString();
                String salary = edt_salary.getText().toString();

                if (id.isEmpty() || name.isEmpty() || birthDate.isEmpty() || salary.isEmpty()) {
                    staffViewModel.setNotification("Vui lòng điền đầy đủ thông tin");
                } else {
                    String staffInfo = "ID: " + id + "\nName: " + name + "\nBirth Date: " + birthDate + "\nSalary: " + salary;

                    staffViewModel.setStaffInfo(staffInfo);
                    staffViewModel.setNotification("Đã thêm nhân viên");
                }
            }
        });
    }
}