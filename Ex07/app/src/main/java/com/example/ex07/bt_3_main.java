package com.example.ex07;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class bt_3_main extends AppCompatActivity {

    EditText edtA, edtB, edtKQ;
    Button btnrequest;
    @Override
    protected void onCreate(Bundle
                                    savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtA = findViewById(R.id.edtB);
        edtB = findViewById(R.id.edtB);
        edtKQ = findViewById(R.id.edtkq);
        btnrequest = findViewById(R.id.btnrequest);

        btnrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myintent = new Intent(MainActivity.this, bt_3_ket_qua.class);
                int a = Integer.parseInt(edtA.getText().toString());
                int b = Integer.parseInt(edtB.getText().toString());
                myintent.putExtra("soa",a);
                myintent.putExtra("sob",b);
                startActivityForResult(myintent,99);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,
                resultCode, data);
        if (requestCode == 99 && resultCode == 33)
        {
            int sum = data.getIntExtra("kq",0);
            edtKQ.setText("Tổng 2 số là: "+sum);
        }
        if (requestCode == 99 && resultCode == 34)
        {
            int sub = data.getIntExtra("kq",0);
            edtKQ.setText("Hiệu 2 số là: "+sub);
        }
    }
}