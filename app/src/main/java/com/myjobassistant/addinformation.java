package com.myjobassistant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

public class addinformation extends AppCompatActivity {
    EditText fname,lname,email,dob,country,city,domicile,postalcode,add,phone,cnic;
    RadioButton female,male;
    String first,last,myemail,date,mycountry,mycity,mydomicile,postal,address,cell,gender,mycnic;
    Button addpersonal,back,next;
    private AwesomeValidation awesomeValidation;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.addinformation);

        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

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
        female= findViewById(R.id.female);
        male= findViewById(R.id.male);
        addpersonal= findViewById(R.id.addpersonal);
        back=findViewById(R.id.backtohome);
        next= findViewById(R.id.nexttoaddeducation);
        awesomeValidation.addValidation(addinformation.this, R.id.fname, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(addinformation.this, R.id.lname, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(addinformation.this, R.id.myemail, android.util.Patterns.EMAIL_ADDRESS, R.string.emailerror);
        awesomeValidation.addValidation(addinformation.this, R.id.cnic, "^[0-9]{5}-[0-9]{7}-[0-9]$", R.string.cnicerror);
        //awesomeValidation.addValidation(addinformation.this, R.id.phone, "^1?([1-9])(\\d{9})", R.string.mobileerror);
        awesomeValidation.addValidation(addinformation.this, R.id.dob, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.doberror);

        addpersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {
                    first = fname.getText().toString();
                    last = lname.getText().toString();
                    myemail = email.getText().toString();
                    date = dob.getText().toString();
                    mycountry = country.getText().toString();
                    mycity = city.getText().toString();
                    mydomicile = domicile.getText().toString();
                    postal = postalcode.getText().toString();
                    address = add.getText().toString();
                    cell = phone.getText().toString();
                    mycnic= cnic.getText().toString();
                    if(female.isChecked()){
                            gender="female";
                        }else {
                            gender = "male";
                        }
                        PersonalRecord user=new PersonalRecord(first,last,myemail,date,mycountry,mycity,mydomicile,postal,address,cell,gender,mycnic);
                        user.setFname(first);
                        mDatabase = FirebaseDatabase.getInstance().getReference().child(uid);
                        mDatabase.child("PersonalRecord").setValue(user);
                        Toast.makeText(addinformation.this, "Record is added successfully for "+ first, Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(addinformation.this, "Record should not be null or invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(addinformation.this,home.class);
                startActivity(i);
            }

        });


    next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i= new Intent(addinformation.this, AddEducation.class);
            startActivity(i);
        }
    });

    }

}
