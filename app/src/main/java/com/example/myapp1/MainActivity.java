package com.example.myapp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10;
    private Button btn;
    private Button btn2;
    private Button start_chat;
    private Button test_toolbar;

    private Button weather_forecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button2);
        btn2 = (Button) findViewById(R.id.button4);
        start_chat = (Button) findViewById(R.id.start_chat);
        test_toolbar=(Button)findViewById(R.id.test_toolbar_button);
        weather_forecast=(Button)findViewById(R.id.weather_forecast_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent myInt = new Intent(getApplicationContext(),ListItemsActivity.class);
            startActivityForResult(myInt,REQUEST_CODE);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt = new Intent(getApplicationContext(),LoginActivity.class);
                startActivityForResult(myInt,REQUEST_CODE);
            }
            });

        start_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            Log.i("Main Activity","User clicked Start Chat");

            Intent myInt = new Intent(getApplicationContext(),ChatWindow.class);
            startActivity(myInt);
            }}
        );

        test_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Main Activity", "User Clicked TestToolbar");
                Intent intent=new Intent(MainActivity.this, TestToolbar.class);
                startActivity(intent);
//
            }


        });

        weather_forecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, WeatherForecast.class);
                startActivity(intent);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            // Check if the result is from ListItemsActivity and it was accepted

            // Retrieve the string data passed from ListItemsActivity
            String messagePassed = data.getStringExtra("Response");

            // Display a Toast message with the retrieved data
            String toastMessage = "ListItemsActivity passed: " + messagePassed;
            Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
        }
    }

    protected void onResume(){
        super.onResume();
        Log.i("running","running onResume in Main Activity");
    }

    protected void onStart(){
        super.onStart();
        Log.i("running","running onStart in Main Activity");

    }
    protected void onPause(){
        super.onPause();
        Log.i("running","running onPause in Main Activity");

    }
    protected void onStop(){
        super.onStop();
        Log.i("running","running onStop in Main Activity");

    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i("running","running onDestroy in Main Activity");

    }




}