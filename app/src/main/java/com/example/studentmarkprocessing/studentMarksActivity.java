package com.example.studentmarkprocessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
    private EditText rollno,cgpa,name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStudentMarksBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        db=FirebaseDatabase.getInstance();
        ref=db.getReference("StudentDetails");

        rollno=(EditText)findViewById(R.id.roll_no);
        cgpa=(EditText)findViewById(R.id.cgpa);
        name=(EditText)findViewById(R.id.name);

        rollno.setText("18BCS013");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String Name=snapshot.child(rollno.getText().toString()).child("Name").getValue(String.class);
                String CGPA=snapshot.child(rollno.getText().toString()).child("CGPA").getValue(String.class);
                cgpa.setText(CGPA);
                name.setText(Name);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}