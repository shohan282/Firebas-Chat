package com.example.shakhawathhossain.firebase;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    FirebaseAuth auth;
    EditText email,pass;
    Button button;
    String m,p;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth=FirebaseAuth.getInstance();

        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.pass);
        button=(Button) findViewById(R.id.button);
        videoView=(VideoView)findViewById(R.id.videoView);

        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.v);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m=email.getText().toString();
                p=pass.getText().toString();

                if(!(TextUtils.isEmpty(m) || TextUtils.isEmpty(p))) {

                    auth.createUserWithEmailAndPassword(m,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                startActivity(new Intent(SignIn.this,MainActivity.class));

                            }

                        }
                    });

                }

            }
        });

    }
}
