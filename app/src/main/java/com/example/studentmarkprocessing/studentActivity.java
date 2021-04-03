package com.example.studentmarkprocessing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentmarkprocessing.databinding.ActivityStudentBinding;

public class studentActivity extends AppCompatActivity {

    private ActivityStudentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_student);
        binding = ActivityStudentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent=getIntent();
        String id=intent.getStringExtra("Id");

        binding.viewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(studentActivity.this, studentMarksActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });

        binding.viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(studentActivity.this, profileActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
            }
        });

    }
}