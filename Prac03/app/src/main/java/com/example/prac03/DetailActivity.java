package com.example.prac03;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        ImageView flag = findViewById(R.id.detail_flag);
        TextView name = findViewById(R.id.detail_name);
        TextView capital = findViewById(R.id.detail_capital);
        TextView area = findViewById(R.id.detail_area);
        TextView density = findViewById(R.id.detail_density);
        TextView population = findViewById(R.id.detail_population);
        TextView worldShare = findViewById(R.id.detail_world_share);

        // Lấy dữ liệu từ Intent
        flag.setImageResource(getIntent().getIntExtra("flag", 0));
        name.setText(getIntent().getStringExtra("name"));
        capital.setText("Capital: " + getIntent().getStringExtra("capital"));
        area.setText("Area: " + getIntent().getDoubleExtra("area", 0) + " Km2");
        density.setText("Density: " + getIntent().getDoubleExtra("density", 0));
        population.setText("Population: " + getIntent().getLongExtra("population", 0) + "people");
        worldShare.setText("World Share: " + getIntent().getDoubleExtra("worldShare", 0) + "%");
    }
}
