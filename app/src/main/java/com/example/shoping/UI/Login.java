package com.example.shoping.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText input_email , input_password ;
    Button btn_login ;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(Login.this);
        auth = FirebaseAuth.getInstance();

        input_email = findViewById(R.id.input_email);
        input_password =  findViewById(R.id.input_password);

        findViewById(R.id.Creat_Account_in_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RecyclerView.Recycler.class);
                startActivity(intent);
                finish();
            }
        });

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email = input_email.getText().toString();
                String txt_password = input_password.getText().toString();
                //Login(txt_email ,txt_password);
            }
        });

    }
    public void isEmpty(){

    }
    public void getText(long number1 , long number2){
      long sum = number1 + number2;
    }
    public boolean login(String email , String password){

        if(email.isEmpty() || password.isEmpty()){
            Toast.makeText(Login.this,"All filed Are required",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Login.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }else {
                              //  Toast.makeText(Login.this,"Authenticaion failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                  //  Toast.makeText(Login.this,"Error : " + e,Toast.LENGTH_SHORT).show();
                    Log.d("ttt" , "Error : " + e );

                }
            });
            return true;
        }

    }
}
