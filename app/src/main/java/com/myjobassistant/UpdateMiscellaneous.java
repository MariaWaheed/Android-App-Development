package com.myjobassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_SHORT;

public class UpdateMiscellaneous extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userId;
    private AwesomeValidation awesomeValidation;
    Button update,back;
    EditText update_jobtype, update_salary,update_others;
    String jobtype, slaray, others;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_miscellaneous);
        update_jobtype=findViewById(R.id.jobtype);
        update_salary=findViewById(R.id.salary);
        update_others=findViewById(R.id.otherreq);
        update=findViewById(R.id.updatejobreq);
        back=findViewById(R.id.back7);
        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        userId=user.getUid();
        mRef=mFirebaseDatabase.getReference().child(userId);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                JobRequirement record=snapshot.child("JobRequirement").getValue(JobRequirement.class);
                if(record!=null){
                    show(record);
                }else{
                    Toast.makeText(UpdateMiscellaneous.this, "No record exist; Add it First", Toast.LENGTH_LONG).show();
                    update_jobtype.setEnabled(false);
                    update_salary.setEnabled(false);
                    update_others.setEnabled(false);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateMiscellaneous.this, "Not Found", LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                    if(isDatachanged()){

                        Toast.makeText(UpdateMiscellaneous.this, "data is updated", LENGTH_SHORT).show();
                    }

                else{
                    Toast.makeText(UpdateMiscellaneous.this, "data is same ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean isDatachanged() {

        if (!jobtype.equals(update_jobtype.getText().toString())||!slaray.equals(update_salary.getText().toString())||!others.equals(update_others.getText().toString()))
        {

            mRef.child("JobRequirement").child("job_type").setValue(update_jobtype.getText().toString());
            mRef.child("JobRequirement").child("salary").setValue(update_salary.getText().toString());
            mRef.child("JobRequirement").child("otherinfo").setValue(update_others.getText().toString());

            return true;
        }
        else {
            return false;
        }
    }


    private void show(JobRequirement record) {
        jobtype=record.getJob_type();
        slaray=record.getSalary();
        others=record.getOtherinfo();
        update_jobtype.setText(jobtype);
        update_salary.setText(slaray);
        update_others.setText(others);
    }
}