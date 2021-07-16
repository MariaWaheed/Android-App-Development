package com.myjobassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
public class AddExperience extends AppCompatActivity {
    Button addexperience,back,next;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    EditText title,company,location,start,end,industry;
    String jbtitle,type,jbcompany,jblocation,jbstart,jbend,jbindustry;
    Spinner emptype;
    private AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experience);
        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        addexperience= findViewById(R.id.addexperience);
        back= findViewById(R.id.back_3);
        next= findViewById(R.id.nexttoadddomain);
        emptype= findViewById(R.id.spinner3);
        title= findViewById(R.id.jbt1);
        company= findViewById(R.id.jbcompany);
        location= findViewById(R.id.jblocation);
        start= findViewById(R.id.jbstart);
        end= findViewById(R.id.jbend);
        industry= findViewById(R.id.jbindustry);

        awesomeValidation.addValidation(AddExperience.this, R.id.jbt1, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(AddExperience.this, R.id.jbcompany, "[a-zA-Z\\s]+", R.string.nameerror);
        awesomeValidation.addValidation(AddExperience.this, R.id.jbstart, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(AddExperience.this, R.id.jbend, "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$", R.string.yearError);
        awesomeValidation.addValidation(AddExperience.this, R.id.jbindustry, "[a-zA-Z\\s]+", R.string.nameerror);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.font_sizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        emptype.setAdapter(adapter);
        emptype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                type=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                type="Full-time";
            }
        });
        addexperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    jbtitle = title.getText().toString();
                    jbcompany = company.getText().toString();
                    jblocation = location.getText().toString();
                    jbstart = start.getText().toString();
                    jbend = end.getText().toString();
                    jbindustry = industry.getText().toString();

                    Experience exp = new Experience(jbtitle, jbcompany, jblocation, jbstart, jbend, type, jbindustry);
                    mDatabase = FirebaseDatabase.getInstance().getReference().child(uid);
                    mDatabase.child("Experience").setValue(exp);
                    Toast.makeText(AddExperience.this, " Experience added successfully!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddExperience.this, "Record should not be null or invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddExperience.this, home.class);
                startActivity(i);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(AddExperience.this, AddDomain.class);
                startActivity(i);
            }
        });

    }
}