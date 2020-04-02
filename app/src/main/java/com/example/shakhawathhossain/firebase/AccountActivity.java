package com.example.shakhawathhossain.firebase;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.VideoView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity {

    ListView listView;
    Button button;
    EditText editText;
    ArrayList<String> user=new ArrayList<>();
    Firebase firebase,f2;
    String text;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        listView=(ListView)findViewById(R.id.list);
        button=(Button)findViewById(R.id.go);
        editText=(EditText)findViewById(R.id.text);
        videoView=(VideoView)findViewById(R.id.videoView);

        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        firebase=new Firebase("https://myapplication-65b2e.firebaseio.com/Users");
        f2=new Firebase("https://myapplication-65b2e.firebaseio.com/Users/Name");

        final ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,user);
        listView.setAdapter(arrayAdapter);

        Intent i = getIntent();
        text = i.getStringExtra ( "TextBox" );
        editText.setText ( text+": " );
        editText.setSelection(editText.getText().length());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Firebase get = f2.child("");

                String value = editText.getText().toString();

                get.setValue(value);

                editText.setText ( text+": " );
                editText.setSelection(editText.getText().length());

            }
        });

        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String val = dataSnapshot.getValue(String.class);
                user.add(val);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                String val = dataSnapshot.getValue(String.class);
                user.add(val);
                arrayAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



    }

    @Override
    protected void onStop() {
        super.onStop();
        Firebase get=f2.child("");
        get.setValue(null);
    }
}
