package com.example.myapp1;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapp1.databinding.ActivityTestToolbarBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class TestToolbar extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private String new_message="You selected option 1";
    private ActivityTestToolbarBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Yooooooo", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        int id=mi.getItemId();

        if (id == R.id.action_one) {
            Log.d("Toolbar", "Option 1 selected");
            Snackbar.make(findViewById(R.id.fab), new_message ,Snackbar.LENGTH_SHORT).setAction("Action",null).show();
            return true;
        } else if (id == R.id.action_two) {
            Log.d("Toolbar","Option 2 selected");
            showAlertDialog();
            return true;
        } else if (id == R.id.action_three) {
            Log.d("Toolbar","Option 3 selected");
            showCustomDialog();
            Snackbar.make(findViewById(R.id.fab),"You selected option 3",Snackbar.LENGTH_SHORT).setAction("Action",null).show();
            return true;
        } else if (id == R.id.about) {
            // Handle the action for the "About" menu item.
            showAboutDialog();
            return true;
        } else {
            return super.onOptionsItemSelected(mi);
        }
}
    private void showAboutDialog() {
        String version = getResources().getString(R.string.ver);
        String author = getResources().getString(R.string.my_name); // Replace with your actual name
        String aboutMessage = version + ", by " + author;
        Toast.makeText(this, aboutMessage, Toast.LENGTH_SHORT).show();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);

        // Positive (OK)
        builder.setPositiveButton(R.string.dialog_positive_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // clicked OK, finish current activity
                finish();
            }
        });

        // Negative (Cancel)
        builder.setNegativeButton(R.string.dialog_negative_button, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // cancelled, do nothing
                dialog.dismiss();
            }
        });

        // Create AlertDialog
        AlertDialog dialog = builder.create();

        // Show  AlertDialog
        dialog.show();
    }

    private void showCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        EditText editTextMessage = dialogView.findViewById(R.id.editTextMessage);

        builder.setView(dialogView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        new_message = editTextMessage.getText().toString();
                        showSnackbar(new_message);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        builder.create().show();
    }

    private void showSnackbar(String message) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show();
    }

}