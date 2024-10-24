package vn.edu.tlu.ex11;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnOpen;
    EditText edtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLink = findViewById(R.id.edtlink);
        btnOpen = findViewById(R.id.btnopen);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = edtLink.getText().toString().trim();
                if (!url.isEmpty()) {
                    // Chuyển đổi URL thành dạng hoàn chỉnh
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://" + url));
                    intent.putExtra("url", url); // Truyền URL cho Activity thứ hai
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập URL", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
