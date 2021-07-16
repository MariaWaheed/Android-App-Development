package com.myjobassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddJobRequirement extends AppCompatActivity {
    Button save,back;
    RadioGroup radioGroup;
    RadioButton selected;
    Spinner jobtype;
    String jobtypestr,selected_salary="";
    EditText otherreq;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job_requirement);

        mAuth = FirebaseAuth.getInstance();
        String uid=mAuth.getUid();

        save= findViewById(R.id.addjobreq);
        back= findViewById(R.id.back7);
        radioGroup= findViewById(R.id.salgroup);
        jobtype= findViewById(R.id.spinnerjobtype);
        otherreq= findViewById(R.id.otherreq1);

        save.setEnabled(false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                save.setEnabled(true);
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.job_nature, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jobtype.setAdapter(adapter);
        jobtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jobtypestr=parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                jobtypestr="Full-time";
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otherreq.getText().toString().isEmpty()){
                    otherreq.setText(" ");
                }
                int selectedId=radioGroup.getCheckedRadioButtonId();
                selected=(RadioButton)findViewById(selectedId);
                selected_salary=selected.getText().toString();
                JobRequirement jobRequirement= new JobRequirement(jobtypestr,selected_salary,otherreq.getText().toString());
                mDatabase = FirebaseDatabase.getInstance().getReference().child(uid);
                mDatabase.child("JobRequirement").setValue(jobRequirement);
                Toast.makeText(AddJobRequirement.this, "Requirements are added successfully!", Toast.LENGTH_SHORT).show();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(AddJobRequirement.this,home.class);
                startActivity(intent);
            }
        });
    }
}