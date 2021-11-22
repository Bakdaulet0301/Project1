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


public class Reg_Activity extends AppCompatActivity {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://appreglog-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        TextView btn=findViewById(R.id.alreadyHaveAccount);

        final EditText name=findViewById(R.id.inputPersonName);
        final EditText email=findViewById(R.id.inputEmail);
        final  EditText password=findViewById(R.id.inputPasswor);
        final  EditText confirmpassword=findViewById(R.id.inputConformpassword);
        final Button register=findViewById(R.id.btnRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String namestxt=name.getText().toString();
                final String emailstxt =email.getText().toString();
                final String passwordtxt=password.getText().toString();
                final String confirmpasstxt=confirmpassword.getText().toString();


                if(namestxt.isEmpty() || emailstxt.isEmpty() || passwordtxt.isEmpty() || confirmpasstxt.isEmpty()){
                    Toast.makeText(Reg_Activity.this,"Please fill all fields",Toast.LENGTH_SHORT).show();
                }
                else if (!passwordtxt.equals(confirmpasstxt)){
                    Toast.makeText(Reg_Activity.this,"Password are not matching",Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(namestxt)){
                                Toast.makeText(Reg_Activity.this,"Name is already registered",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("users").child(namestxt).child("email").setValue(emailstxt);
                                databaseReference.child("users").child(namestxt).child("password").setValue(passwordtxt);

                                Toast.makeText(Reg_Activity.this,"User registered successfully.",Toast.LENGTH_SHORT).show();
                                finish();

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
                startActivity(new Intent(Reg_Activity.this,Login_Activity.class));
            }
        });
    }
}