package com.example.th3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView tv_bug;
    public RecyclerView recyclerView;
    public StudentAdapter studentAdapter;
    public List<Student> studentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_bug = findViewById(R.id.tv_bug);

        ImageButton btn_add = findViewById(R.id.btn_add_student);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_add_student.class);
                startActivityForResult(intent, 1);
            }
        });


        String jsonString = loadJSONFromAsset();


        Gson gson = new Gson();
        Type studentListType = new TypeToken<List<Student>>(){}.getType();
        studentList = gson.fromJson(jsonString, studentListType);



        try {
            recyclerView = findViewById(R.id.recyclerView);
            studentAdapter = new StudentAdapter(studentList);
            recyclerView.setAdapter(studentAdapter);


        }
        catch (Exception e) {
            tv_bug.setText(e.toString());

        }


    }

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1 && resultCode == RESULT_OK) {
            Student newStudent = (Student) data.getSerializableExtra("new_student");
            if (newStudent != null) {
                studentList.add(newStudent);
                studentAdapter.notifyItemInserted(studentList.size() - 1);
            }
        }


        else if (requestCode == 2 && resultCode == RESULT_OK) {
            String studentJson = data.getStringExtra("edited_student");
            int position = data.getIntExtra("position", -1);
            if (studentJson != null && position != -1) {
                Student editedStudent = new Gson().fromJson(studentJson, Student.class);
                studentList.set(position, editedStudent);
                studentAdapter.notifyItemChanged(position);
            }
        }
    }

}
