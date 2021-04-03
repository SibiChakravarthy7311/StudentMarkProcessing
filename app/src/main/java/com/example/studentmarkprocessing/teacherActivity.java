package com.example.studentmarkprocessing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class teacherActivity extends AppCompatActivity {
    CardView update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        update = (CardView) findViewById(R.id.updateMark);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(teacherActivity.this,UpdateStudentMark.class);
                startActivity(intent);
            }
        });
    }
}