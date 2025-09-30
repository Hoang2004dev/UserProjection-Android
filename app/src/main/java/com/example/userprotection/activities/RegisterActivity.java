package com.example.userprotection.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userprotection.R;
import com.example.userprotection.models.RegisterRequest;
import com.example.userprotection.network.ApiClient;
import com.example.userprotection.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPassword;
    private Button btnRegister, btnGoLogin;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName  = findViewById(R.id.etLastName);
        etEmail     = findViewById(R.id.etEmail);
        etPassword  = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        btnGoLogin  = findViewById(R.id.btnGoLogin);

        apiService = ApiClient.getClient().create(ApiService.class);

        btnRegister.setOnClickListener(v -> registerUser());
        btnGoLogin.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String firstName = etFirstName.getText().toString().trim();
        String lastName  = etLastName.getText().toString().trim();
        String email     = etEmail.getText().toString().trim();
        String password  = etPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email và mật khẩu là bắt buộc", Toast.LENGTH_SHORT).show();
            return;
        }

        // Nếu muốn user mặc định KHÔNG thuộc tenant nào → tenantId = null
        RegisterRequest request = new RegisterRequest(null, firstName, lastName, email, password);

        apiService.register(request).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this,
                            "Đăng ký thất bại: " + response.message(),
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this,
                        "Lỗi kết nối: " + t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}
