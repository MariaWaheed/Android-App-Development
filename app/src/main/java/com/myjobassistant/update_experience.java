package com.myjobassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class update_experience extends AppCompatActivity {
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userId;
    private AwesomeValidation awesomeValidation;
    Button update,back,next;
    EditText title,company,location,start,end,industry,emptype;
    String jbtitle,type,jbcompany,jblocation,jbstart,jbend,jbindustry;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_experience);

        update= findViewById(R.id.update);
        back= findViewById(R.id.back_3);
        next= findViewById(R.id.nexttoadddomain);
        emptype= findViewById(R.id.spinner3);
        title= findViewById(R.id.jbt1);
        company= findViewById(R.id.jbcompany);
        location= findViewById(R.id.jblocation);
        start= findViewById(R.id.jbstart);
        end= findViewById(R.id.jbend);
        industry= findViewById(R.id.jbindustry);

        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        userId=user.getUid();
        mRef=mFirebaseDatabase.getReference().child(userId);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Experience record=snapshot.child("Experience").getValue(Experience.class);
                if(record!=null){
                    show(record);

                }else{
                    Toast.makeText(update_experience.this, "No record exist; add it first", Toast.LENGTH_LONG).show();
                    emptype.setEnabled(false);
                    title.setEnabled(false);
                    company.setEnabled(false);
                    location.setEnabled(false);
                    start.setEnabled(false);
                    end.setEnabled(false);
                    industry.setEnabled(false);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update_experience.this, "Not Found", LENGTH_SHORT).show();
            }
        });

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(update_experience.this, R.id.jbt1, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(update_experience.this, R.id.jbcompany, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(update_experience.this, R.id.jbstart, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(update_experience.this, R.id.jbend, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(update_experience.this, R.id.jbindustry, "[a-zA-Z\\s]+", R.string.nameerror);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {


                    if (isDatachanged()) {

                        Toast.makeText(update_experience.this, "data is updated", LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(update_experience.this, "data is same", LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(update_experience.this, "invalid ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_experience.this, update_education.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(update_experience.this, update_domain.class);
                startActivity(i);
            }
        });

    }
    private boolean isDatachanged(){

        if (!jbtitle.equals(title.getText().toString()) ||!jbcompany.equals(company.getText().toString())
                ||!jbend.equals(end.getText().toString())||!jbstart.equals(start.getText().toString())||!jbindustry.equals(industry.getText().toString())
                ||!jblocation.equals(location.getText().toString())||!type.equals(emptype.getText().toString())) {

            mRef.child("Experience").child("title").setValue(title.getText().toString());
            mRef.child("Experience").child("company").setValue(company.getText().toString());
            mRef.child("Experience").child("location").setValue(location.getText().toString());
            mRef.child("Experience").child("startyear").setValue(start.getText().toString());
            mRef.child("Experience").child("endyear").setValue(end.getText().toString());
            mRef.child("Experience").child("industry").setValue(industry.getText().toString());
            mRef.child("Experience").child("emp_type").setValue(emptype.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }
    public void show(Experience userrecord){
        jbtitle=userrecord.getTitle();
        jblocation=userrecord.getLocation();
        jbindustry=userrecord.getIndustry();
        jbstart=userrecord.getStartyear();
        jbend=userrecord.getEndyear();
        jbcompany=userrecord.getCompany();
        type=userrecord.getEmp_type();
        title.setText(jbtitle);
        location.setText(jblocation);
        industry.setText(jbindustry);
        start.setText(jbstart);
        end.setText(jbend);
        company.setText(jbcompany);
        emptype.setText(type);
    }
}