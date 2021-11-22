package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login_Activity extends AppCompatActivity {

    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://appreglog-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn=findViewById(R.id.textViewSignUp);
        final EditText password=findViewById(R.id.inputPassword);
        final EditText name=findViewById(R.id.inputPersonName);
        final Button btnlog=findViewById(R.id.btnlogin);

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nametxt=name.getText().toString();
                final String passwordtxt=password.getText().toString();

                if (nametxt.isEmpty() || passwordtxt.isEmpty()){
                    Toast.makeText(Login_Activity.this,"please enter your name or password",Toast.LENGTH_SHORT).show();

                }
                else {
                        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(nametxt)){
                                    final String getPassword=snapshot.child(nametxt).child("password").getValue(String.class);
                                    if (getPassword.equals(passwordtxt)){
                                        Toast.makeText(Login_Activity.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Login_Activity.this,Home_Activity.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Login_Activity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                                    }

                                }
                                else {
                                    Toast.makeText(Login_Activity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Activity.this,Reg_Activity.class));
            }
        });

    }
}