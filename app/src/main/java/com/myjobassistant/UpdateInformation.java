package com.myjobassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

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

public class UpdateInformation extends AppCompatActivity {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String userId;
    private AwesomeValidation awesomeValidation;
    Button update,back,next;
    EditText fname,lname,email,dob,country,city,domicile,postalcode,add,phone,cnic,gender;
    String _Firstname,_Lastname,_Email,_Dob,_Domicile,_Phone,_Country,_City,_Residential,_Postal,_Gender,_Cnic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_information);
        fname= findViewById(R.id.fname);
        lname= findViewById(R.id.lname);
        email= findViewById(R.id.myemail);
        dob= findViewById(R.id.dob);
        cnic= findViewById(R.id.cnic);
        country= findViewById(R.id.country);
        city= findViewById(R.id.city);
        domicile= findViewById(R.id.domicile);
        postalcode= findViewById(R.id.postalcode);
        add= findViewById(R.id.residential);
        phone= findViewById(R.id.phone);
        gender= findViewById(R.id.gender);
        update= findViewById(R.id.buttonupdate);
        back=findViewById(R.id.backtohome);
        next = findViewById(R.id.next);
        mAuth=FirebaseAuth.getInstance();
        mFirebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        userId=user.getUid();
        mRef=mFirebaseDatabase.getReference().child(userId);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                PersonalRecord record=snapshot.child("PersonalRecord").getValue(PersonalRecord.class);
                if(record!=null){
                    show(record);
                }else{
                    Toast.makeText(UpdateInformation.this, "No record exist; Add record first", Toast.LENGTH_LONG).show();
                    fname.setEnabled(false);
                    lname.setEnabled(false);
                    email.setEnabled(false);
                    country.setEnabled(false);
                    city.setEnabled(false);
                    domicile.setEnabled(false);
                    dob.setEnabled(false);
                    postalcode.setEnabled(false);
                    add.setEnabled(false);
                    gender.setEnabled(false);
                    phone.setEnabled(false);
                    cnic.setEnabled(false);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UpdateInformation.this, "Not Found", LENGTH_SHORT).show();
            }
        });

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(UpdateInformation.this, R.id.fname, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(UpdateInformation.this, R.id.lname, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(UpdateInformation.this, R.id.viewemail, android.util.Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(UpdateInformation.this, R.id.cnic, "^[0-9]{5}-[0-9]{7}-[0-9]$", R.string.cnicerror);
        //awesomeValidation.addValidation(UpdateInformation.this, R.id.viewcell, "^1?([1-9])(\\d{9})", R.string.mobileerror);
        awesomeValidation.addValidation(UpdateInformation.this, R.id.dateofbirth, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.doberror);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {

                    if(isDatachanged()){

                        Toast.makeText(UpdateInformation.this, "data is updated", LENGTH_SHORT).show();
                    }

                    else {
                        Toast.makeText(UpdateInformation.this, "data is same", LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(UpdateInformation.this, "invalid ", Toast.LENGTH_SHORT).show();
                }

            }



        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateInformation.this,home.class);
                startActivity(i);
            }

        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateInformation.this, update_education.class);
                startActivity(i);
            }
        });
    }

    private boolean isDatachanged() {

        if (!_Firstname.equals(fname.getText().toString())||!_Lastname.equals(lname.getText().toString())||!_Email.equals(email.getText().toString())
                || !_Country.equals(country.getText().toString())||!_City.equals(city.getText().toString()) ||!_Domicile.equals(domicile.getText().toString())
                ||!_Dob.equals(dob.getText().toString())||!_Postal.equals(postalcode.getText().toString())
                ||!_Residential.equals(add.getText().toString())||!_Gender.equals(gender.getText().toString())
                ||!_Phone.equals(phone.getText().toString())||!_Cnic.equals(cnic.getText().toString()))



        {

            mRef.child("PersonalRecord").child("fname").setValue(fname.getText().toString());
            mRef.child("PersonalRecord").child("last").setValue(lname.getText().toString());
            mRef.child("PersonalRecord").child("email").setValue(email.getText().toString());
            mRef.child("PersonalRecord").child("country").setValue(country.getText().toString());
            mRef.child("PersonalRecord").child("city").setValue(city.getText().toString());
            mRef.child("PersonalRecord").child("domicile").setValue(domicile.getText().toString());
            mRef.child("PersonalRecord").child("date").setValue(dob.getText().toString());
            mRef.child("PersonalRecord").child("postal").setValue(postalcode.getText().toString());
            mRef.child("PersonalRecord").child("address").setValue(add.getText().toString());
            mRef.child("PersonalRecord").child("gender").setValue(gender.getText().toString());
            mRef.child("PersonalRecord").child("cell").setValue(phone.getText().toString());
            mRef.child("PersonalRecord").child("cnic").setValue(cnic.getText().toString());
            return true;
        }
        else {
            return false;
        }
    }
    public void show(PersonalRecord userrecord){
        _Firstname=userrecord.getFname();
        _Lastname=userrecord.getLast();
        _Email=userrecord.getEmail();
        _Country=userrecord.getCountry();
        _City=userrecord.getCity();
        _Domicile=userrecord.getDomicile();
        _Dob=userrecord.getDate();
        _Postal=userrecord.getPostal();
        _Residential=userrecord.getAddress();
        _Gender=userrecord.getGender();
        _Phone=userrecord.getCell();
        _Cnic=userrecord.getCnic();
        fname.setText(_Firstname);
        lname.setText(_Lastname);
        email.setText(_Email);
        country.setText(_Country);
        city.setText(_City);
        domicile.setText(_Domicile);
        dob.setText(_Dob);
        postalcode.setText(_Postal);
        add.setText(_Residential);
        gender.setText(_Gender);
        phone.setText(_Phone);
        cnic.setText(_Cnic);
    }
}