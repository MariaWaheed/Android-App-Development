package com.myjobassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_SHORT;

public class update_domain extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userId;
    Button update,back,next;
    EditText domain,domainp;
    String udomain,udomainp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_domain);
        update= findViewById(R.id.update);
        back=findViewById(R.id.back);
        next = findViewById(R.id.next);
        domain=findViewById(R.id.domain);
        domainp=findViewById(R.id.domainp);
        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        userId=user.getUid();
        mRef=mFirebaseDatabase.getReference().child(userId);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Domain record=snapshot.child("Domain").getValue(Domain.class);
                Domain_Profile rec=snapshot.child("Domain").child("DomainProfile").getValue(Domain_Profile.class);
                if(record!=null){
                    show(record);
                }else{
                    Toast.makeText(update_domain.this, "No domain exist", Toast.LENGTH_LONG).show();
                    domain.setEnabled(false);
                }
                if(rec!=null){
                    show2(rec);
                }else{
                    Toast.makeText(update_domain.this, "No domain profile exist", Toast.LENGTH_LONG).show();
                    domainp.setEnabled(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update_domain.this, "Not Found", LENGTH_SHORT).show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_domain.this, update_experience.class);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isDatachanged()) {

                    Toast.makeText(update_domain.this, "data is updated", LENGTH_SHORT).show();
                } else {
                    Toast.makeText(update_domain.this, "data is same", LENGTH_SHORT).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(update_domain.this, UpdateProfile.class);
                startActivity(i);
            }
        });
    }
    private boolean isDatachanged(){

        if (!udomain.equals(domain.getText().toString())|| !udomainp.equals(domainp.getText().toString())) {
            mRef.child("Domain").child("SelectedDomain").setValue(domain.getText().toString());
            mRef.child("Domain").child("DomainProfile").child("selected_domain_profile").setValue(domainp.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }

    public void show(Domain userrecord){
       udomain=userrecord.getSelectedDomain();
       domain.setText(udomain);
    }
    public void show2(Domain_Profile userrecord){
        udomainp=userrecord.getSelected_domain_profile();
        domainp.setText(udomainp);
    }
}