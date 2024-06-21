package com.example.task_trove;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignupActivity extends AppCompatActivity {
    EditText name_edt, phone_edt, email_edt, password_edt;
    Button signup_btn;
    FloatingActionButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name_edt = findViewById(R.id.name_edt);
        phone_edt = findViewById(R.id.phone_edt);
        email_edt = findViewById(R.id.email_edt);
        password_edt = findViewById(R.id.password_edt);
        signup_btn = findViewById(R.id.signup_btn);
        back = findViewById(R.id.back);

        signup_btn.setOnClickListener(v -> {
            if(name_edt.getText().toString().isEmpty() || phone_edt.getText().toString().isEmpty() || email_edt.getText().toString().isEmpty() || password_edt.getText().toString().isEmpty()){
                Toast.makeText(this, "Please provide all fields", Toast.LENGTH_SHORT).show();
            } else if (!email_edt.getText().toString().contains("@") || !email_edt.getText().toString().endsWith(".com")) {
                Toast.makeText(this, "Please provide a valid email", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Everything's good", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(v -> {
            finish();
        });
    }
}