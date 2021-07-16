package com.myjobassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_SHORT;

public class JobAccountDetail extends AppCompatActivity {
    Button save,reload,update;
    EditText email,username,password;
    String _email,_username,_password;
    Spinner portals;
    private FirebaseAuth mAuth;
    private String userId;
    private AwesomeValidation awesomeValidation;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabase,mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_account_detail);

        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        save= findViewById(R.id.savecredentials);
        reload= findViewById(R.id.buttonreload);
        update=findViewById(R.id.update);
        email= findViewById(R.id.portalemail);
        username= findViewById(R.id.portalusername);
        password= findViewById(R.id.portalpassword);
        portals= findViewById(R.id.jobportals);

        awesomeValidation.addValidation(JobAccountDetail.this, R.id.portalusername, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(JobAccountDetail.this, R.id.portalemail, android.util.Patterns.EMAIL_ADDRESS, R.string.emailerror);


        mFirebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        userId=user.getUid();
        mRef=mFirebaseDatabase.getReference().child(userId);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate() && !(password.getText().toString().isEmpty())) {
                    PortalAccountDetails portalAccountDetails = new PortalAccountDetails(email.getText().toString(), username.getText().toString(), password.getText().toString());
                    mDatabase = FirebaseDatabase.getInstance().getReference().child(uid);
                    mDatabase.child("Job_Portal_Account_Details").setValue(portalAccountDetails);
                    Toast.makeText(JobAccountDetail.this, "Details are added successfully!", LENGTH_SHORT).show();
                }else{
                    Toast.makeText(JobAccountDetail.this, "invalid record cannot be saved!", LENGTH_SHORT).show();
                }
            }
        });
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PortalAccountDetails record=snapshot.child("Job_Portal_Account_Details").getValue(PortalAccountDetails.class);
                        if(record!=null){
                            show(record);
                        }else{
                            Toast.makeText(JobAccountDetail.this, "No record exist", LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(JobAccountDetail.this, "Not Found", LENGTH_SHORT).show();
                    }
                });
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate() && password.getText().toString()=="") {

                    if (isDatachanged()) {

                        Toast.makeText(JobAccountDetail.this, "data is updated", LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(JobAccountDetail.this, "data is same", LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(JobAccountDetail.this, "invalid record cannot be updated", LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isDatachanged() {
        if (_email.equals(email.getText().toString())||_username.equals(username.getText().toString())||_password.equals(password.getText().toString()))
        {

            mRef.child("Job_Portal_Account_Details").child("email").setValue(email.getText().toString());
            mRef.child("Job_Portal_Account_Details").child("username").setValue(username.getText().toString());
            mRef.child("Job_Portal_Account_Details").child("password").setValue(password.getText().toString());

            return true;
        }
        else {
            return false;
        }
    }
    private void show(PortalAccountDetails record) {
        _email=record.getEmail();
        _username=record.getUsername();
        _password=record.getPassword();
        email.setText(_email);
        username.setText(_username);
        password.setText(_password);
    }
}