package com.example.shoping.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoping.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
   public EditText input_email , input_password , input_name;
    FirebaseAuth auth;
    DatabaseReference reference;
    Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        input_name = findViewById(R.id.input_name);
        btn_signup = findViewById(R.id.btn_signup);

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_username = input_name.getText().toString().trim();
                String txt_email = input_email.getText().toString().trim();
                String txt_password = input_password.getText().toString().trim();
                if (txt_email.isEmpty() || txt_password.isEmpty() || txt_username.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "All fileds are required",Toast.LENGTH_SHORT).show();
                }else if(txt_password.length() < 6){
                    Toast.makeText(RegisterActivity.this, "password must be at least 6 chaeracters",Toast.LENGTH_SHORT).show();
                }else {
                    register(txt_username,txt_email,txt_password);
                }

            }
        });

        }

    private  void register(final String username , String email  , String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            HashMap<String , String > hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("username",username);hashMap.put("imageURL","default");
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        }else {
                            Log.d("ttt", "TASK : "+ task.getException().getMessage());
                            Toast.makeText(RegisterActivity.this,"You Can't Register with this email and password",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }



}
