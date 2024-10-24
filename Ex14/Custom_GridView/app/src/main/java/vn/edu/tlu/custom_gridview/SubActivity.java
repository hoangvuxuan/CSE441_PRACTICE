package vn.edu.tlu.custom_gridview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SubActivity extends Activity {
    TextView txtName;
    ImageView imgView;
    Bundle extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        txtName = findViewById(R.id.textView2);
        imgView = findViewById(R.id.imageView2);

        extra = getIntent().getExtras();
        int position = extra.getInt("TITLE");

        txtName.setText(MainActivity.arrayName[position]);
        imgView.setImageResource(MainActivity.imageName[position]);
    }
}
