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
                if(db.getText().equals("Student") && binding.edtName.getText().toString().length()>0){
                    ref=database.getReference("StudentDetails");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(binding.edtName.getText().toString()).exists()) {
                                if (snapshot.child(binding.edtName.getText().toString()).child("Rollno").getValue(String.class).equals(binding.edtName.getText().toString())) {
                                    if (snapshot.child(binding.edtName.getText().toString()).child("Password").getValue(String.class).equals(binding.edtPassword.getText().toString())) {
                                        binding.edtPassword.setError(null);
                                        Intent intent = new Intent(LoginActivity.this, studentActivity.class);
                                        intent.putExtra("Id", binding.edtName.getText().toString());
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        binding.edtPassword.setError("Password Not Match");
                                    }
                                }
                            }else{binding.edtName.setError("Invalid UserName");}
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else if(db.getText().toString().equals("Staff") && binding.edtName.getText().toString().length()>0){
                    ref=database.getReference("StaffDetails");
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.child(binding.edtName.getText().toString()).exists()) {
                                binding.edtName.setError(null);
                                if (snapshot.child(binding.edtName.getText().toString()).child("Id").getValue(String.class).equals(binding.edtName.getText().toString())) {
                                    if (snapshot.child(binding.edtName.getText().toString()).child("Password").getValue(String.class).equals(binding.edtPassword.getText().toString())) {
                                        binding.edtPassword.setError(null);
                                        Intent intent = new Intent(LoginActivity.this, teacherActivity.class);
                                        intent.putExtra("Id", binding.edtName.getText().toString());
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        binding.edtPassword.setError("Password Not Match");
                                    }
                                }
                            }else{binding.edtName.setError("Invalid UserName");}
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    binding.edtName.setError("Username Invalid");
                }
            }
        });

    }
}