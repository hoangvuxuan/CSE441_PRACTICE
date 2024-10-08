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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView tv_bug;
    public RecyclerView recyclerView;
    public StudentAdapter studentAdapter;
    public List<Student> studentList;
    public boolean isAscending = true;


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

        TextView tv_sort = findViewById(R.id.tv_sort); // Đảm bảo ID này đúng với XML
        tv_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortStudentList();
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

    private void sortStudentList() {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                if (isAscending) {
                    return s1.getFull_name().getFirst().compareTo(s2.getFull_name().getFirst());
                } else {
                    return s2.getFull_name().getFirst().compareTo(s1.getFull_name().getFirst());
                }
            }
        });
        studentAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
        isAscending = !isAscending; // Đảo ngược thứ tự
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
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            boolean isDeleted = data.getBooleanExtra("delete_student", false);
            int position = data.getIntExtra("position", -1);

            if (isDeleted && position != -1) {
                // Xoá sinh viên khỏi danh sách
                studentList.remove(position);
                studentAdapter.notifyItemRemoved(position);
            } else {
                // Cập nhật sinh viên
                String studentJson = data.getStringExtra("edited_student");
                if (studentJson != null && position != -1) {
                    Student editedStudent = new Gson().fromJson(studentJson, Student.class);
                    studentList.set(position, editedStudent);
                    studentAdapter.notifyItemChanged(position);
                }
            }
        }
    }


}
