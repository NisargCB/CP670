package com.example.myapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    private ListView chat_List;
    private EditText text_field;
    private Button send_button;
    private ArrayList<String> all_messages;

    private ChatAdapter messageAdapter;
    private static final String ACTIVITY_NAME = "ChatWindow";
    private ChatDatabaseHelper databaseHelper;
    private SQLiteDatabase db;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        chat_List =findViewById(R.id.Chat_List);
        text_field=findViewById(R.id.text_field);
        send_button =findViewById(R.id.send_button);
        all_messages= new ArrayList<>();
        messageAdapter = new ChatAdapter(this,all_messages);
        chat_List.setAdapter(messageAdapter);
        databaseHelper = new ChatDatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

//        String query = "SELECT " + ChatDatabaseHelper.KEY_MESSAGE + " FROM " + ChatDatabaseHelper.TABLE_NAME;
        Cursor cursor = db.rawQuery("SELECT * FROM " + ChatDatabaseHelper.TABLE_NAME, null);


//        if (cursor.moveToFirst()) {
//            do {
//                int columnIndex = cursor.getColumnIndex(ChatDatabaseHelper.KEY_MESSAGE);
//                if (columnIndex != -1) {
//                    String message = cursor.getString(columnIndex);
//                    Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + message);
//                    all_messages.add(message);
//                }
//            } while (cursor.moveToNext());
//        }
        while (cursor.moveToNext()) {
            Log.i(ACTIVITY_NAME, "SQL MESSAGE: " + cursor.getString(1));
            all_messages.add(
                    cursor.getString(1)

            );

            messageAdapter.notifyDataSetChanged();
        }
        Log.i(ACTIVITY_NAME, "Column count of cursor is =" + cursor.getColumnCount());

        for (int i = 0; i < cursor.getColumnCount(); i++) {
            String columnName = cursor.getColumnName(i);
            Log.i(ACTIVITY_NAME, "Column Name " + i + ": " + columnName);
        }
        // Close the cursor and database when done
        cursor.close();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Enable the Up button in the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        send_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(ACTIVITY_NAME,"send button called");

                String message = text_field.getText().toString().trim();

                if(!message.isEmpty())
                {
                    all_messages.add(message);

                    ContentValues values = new ContentValues();
                    values.put(ChatDatabaseHelper.KEY_MESSAGE, message);

                    db.insert(ChatDatabaseHelper.TABLE_NAME, null, values);

                    // Log the new message and its associated database row ID
                    messageAdapter.notifyDataSetChanged();
                    text_field.setText("");
                }

            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Close the database when the activity is destroyed
        if (db != null  && db.isOpen()) {
            db.close();
            Log.i(ACTIVITY_NAME, "Database closed in onDestroy");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // When the Up button is clicked, navigate to the parent activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private class ChatAdapter extends ArrayAdapter<String> {
        private final ArrayList<String> str2;
        private Context context;
        public ChatAdapter(Context ctx,ArrayList<String> str){
            super(ctx,0);
            this.context=ctx;
            this.str2=str;
        }

        public int getCount(){
            return str2.size();
        }
        public String getItem(int position){

            return str2.get(position);
        }

        public View getView(int position,View convertView, ViewGroup parent){
            LayoutInflater inflater=ChatWindow.this.getLayoutInflater();

            View result = null;

            if(position%2==0){
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            }
            else{
                result=inflater.inflate(R.layout.chat_row_outgoing,null);
            }

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }






    }

}

