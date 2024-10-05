package com.example.prac03;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.blongho.country_data.Country;
import com.blongho.country_data.World;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        World.init(this);
        List<Country> countries = World.getAllCountries();
        RecyclerView recyclerView = findViewById(R.id.recycler);

        Adapter adapter = new Adapter(MainActivity.this, countries);
        recyclerView.setAdapter(adapter);

    }
}