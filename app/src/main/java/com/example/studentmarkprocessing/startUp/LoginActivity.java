package com.example.studentmarkprocessing.startUp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.studentmarkprocessing.MainActivity;
import com.example.studentmarkprocessing.R;
import com.example.studentmarkprocessing.databinding.ActivityLoginBinding;
import com.example.studentmarkprocessing.studentActivity;
import com.example.studentmarkprocessing.teacherActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private FirebaseDatabase database;
    private DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_login);

        database=FirebaseDatabase.getInstance();


        binding.btnSignUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=binding.selection.getCheckedRadioButtonId();
                RadioButton db=findViewById(id);
                Toast.makeText(LoginActivity.this, db.getText(), Toast.LENGTH_SHORT).show();
                if(db.getText().equals("Student")){
                    ref=database.getReference("StudentDetails");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Toast.makeText(LoginActivity.this, snapshot.child(binding.edtName.getText().toString()).child("Rollno").getValue(String.class), Toast.LENGTH_SHORT).show();
                            if(snapshot.child(binding.edtName.getText().toString()).child("Rollno").getValue(String.class).equals(binding.edtName.getText().toString())) {
                                if(snapshot.child(binding.edtName.getText().toString()).child("Password").getValue(String.class).equals(binding.edtPassword.getText().toString()))
                                {
                                    Intent intent = new Intent(LoginActivity.this, studentActivity.class);
                                    intent.putExtra("Id",binding.edtName.getText().toString());
                                    startActivity(intent);
                                    finish();
                                }else{binding.edtPassword.setError("Password Not Match");}
                            }else{binding.edtName.setError("Username Not Found");}
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    ref=database.getReference("StaffDetails");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child(binding.edtName.getText().toString()).equals(binding.edtName.getText().toString())) {
                                if(snapshot.child(binding.edtName.getText().toString()).child("Password").equals(binding.edtPassword.getText().toString()))
                                {
                                    Intent intent = new Intent(LoginActivity.this, teacherActivity.class);
                                    intent.putExtra("Id",binding.edtName.getText().toString());
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}