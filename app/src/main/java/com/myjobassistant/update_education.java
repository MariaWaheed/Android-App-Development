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
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.widget.Toast.LENGTH_SHORT;

public class update_education extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userId;
    private AwesomeValidation awesomeValidation;
    Button next,back,update;
    EditText minst,myear,msub,mgrade,hinst,hyear,hsub,hgrade,ginst,gyear,gsub,ggrade,masterinst,masteryear,mastergrade,mastersub,pinst,pyear,psub,pgrade;
    String myminst,mymyear,mymsub,mymgrade,myhinst,myhyear,myhsub,myhgrade,myginst,mygyear,mygsub,myggrade,mymasterinst,mymasteryear,mymastergrade,mymastersub,mypinst,mypyear,mypsub,mypgrade;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_education);

        back = findViewById(R.id.back_2);
        update = findViewById(R.id.update);
        next = findViewById(R.id.next);

        minst = findViewById(R.id.Minstitution);
        myear = findViewById(R.id.Mendyear);
        msub = findViewById(R.id.Msubject);
        mgrade = findViewById(R.id.Mmarks);
        hinst = findViewById(R.id.Hinstitution);
        hyear = findViewById(R.id.Hendyear);
        hsub = findViewById(R.id.Hsubject);
        hgrade = findViewById(R.id.Hmarks);
        ginst = findViewById(R.id.Ginstitution);
        gyear = findViewById(R.id.Gendyear);
        gsub = findViewById(R.id.Gsubject);
        ggrade = findViewById(R.id.Gmarks);
        masterinst = findViewById(R.id.Oinstitution);
        masteryear = findViewById(R.id.Oendyear);
        mastergrade = findViewById(R.id.Omarks);
        mastersub = findViewById(R.id.Osubject);
        pinst = findViewById(R.id.Pinstitution);
        pyear = findViewById(R.id.Pendyear);
        psub = findViewById(R.id.Psubject);
        pgrade = findViewById(R.id.Pmarks);

        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        userId=user.getUid();
        mRef=mFirebaseDatabase.getReference().child(userId);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Education record= snapshot.child("Education").getValue(Education.class);
                if(record!=null){
                    show(record);
                }else{
                    Toast.makeText(update_education.this, "No record exist; add it first", Toast.LENGTH_LONG).show();
                    minst.setEnabled(false);
                    myear.setEnabled(false);
                    mgrade.setEnabled(false);
                    msub.setEnabled(false);
                    hinst.setEnabled(false);
                    hyear.setEnabled(false);
                    hgrade.setEnabled(false);
                    hsub.setEnabled(false);
                    masterinst.setEnabled(false);
                    mastergrade.setEnabled(false);
                    mastersub.setEnabled(false);
                    masteryear.setEnabled(false);
                    pgrade.setEnabled(false);
                    pinst.setEnabled(false);
                    psub.setEnabled(false);
                    pyear.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(update_education.this, "Not Found", LENGTH_SHORT).show();
            }
        });

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(update_education.this, R.id.Minstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(update_education.this, R.id.Mendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(update_education.this, R.id.Msubject, "[a-zA-Z\\s]+", R.string.nameerror);

        awesomeValidation.addValidation(update_education.this, R.id.Hinstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(update_education.this, R.id.Hendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(update_education.this, R.id.Hsubject, "[a-zA-Z\\s]+", R.string.nameerror);

        awesomeValidation.addValidation(update_education.this, R.id.Ginstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(update_education.this, R.id.Gendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(update_education.this, R.id.Gsubject, "[a-zA-Z\\s]+", R.string.nameerror);

        awesomeValidation.addValidation(update_education.this, R.id.Oinstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(update_education.this, R.id.Oendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(update_education.this, R.id.Osubject, "[a-zA-Z\\s]+", R.string.nameerror);

        awesomeValidation.addValidation(update_education.this, R.id.Pinstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(update_education.this, R.id.Pendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(update_education.this, R.id.Psubject, "[a-zA-Z\\s]+", R.string.nameerror);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(update_education.this, UpdateInformation.class);
                startActivity(i);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {


                    if (isDatachanged()) {

                        Toast.makeText(update_education.this, "data is updated", LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(update_education.this, "data is same", LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(update_education.this, "invalid ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(update_education.this, update_experience.class);
                startActivity(i);
            }
        });


    }
    private boolean isDatachanged(){

        if (!myminst.equals(minst.getText().toString()) || !mymyear.equals(myear.getText().toString())||!mymsub.equals(msub.getText().toString())
                ||!mymgrade.equals(mgrade.getText().toString())||!myhinst.equals(hinst.getText().toString())||!myhyear.equals(hyear.getText().toString())
                ||!myhsub.equals(hsub.getText().toString())||!myhgrade.equals(hgrade.getText().toString())||!myginst.equals(ginst.getText().toString())||!mygyear.equals(gyear.getText().toString())||!mygsub.equals(gsub.getText().toString())
                ||!myggrade.equals(ggrade.getText().toString())||!mymasterinst.equals(masterinst.getText().toString())||!mymasteryear.equals(masteryear.getText().toString())
                ||!mymastergrade.equals(mastergrade.getText().toString())||!mymastersub.equals(mastersub.getText().toString())||!mypinst.equals(pinst.getText().toString())
                ||!mypyear.equals(pyear.getText().toString())||!mypsub.equals(psub.getText().toString())||!mypgrade.equals(pgrade.getText().toString())) {
            mRef.child("Education").child("metric_Institute").setValue(minst.getText().toString());
            mRef.child("Education").child("metric_endYear").setValue(myear.getText().toString());
            mRef.child("Education").child("metric_subject").setValue(msub.getText().toString());
            mRef.child("Education").child("metric_grade").setValue(mgrade.getText().toString());
            mRef.child("Education").child("inter_Institute").setValue(hinst.getText().toString());
            mRef.child("Education").child("inter_endYear").setValue(hyear.getText().toString());
            mRef.child("Education").child("inter_subject").setValue(hsub.getText().toString());
            mRef.child("Education").child("inter_grade").setValue(hgrade.getText().toString());
            mRef.child("Education").child("graduation_Institute").setValue(ginst.getText().toString());
            mRef.child("Education").child("graduation_endYear").setValue(gyear.getText().toString());
            mRef.child("Education").child("graduation_subject").setValue(gsub.getText().toString());
            mRef.child("Education").child("graduation_grade").setValue(ggrade.getText().toString());
            mRef.child("Education").child("master_Institute").setValue(masterinst.getText().toString());
            mRef.child("Education").child("master_endYear").setValue(masteryear.getText().toString());
            mRef.child("Education").child("master_grade").setValue(mastergrade.getText().toString());
            mRef.child("Education").child("master_subject").setValue(mastersub.getText().toString());
            mRef.child("Education").child("phd_Institute").setValue(pinst.getText().toString());
            mRef.child("Education").child("phd_endYear").setValue(pyear.getText().toString());
            mRef.child("Education").child("phd_subject").setValue(psub.getText().toString());
            mRef.child("Education").child("phd_grade").setValue(pgrade.getText().toString());


            return true;
        }
        else {
            return false;
        }
    }

    public void show(Education userrecord){
        myminst=userrecord.getMetric_Institute();
        mymyear=userrecord.getMetric_endYear();
        mymgrade=userrecord.getMetric_grade();
        mymsub=userrecord.getMetric_subject();
        myhinst=userrecord.getInter_Institute();
        myhyear=userrecord.getInter_endYear();
        myhgrade=userrecord.getInter_grade();
        myhsub=userrecord.getInter_subject();
        myginst=userrecord.getGraduation_Institute();
        myggrade=userrecord.getGraduation_grade();
        mygsub=userrecord.getGraduation_subject();
        mygyear=userrecord.getGraduation_endYear();
        mymasterinst=userrecord.getMaster_Institute();
        mymastergrade=userrecord.getMaster_grade();
        mymastersub=userrecord.getGraduation_subject();
        mymasteryear=userrecord.getMaster_endYear();
        mypinst=userrecord.getPhd_Institute();
        mypgrade=userrecord.getPhd_grade();
        mypsub=userrecord.getPhd_subject();
        mypyear=userrecord.getPhd_endYear();
        minst.setText(myminst);
        myear.setText(mymyear);
        mgrade.setText(mymgrade);
        msub.setText(mymsub);
        hinst.setText(myhinst);
        hyear.setText(myhyear);
        hgrade.setText(myhgrade);
        hsub.setText(myhsub);
        ginst.setText(myginst);
        ggrade.setText(myggrade);
        gyear.setText(mygyear);
        gsub.setText(mygsub);
        masterinst.setText(mymasterinst);
        mastersub.setText(mymastersub);
        mastergrade.setText(mymastergrade);
        masteryear.setText(mymasteryear);
        pinst.setText(mypinst);
        pyear.setText(mypyear);
        pgrade.setText(mypgrade);
        psub.setText(mypsub);
    }
}