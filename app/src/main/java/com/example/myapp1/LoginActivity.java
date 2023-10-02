package com.example.myapp1;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.EditText;



public class LoginActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    EditText emailEditText;
    EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        Button btn = findViewById(R.id.login_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String UserEmail = emailEditText.getText().toString().trim();
                String UserPassword = passwordEditText.getText().toString().trim(); // Trim whitespace

                if(UserEmail.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Email is required", LENGTH_SHORT).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(UserEmail).matches()){
                    Toast.makeText(LoginActivity.this, "Enter valid email address", LENGTH_SHORT).show();
                    return;
                }
                if(UserPassword.isEmpty()){
                    Toast.makeText(LoginActivity.this, "Password is required", LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("DefaultEmail",UserEmail);
                editor.apply();

                Intent myInt = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(myInt);
            }
        });

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Read the stored email address and set it as the initial text
        String storedEmail = sharedPreferences.getString("DefaultEmail", "nisargbhalia@gmail.com");
        emailEditText.setText(storedEmail);

    }

    protected void onResume(){
        super.onResume();
        Log.i("running","running onResume in Login Activity");
    }

    protected void onStart(){
        super.onStart();
        Log.i("running","running onStart in Login Activity");

    }
    protected void onPause(){
        super.onPause();
        Log.i("running","running onPause in Login Activity");

    }
    protected void onStop(){
        super.onStop();
        Log.i("running","running onStop in Login Activity");

    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i("running","running onDestroy in Login Activity");

    }


}