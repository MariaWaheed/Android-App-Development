package com.myjobassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilePage extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userId;
    Button update,reload,back;
    TextView firstname,lastname,email,dob,domicile,phone,country,city,residential,postal,gender,cnic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_profile_page);
        firstname= findViewById(R.id.firstname);
        lastname= findViewById(R.id.lastname);
        email= findViewById(R.id.viewemail);
        dob= findViewById(R.id.dateofbirth);
        domicile= findViewById(R.id.viewdomicile);
        phone= findViewById(R.id.viewcell);
        country= findViewById(R.id.viewcountry);
        city= findViewById(R.id.viewcity);
        residential= findViewById(R.id.viewresidential);
        postal= findViewById(R.id.viewpostal);
        gender= findViewById(R.id.viewgender);
        cnic= findViewById(R.id.viewcnic);
        reload= findViewById(R.id.buttonreload);
        update= findViewById(R.id.buttonupdate);
        back=findViewById(R.id.backtohome);
        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        userId=user.getUid();
        mRef=mFirebaseDatabase.getReference().child(userId);


        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PersonalRecord record=snapshot.child("PersonalRecord").getValue(PersonalRecord.class);
                        if(record!=null){
                            show(record);
                        }else{
                            Toast.makeText(ProfilePage.this, "No record exist", Toast.LENGTH_SHORT).show();
                            firstname.setText(" ");
                            lastname.setText(" ");
                            email.setText(" ");
                            country.setText(" ");
                            city.setText(" ");
                            domicile.setText(" ");
                            dob.setText(" ");
                            postal.setText(" ");
                            residential.setText(" ");
                            gender.setText(" ");
                            phone.setText(" ");
                            cnic.setText(" ");
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(ProfilePage.this, "Not Found", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ProfilePage.this,UpdateInformation.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(ProfilePage.this,home.class);
                startActivity(i);
            }
        });
    }

    public void show(PersonalRecord userrecord){
            firstname.setText(userrecord.getFname());
            lastname.setText(userrecord.getLast());
            email.setText(userrecord.getEmail());
            country.setText(userrecord.getCountry());
            city.setText(userrecord.getCity());
            domicile.setText(userrecord.getDomicile());
            dob.setText(userrecord.getDate());
            postal.setText(userrecord.getPostal());
            residential.setText(userrecord.getAddress());
            gender.setText(userrecord.getGender());
            phone.setText(userrecord.getCell());
            cnic.setText(userrecord.getCnic());
    }
}