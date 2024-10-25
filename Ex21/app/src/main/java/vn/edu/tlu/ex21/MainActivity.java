package vn.edu.tlu.ex21;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnparse;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnparse = findViewById(R.id.btnparse);
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsejson();
            }
        });
    }

    private void parsejson() {
        String json = null;
        try {
            InputStream inputStream = getAssets().open("computer.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            JSONObject reader = new JSONObject(json);
            mylist.add("MaDM: " + reader.getString("MaDM"));
            mylist.add("TenDM: " + reader.getString("TenDM"));

            JSONArray myarray = reader.getJSONArray("Sanphams");
            for (int i = 0; i < myarray.length(); i++) {
                JSONObject myobj = myarray.getJSONObject(i);
                mylist.add("MaSP: " + myobj.getString("MaSP") + " - TenSP: " + myobj.getString("TenSP"));
                mylist.add("SoLuong: " + myobj.getString("SoLuong") + " * DonGia: " + myobj.getString("DonGia") + " = ThanhTien: " + myobj.getString("ThanhTien"));
                mylist.add("Hinh: " + myobj.getString("Hinh"));
            }
            myadapter.notifyDataSetChanged();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}