package vn.edu.tlu.ex18;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ActivitySub extends AppCompatActivity {
    TextView txtmaso, txtbaihat, txtloibaihat, txttacgia;
    ImageButton btnthich, btnkhongthich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);

        txtmaso = findViewById(R.id.txtmaso);
        txtbaihat = findViewById(R.id.txtbaihat);
        txtloibaihat = findViewById(R.id.txtloibaihat);
        txttacgia = findViewById(R.id.txttacgia);
        btnthich = findViewById(R.id.btnthich);
        btnkhongthich = findViewById(R.id.btnkhongthich);

        Intent callerIntent1 = getIntent();
        Bundle backagecaller1 = callerIntent1.getBundleExtra("package");
        String maso = backagecaller1.getString("maso");

        Cursor c = MainActivity.database.rawQuery("SELECT * FROM ArirangSongList WHERE MABH LIKE'" + maso + "'", null);
        txtmaso.setText(maso);
        c.moveToFirst();
        txtbaihat.setText(c.getString(2));
        txtloibaihat.setText(c.getString(3));
        txttacgia.setText(c.getString(4));

        if (c.getInt(6) == 0) {
            btnthich.setVisibility(View.INVISIBLE);
            btnkhongthich.setVisibility(View.VISIBLE);
        } else {
            btnkhongthich.setVisibility(View.INVISIBLE);
            btnthich.setVisibility(View.VISIBLE);
        }
        c.close();

        btnthich.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                MainActivity.database.update("ArirangSongList", values,
                        "MABH=?", new String[]{txtmaso.getText().toString()});
                btnthich.setVisibility(View.INVISIBLE);
                btnkhongthich.setVisibility(View.VISIBLE);
            }
        });

        btnkhongthich.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                MainActivity.database.update("ArirangSongList", values,
                        "MABH=?", new String[]{txtmaso.getText().toString()});
                btnkhongthich.setVisibility(View.INVISIBLE);
                btnthich.setVisibility(View.VISIBLE);
            }
        });
    }
}
