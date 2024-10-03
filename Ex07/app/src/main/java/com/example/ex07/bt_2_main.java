package com.example.ex07;

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

public class bt_2_main extends AppCompatActivity {

    EditText edt_a,edt_b;
    Button btn_kq, btn_return;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_a = findViewById(R.id.edt_a);
        edt_b = findViewById(R.id.edt_b);
        btn_kq = findViewById(R.id.btn_result_2);
        btn_return = findViewById(R.id.btn_2_return);
        btn_kq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(bt_2_main.this,bt_2_ket_qua.class);
                Bundle bundle1 = new Bundle();
                int a = Integer.parseInt(edt_a.getText()+"");
                int b = Integer.parseInt(edt_b.getText()+"");
                bundle1.putInt("soa", a);
                bundle1.putInt("sob",b);
                myintent.putExtra("mybackage", bundle1);
                startActivity(myintent);
            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bt_2_main.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}