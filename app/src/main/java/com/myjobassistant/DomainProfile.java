package com.myjobassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DomainProfile extends AppCompatActivity {
    String [] technology,business,education,avaiation,service_industry,art,media,medical;
    String selected,selected_domainprofile;
    RadioButton d1;
    EditText otherdomainprofile;
    private FirebaseAuth mAuth;
    RadioGroup radioGroup;
    RadioButton selectedradio;
    Button adddomainprofile,back,next;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_domain_profile);

        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();



        technology= new String[]{"Mechnical Engineering", "Electrical Engineering","Civil Engineering","Software Engineering","Back End Development","Front End Development","Programming"};
        business=new String[]{"Accountant","Banking","Administrative /Assistant","Advertisement","Financial Advisor","Human Resource","Sales and Marketing"};
        education=new String[]{"College Professor","Assistant Teacher","School jobs","University Professor","Teaching Online","Montessori Teacher","Assistant Professor"};
        avaiation=new String[]{"Pilot","Federal Air Marshall","Flight Attendant","Transportation Security Officer","Aircraft Dispatcher","Air Traffic Controller","Aeronautical Engineer"};
        service_industry=new String[]{"Call Center","Fitness Trainer","Retailer","Event Organizer","Restaurant","Salon Expert","Wedding Planner"};
        art=new String[]{"Actor","Architect","Artist","Museum Jobs","Music Conductor","Art Auctioneer","Art Appraiser"};
        media=new String[]{"Book Publishing","Freelance Writting","Public Relation","Writter / Editor","Content Manager","Digital Media Specialist","Author"};
        medical= new String[]{"Doctor","Hospitality","Nurse","Mental Health Councler","Physician","Phychiatrist","Dentist"};

        Intent intent=getIntent();
        selected=intent.getStringExtra("domain");


        otherdomainprofile=findViewById(R.id.othersubdomain);
        d1= findViewById(R.id.r8);
        adddomainprofile= findViewById(R.id.adddomainprofile);
        back= findViewById(R.id.back_5);
        next= findViewById(R.id.nextto);

        radioGroup = (RadioGroup)findViewById(R.id.subprofile_group);
        otherdomainprofile.setEnabled(false);
        radioGroup.setVisibility(View.VISIBLE);

        adddomainprofile.setEnabled(false);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                adddomainprofile.setEnabled(true);
            }
        });

        for (int i = 0; i < radioGroup.getChildCount()-1; i++) {
            if(selected.equals("Technology")) {
                ((RadioButton) radioGroup.getChildAt(i)).setText(technology[i]);
            }else if(selected.equals("Buisness")){
                ((RadioButton) radioGroup.getChildAt(i)).setText(business[i]);
            }else if(selected.equals("Education")){
                ((RadioButton) radioGroup.getChildAt(i)).setText(education[i]);
            }else if(selected.equals("Aviation")){
                ((RadioButton) radioGroup.getChildAt(i)).setText(avaiation[i]);
            }else if(selected.equals("Service Industry")){
                ((RadioButton) radioGroup.getChildAt(i)).setText(service_industry[i]);
            }else if(selected.equals("Arts")){
                ((RadioButton) radioGroup.getChildAt(i)).setText(art[i]);
            }else if(selected.equals("Medical")){
                ((RadioButton) radioGroup.getChildAt(i)).setText(medical[i]);
            }else if(selected.equals("Media")){
                ((RadioButton) radioGroup.getChildAt(i)).setText(media[i]);
            }
        }
        if(selected.equals("Other")){
            radioGroup.setVisibility(View.GONE);
            otherdomainprofile.setEnabled(true);
        }
        d1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otherdomainprofile.setEnabled(true);
            }
        });

        adddomainprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selected.equals("Other")){
                    selected_domainprofile=otherdomainprofile.getText().toString();
                }else{
                    int selectedId=radioGroup.getCheckedRadioButtonId();
                    selectedradio=(RadioButton)findViewById(selectedId);
                    selected_domainprofile=selectedradio.getText().toString();
                    if(selected_domainprofile.equals("Other Domain Profile")){
                        selected_domainprofile=otherdomainprofile.getText().toString();
                    }
                }
                Domain_Profile domain_profile=new Domain_Profile(selected_domainprofile);
                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid).child("Domain");
                mDatabase.child("DomainProfile").setValue(domain_profile);
                Toast.makeText(DomainProfile.this, "DomainProfile is added successfully!", Toast.LENGTH_SHORT).show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(DomainProfile.this, home.class);
                startActivity(i);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DomainProfile.this, AddDocuments.class);
                startActivity(i);
            }
        });
    }
}