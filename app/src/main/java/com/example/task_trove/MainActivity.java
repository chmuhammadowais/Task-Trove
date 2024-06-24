package com.example.task_trove;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText email_edt, password_edt;
    Button signup_btn, signin_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email_edt = findViewById(R.id.email_edt);
        password_edt = findViewById(R.id.password_edt);
        signin_btn = findViewById(R.id.signin_btn);
        signup_btn = findViewById(R.id.signup_btn);

        signin_btn.setOnClickListener(v -> {
            if(email_edt.getText().toString().isEmpty() || password_edt.getText().toString().isEmpty()){
                Toast.makeText(this, "Please provide all fields", Toast.LENGTH_SHORT).show();
            }
            else if (!email_edt.getText().toString().contains("@") || !email_edt.getText().toString().endsWith(".com")) {
                Toast.makeText(this, "Please provide a valid email", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Everything's good", Toast.LENGTH_SHORT).show();
                //Perform relevant API call then handle the Intent accordingly
                Intent i = new Intent(this, PrimaryActivity.class);
                startActivity(i);
            }
        });

        signup_btn.setOnClickListener(v -> {
            Intent i = new Intent(this, SignupActivity.class);
            startActivity(i);
        });
    }
}