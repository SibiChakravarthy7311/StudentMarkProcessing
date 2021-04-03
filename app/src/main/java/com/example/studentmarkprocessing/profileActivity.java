package com.example.studentmarkprocessing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.studentmarkprocessing.databinding.ActivityProfileBinding;
import com.example.studentmarkprocessing.databinding.ActivityStudentMarksBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private FirebaseDatabase db;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        String id=getIntent().getStringExtra("Id");
        db=FirebaseDatabase.getInstance();
        ref=db.getReference("StudentDetails").child(id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.name.setText(snapshot.child("Name").getValue().toString());
                binding.phone.setText(snapshot.child("Personal").child("Phonenumber").getValue().toString());
                binding.address.setText(snapshot.child("Personal").child("Address").getValue().toString());
                binding.DOB.setText(snapshot.child("Personal").child("DOB").getValue().toString());
                binding.email.setText(snapshot.child("Personal").child("Email").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}