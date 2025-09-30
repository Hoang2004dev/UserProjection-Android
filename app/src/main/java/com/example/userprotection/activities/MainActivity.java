package com.example.userprotection.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userprotection.R;
import com.example.userprotection.utils.JwtUtils;
import com.example.userprotection.utils.SessionManager;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnLogout;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);
        sessionManager = new SessionManager(this);

        String token = sessionManager.getToken();
        if (token == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        // Dùng JwtUtils để lấy email
        String email = JwtUtils.getEmail(token);
        if (email != null) {
            tvWelcome.setText("Welcome, " + email);
        } else {
            tvWelcome.setText("Welcome! (Token invalid)");
        }

        btnLogout.setOnClickListener(v -> {
            sessionManager.clearSession();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }
}
