package com.example.shakhawathhossain.firebase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.VideoView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText email,pass,name;
    Button button;
    String m,p;
    TextView sign,lan;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();

        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        button=(Button) findViewById(R.id.button);
        lan=(TextView)findViewById(R.id.lan);
        sign=(TextView)findViewById(R.id.sign);
        name=(EditText)findViewById(R.id.name);
        videoView=(VideoView)findViewById(R.id.videoView);

        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        String text=getString(R.string.noob);

        SpannableString spannableString=new SpannableString(text);

        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this,SignIn.class));

            }
        };

        spannableString.setSpan(clickableSpan,12,17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        sign.setText(spannableString);
        sign.setMovementMethod(LinkMovementMethod.getInstance());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m=email.getText().toString();
                p=pass.getText().toString();

                if(!(TextUtils.isEmpty(m) || TextUtils.isEmpty(p))) {

                    auth.signInWithEmailAndPassword(m, p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                Intent intent = new Intent ( MainActivity.this, AccountActivity.class );
                                intent.putExtra ( "TextBox", name.getText().toString() );
                                startActivity(intent);


                            }

                        }
                    });

                }

            }
        });

        lan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog=new Dialog();
                dialog.show(getSupportFragmentManager(),"Dialog");

            }
        });

    }

}
