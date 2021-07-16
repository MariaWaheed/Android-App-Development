package com.myjobassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDomain extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton selected;
    Button next,backbtn,savedomain;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    String selected_domain="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_domain);
        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();

        radioGroup= findViewById(R.id.group);
        savedomain= findViewById(R.id.save);
        backbtn= findViewById(R.id.back4);
        next= findViewById(R.id.nexttodomainProfile);

        savedomain.setEnabled(false);
        next.setEnabled(false);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                savedomain.setEnabled(true);
                next.setEnabled(true);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddDomain.this, home.class);
                startActivity(i);
            }
        });

        savedomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId=radioGroup.getCheckedRadioButtonId();
                selected=(RadioButton)findViewById(selectedId);
                selected_domain=selected.getText().toString();
                Domain domain= new Domain(selected_domain);
                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid);
                mDatabase.child("Domain").setValue(domain);
                Toast.makeText(AddDomain.this, "Domain is added successfully!", Toast.LENGTH_SHORT).show();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    int selectedId=radioGroup.getCheckedRadioButtonId();
                    selected=(RadioButton)findViewById(selectedId);
                    selected_domain=selected.getText().toString();
                    Intent i = new Intent(AddDomain.this, DomainProfile.class);
                    i.putExtra("domain", selected_domain);
                    startActivity(i);
                }
        });

    }
}