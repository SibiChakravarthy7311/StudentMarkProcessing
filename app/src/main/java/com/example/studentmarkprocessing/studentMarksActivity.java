package com.example.studentmarkprocessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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
    private FirebaseDatabase student;
    private DatabaseReference ref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentMarksBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_student_marks);

        /*
        String name1;

        student = FirebaseDatabase.getInstance();
        ref = student.getReference();
        /*
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                //String name1 = snapshot.child("18BCS001").child("Name").getValue(String.class);
                //binding.name.setText(name1);
                Post post = snapshot.getValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });


        Query query = ref.child("StudentDetails");
        //Query query = ref.child("Name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(getApplicationContext(), "Powerrr!!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Directed by Robert.B.Weide", Toast.LENGTH_SHORT).show();
            }
        });

         */

        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ref1 = FirebaseDatabase.getInstance().getReference().child("StudentDetails").child("18BCS001");
                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String name1 =snapshot.child("Name").getValue().toString();
                        String cgpa1 =snapshot.child("CGPA").getValue().toString();
                        String sem =snapshot.child("Semester").getValue().toString();
                        binding.name.setText(name1);
                        binding.semester.setText(sem);
                        binding.cgpa.setText(cgpa1);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}