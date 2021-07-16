package com.myjobassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;

public class StartApplyJobs extends AppCompatActivity {
    Button portaldetails,start,sendit;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private DatabaseReference domainRef;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabase;
    String name,email,password,mydomain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_apply_jobs);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        String uid=mAuth.getUid();
        mRef=mFirebaseDatabase.getReference().child(uid);

        portaldetails = findViewById(R.id.portaldetails);
        start = findViewById(R.id.start);
        sendit= findViewById(R.id.sendit);

        portaldetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartApplyJobs.this, JobAccountDetail.class);
                startActivity(i);
            }
        });
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        PortalAccountDetails portalrecord=snapshot.child("Job_Portal_Account_Details").getValue(PortalAccountDetails.class);
                        if(portalrecord!=null){
                            name=portalrecord.getUsername();
                            email=portalrecord.getEmail();
                            password=portalrecord.getPassword();
                            }else{
                            Toast.makeText(StartApplyJobs.this, "Personal record not exist; Add it first", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(StartApplyJobs.this, "Not Found", LENGTH_SHORT).show();
                    }
                });
                domainRef=mFirebaseDatabase.getReference().child(uid).child("Domain");
                domainRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Domain_Profile domain=snapshot.child("DomainProfile").getValue(Domain_Profile.class);
                        if(domain!=null){
                            mydomain=domain.getSelected_domain_profile();
                        }else{
                            Toast.makeText(StartApplyJobs.this, "No job-domain exist; Add it first", Toast.LENGTH_LONG).show();
                        }}

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(StartApplyJobs.this, "Not Found", LENGTH_SHORT).show();
                    }
                });
                if(name!=null && mydomain!=null)
                Toast.makeText(StartApplyJobs.this, "Record taken successfully for "+ name + "!", Toast.LENGTH_LONG).show();
            }
        });

        sendit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = "{\"name\": \""+name+"\", \"email\": \""+email+"\", \"password\": \""+password+"\", \"job_domain\": \""+mydomain+"\"}";
                try {
                    JSONObject obj=new JSONObject(string);
                    Toast.makeText(StartApplyJobs.this, obj.toString(), LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}