package com.example.prac01;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChildActivity extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_point;
    private Button btn_return;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        edt_name = findViewById(R.id.edt_name);
        edt_point = findViewById(R.id.edt_point);
        btn_return = findViewById(R.id.btn_return);

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("hoTen", edt_name.getText().toString());
                returnIntent.putExtra("diemTichLuy", edt_point.getText().toString());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}