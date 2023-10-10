package com.example.myapp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    private ListView chat_List;
    private EditText text_field;
    private Button send_button;

    private ArrayList<String> all_messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        chat_List =findViewById(R.id.Chat_List);
        text_field=findViewById(R.id.text_field);
        send_button =findViewById(R.id.send_button);
        all_messages= new ArrayList<>();

        ChatAdapter messageAdapter = new ChatAdapter(this,all_messages);
        chat_List.setAdapter(messageAdapter);

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
                String message = text_field.getText().toString().trim();

                if(!message.isEmpty())
                {
                    all_messages.add(message);
                    messageAdapter.notifyDataSetChanged();
                    text_field.setText("");
                }

            }
        });
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
//



    }

}

