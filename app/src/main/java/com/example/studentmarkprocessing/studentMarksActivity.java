package com.example.studentmarkprocessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentmarkprocessing.databinding.ActivityStudentBinding;
import com.example.studentmarkprocessing.databinding.ActivityStudentMarksBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class studentMarksActivity extends AppCompatActivity {

    private ActivityStudentMarksBinding binding;

    private FirebaseDatabase db;
    private DatabaseReference ref;
    private TextView[] gpas;


    //private EditText gpa1,gpa2,gpa3,gpa4,gpa5,gpa6,gpa7,gpa8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentMarksBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db=FirebaseDatabase.getInstance();
        ref=db.getReference("StudentDetails");

        gpas = new TextView[8];
        gpas[0]=binding.gpa1;
        gpas[1]=binding.gpa2;
        gpas[2]=binding.gpa3;
        gpas[3]=binding.gpa4;
        gpas[4]=binding.gpa5;
        gpas[5]=binding.gpa6;
        gpas[6]=binding.gpa7;
        gpas[7]=binding.gpa8;


        Intent i=getIntent();
        binding.rollNo.setText(i.getStringExtra("Id"));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name=snapshot.child(binding.rollNo.getText().toString()).child("Name").getValue(String.class);
                String dept=snapshot.child(binding.rollNo.getText().toString()).child("Department").getValue(String.class);
                binding.dept.setText(dept);
                binding.name.setText(Name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(int i=0;i<8;i++){
                            //gpas[i]=findViewById(R.id.("gpa"+String.valueOf(i)))
                            //Toast.makeText(studentMarksActivity.this, name, Toast.LENGTH_SHORT).show();
                            String val=snapshot.child(binding.rollNo.getText().toString()).child("Semester").child(String.valueOf(i+1)).getValue(String.class);
                            //Toast.makeText(studentMarksActivity.this, val, Toast.LENGTH_SHORT).show();
                            gpas[i].setText(val);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}