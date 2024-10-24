package vn.edu.tlu.custom_gridview;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static String[] arrayName = {"Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4", "Ảnh 5", "Ảnh 6", "Ảnh 7", "Ảnh 8", "Ảnh 9", "Ảnh 10", "Ảnh 11", "Ảnh 12"};
    public static int[] imageName = {R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground, R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground};

    GridView gridViewDemo;
    MyarrayAdapter adapterDanhSach;
    ArrayList<Image> arrImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridViewDemo = findViewById(R.id.grid1);
        arrImage = new ArrayList<>();

        for (int i = 0; i < arrayName.length; i++) {
            arrImage.add(new Image(imageName[i], arrayName[i]));
        }

        adapterDanhSach = new MyarrayAdapter(this, R.layout.listitem, arrImage);
        gridViewDemo.setAdapter(adapterDanhSach);

        gridViewDemo.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt("TITLE", position);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }
}
