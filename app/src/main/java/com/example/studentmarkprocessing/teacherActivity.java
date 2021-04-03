package com.example.studentmarkprocessing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentmarkprocessing.databinding.ActivityStudentBinding;
import com.example.studentmarkprocessing.databinding.ActivityTeacherBinding;

public class teacherActivity extends AppCompatActivity {
    CardView update;

    private ActivityTeacherBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityTeacherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        String id=getIntent().getStringExtra("Id");
        binding.viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(teacherActivity.this,UpdateStudentMark.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });
    }
}