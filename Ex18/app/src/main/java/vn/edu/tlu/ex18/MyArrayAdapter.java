package vn.edu.tlu.ex18;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    Activity context;
    ArrayList<Item> myArray;
    int layoutId;

    public MyArrayAdapter(Activity context, int layoutId, ArrayList<Item> arr) {
        super(context, layoutId, arr);
        this.context = context;
        this.layoutId = layoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        final Item myItem = myArray.get(position);
        final TextView tieude = convertView.findViewById(R.id.txttieude);
        tieude.setText(myItem.getTieude());
        final TextView maso = convertView.findViewById(R.id.txtmaso);
        maso.setText(myItem.getMaso());
        final ImageView btnlike = convertView.findViewById(R.id.btnlike);
        final ImageView btnunlike = convertView.findViewById(R.id.btnunlike);
        int thich = myItem.getThich();

        if (thich == 0) {
            btnlike.setVisibility(View.INVISIBLE);
            btnunlike.setVisibility(View.VISIBLE);
        } else {
            btnunlike.setVisibility(View.INVISIBLE);
            btnlike.setVisibility(View.VISIBLE);
        }

        btnlike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                MainActivity.database.update("ArirangSongList", values,
                        "MABH=?", new String[]{maso.getText().toString()});
                btnlike.setVisibility(View.INVISIBLE);
                btnunlike.setVisibility(View.VISIBLE);
            }
        });

        btnunlike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                MainActivity.database.update("ArirangSongList", values,
                        "MABH=?", new String[]{maso.getText().toString()});
                btnunlike.setVisibility(View.INVISIBLE);
                btnlike.setVisibility(View.VISIBLE);
            }
        });

        tieude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tieude.setTextColor(Color.RED);
                maso.setTextColor(Color.RED);
                Intent intent1 = new Intent(context, ActivitySub.class);
                Bundle bundle1 = new Bundle();
                bundle1.putString("maso", maso.getText().toString());
                intent1.putExtra("package", bundle1);
                context.startActivity(intent1);
            }
        });

        return convertView;
    }
}

