package com.myjobassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class UpdateProfile extends AppCompatActivity {
    Button updateinformation,UpdateEducation,UpdateExperience,UpdateDomain,UpdateMiscellaneous,AddDocuments,back_5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_profile);
        back_5 = findViewById(R.id.back_5);
        updateinformation= findViewById(R.id.updateinformation);
        UpdateEducation= findViewById(R.id.UpdateEducation);
        UpdateExperience = findViewById(R.id.UpdateExperience);
        UpdateDomain = findViewById(R.id.UpdateDomain);
        UpdateMiscellaneous = findViewById(R.id.UpdateMiscellaneous);
        AddDocuments = findViewById(R.id.AddDocuments);
        back_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateProfile.this, home.class);
                startActivity(i);
            }
        });
        updateinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateProfile.this,  UpdateInformation.class);
                startActivity(i);
            }
        });
        UpdateEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateProfile.this,  update_education.class);
                startActivity(i);
            }
        });
        UpdateExperience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateProfile.this,  update_experience.class);
                startActivity(i);
            }
        });
        UpdateDomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateProfile.this,  update_domain.class);
                startActivity(i);
            }
        });
        UpdateMiscellaneous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateProfile.this,  UpdateMiscellaneous.class);
                startActivity(i);
            }
        });
        AddDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateProfile.this, AddDocuments.class);
                startActivity(i);
            }
        });


    }
}