package com.example.th3;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private List<Student> studentList;

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item, parent, false);
        return new StudentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = studentList.get(position);
        String fullName = student.getFull_name().getFirst() + " " +
                student.getFull_name().getMidd() + " " +
                student.getFull_name().getLast();
        holder.tv_id.setText(String.valueOf(student.getId()));
        holder.tv_name.setText(fullName);
        holder.tv_gpa.setText("GPA: " + student.getGpa());

        if (student.getGender().equalsIgnoreCase("Male")) {
            holder.ib_gender.setImageResource(R.drawable.male);
        } else {
            holder.ib_gender.setImageResource(R.drawable.female);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), EditActivity.class);
                intent.putExtra("student_data", new Gson().toJson(student));
                intent.putExtra("position", holder.getAdapterPosition());
                ((MainActivity)holder.itemView.getContext()).startActivityForResult(intent, 2);
            }
        });
    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    // Lớp ViewHolder để ánh xạ các thành phần giao diện
    public static class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_gpa, tv_id;
        public ImageButton ib_gender;
        public StudentViewHolder(View view) {
            super(view);
            tv_id = view.findViewById(R.id.tv_id);
            tv_name = view.findViewById(R.id.tv_name);
            tv_gpa = view.findViewById(R.id.tv_gpa);
            ib_gender = view.findViewById(R.id.ib_gender);

        }
    }
}
