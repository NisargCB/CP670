package com.example.myapp1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ComputableLiveData;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        print("String test");
        setContentView(R.layout.activity_list_items);
        Switch switchExample = findViewById(R.id.switch1);

        imageButton=findViewById(R.id.imageButton2);
        CheckBox checkBox = findViewById(R.id.checkBox);
        Button btn = findViewById(R.id.Logout_button);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    showFinishDialog();
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(myInt);
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        switchExample.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String message = isChecked ? "Switch is On" : "Switch is Off";
                int duration = isChecked ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG;

                Toast.makeText(ListItemsActivity.this, message, duration).show();
            }
        });

        Button btn2 = findViewById(R.id.home_button);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInt = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(myInt);
            }
        });

    }

    private void showFinishDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
        builder.setMessage(R.string.dialog_message) // Add a dialog message to strings.xml
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("Response", "My information to share");

                        setResult(Activity.RESULT_OK,resultIntent);
                    finish();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        // User cancelled the dialog
                        dialog.dismiss(); // Dismiss the dialog if Cancel is clicked
                    }
                }).show();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent("android.media.action.IMAGE_CAPTURE");
          startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Get the captured image from the data
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                // Set the captured image as the ImageButton's background
                imageButton.setImageBitmap(imageBitmap);
            }
        }
    }
    protected void onResume(){
        super.onResume();
        Log.i("running","running onResume in ListItems Activity");
    }

    protected void onStart(){
        super.onStart();
        Log.i("running","running onStart in ListItems Activity");

    }
    protected void onPause(){
        super.onPause();
        Log.i("running","running onPause in ListItems Activity");

    }
    protected void onStop(){
        super.onStop();
        Log.i("running","running onStop in ListItems Activity");

    }
    protected void onDestroy(){
        super.onDestroy();
        Log.i("running","running onDestroy in ListItems Activity");

    }
    private void print(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}