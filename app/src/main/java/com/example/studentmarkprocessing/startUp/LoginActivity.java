package com.example.studentmarkprocessing.startUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentmarkprocessing.MainActivity;
import com.example.studentmarkprocessing.R;
import com.example.studentmarkprocessing.databinding.ActivityLoginBinding;
import com.example.studentmarkprocessing.studentActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //setContentView(R.layout.activity_login);

        binding.btnSignUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, studentActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}