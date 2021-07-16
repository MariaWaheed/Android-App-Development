package com.myjobassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;
import android.widget.Toolbar;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddEducation extends AppCompatActivity {
    Button addedu,back,next;
    EditText minst,myear,msub,mgrade,hinst,hyear,hsub,hgrade,ginst,gyear,gsub,ggrade,masterinst,masteryear,mastergrade,mastersub,pinst,pyear,psub,pgrade;
    String myminst,mymyear,mymsub,mymgrade,myhinst,myhyear,myhsub,myhgrade,myginst,mygyear,mygsub,myggrade,mymasterinst,mymasteryear,mymastergrade,mymastersub,mypinst,mypyear,mypsub,mypgrade;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_education);

        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        back= findViewById(R.id.back_2);
        next= findViewById(R.id.nexttoaddexperience);
        addedu= findViewById(R.id.addeducation);
        minst= findViewById(R.id.Minstitution);
        myear= findViewById(R.id.Mendyear);
        msub= findViewById(R.id.Msubject);
        mgrade= findViewById(R.id.Mmarks);
        hinst= findViewById(R.id.Hinstitution);
        hyear= findViewById(R.id.Hendyear);
        hsub= findViewById(R.id.Hsubject);
        hgrade= findViewById(R.id.Hmarks);
        ginst= findViewById(R.id.Ginstitution);
        gyear= findViewById(R.id.Gendyear);
        gsub= findViewById(R.id.Gsubject);
        ggrade= findViewById(R.id.Gmarks);
        masterinst= findViewById(R.id.Oinstitution);
        masteryear= findViewById(R.id.Oendyear);
        mastergrade= findViewById(R.id.Omarks);
        mastersub= findViewById(R.id.Osubject);
        pinst= findViewById(R.id.Pinstitution);
        pyear= findViewById(R.id.Pendyear);
        psub= findViewById(R.id.Psubject);
        pgrade= findViewById(R.id.Pmarks);

        awesomeValidation.addValidation(AddEducation.this, R.id.Minstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(AddEducation.this, R.id.Mendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(AddEducation.this, R.id.Msubject, "[a-zA-Z\\s]+", R.string.nameerror);

        awesomeValidation.addValidation(AddEducation.this, R.id.Hinstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(AddEducation.this, R.id.Hendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(AddEducation.this, R.id.Hsubject, "[a-zA-Z\\s]+", R.string.nameerror);

        awesomeValidation.addValidation(AddEducation.this, R.id.Ginstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(AddEducation.this, R.id.Gendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(AddEducation.this, R.id.Gsubject, "[a-zA-Z\\s]+", R.string.nameerror);

        awesomeValidation.addValidation(AddEducation.this, R.id.Oinstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(AddEducation.this, R.id.Oendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(AddEducation.this, R.id.Osubject, "[a-zA-Z\\s]+", R.string.nameerror);

        awesomeValidation.addValidation(AddEducation.this, R.id.Pinstitution, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(AddEducation.this, R.id.Pendyear, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(AddEducation.this, R.id.Psubject, "[a-zA-Z\\s]+", R.string.nameerror);
        addedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {
                    myminst = minst.getText().toString();
                    mymyear = myear.getText().toString();
                    mymsub = msub.getText().toString();
                    mymgrade = mgrade.getText().toString();
                    myhinst = hinst.getText().toString();
                    myhyear = hyear.getText().toString();
                    myhsub = hsub.getText().toString();
                    myhgrade = hgrade.getText().toString();
                    myginst = ginst.getText().toString();
                    mygyear = gyear.getText().toString();
                    mygsub = gsub.getText().toString();
                    myggrade = ggrade.getText().toString();
                    mymasterinst = masterinst.getText().toString();
                    mymasteryear = masteryear.getText().toString();
                    mymastergrade = mastergrade.getText().toString();
                    mymastersub = mastersub.getText().toString();
                    mypinst = pinst.getText().toString();
                    mypyear = pyear.getText().toString();
                    mypsub = psub.getText().toString();
                    mypgrade = pgrade.getText().toString();

                    Education edu = new Education(myminst, mymyear, mymgrade, mymsub, myhinst, myhyear, myhgrade, myhsub, myginst, mygyear, myggrade, mygsub, mymasterinst, mymasteryear, mymastergrade, mymastersub, mypinst, mypyear, mypgrade, mypsub);
                    mDatabase = FirebaseDatabase.getInstance().getReference().child(uid);
                    mDatabase.child("Education").setValue(edu);
                    Toast.makeText(AddEducation.this, "Education is added successfully!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddEducation.this, "Record should not be null or invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddEducation.this, AddExperience.class);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddEducation.this, home.class);
                startActivity(i);
            }
        });
    }




}