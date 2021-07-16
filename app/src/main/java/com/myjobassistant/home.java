package com.myjobassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity {
    TextView login;
    Button addinfo;
    Button updateinfo;
    Button applyjobs;
    FirebaseAuth mAuth;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    private Button btnToggleDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.home);
        setUpToolbar();
        login = findViewById(R.id.hello);

        mAuth = FirebaseAuth.getInstance();
        String user = mAuth.getCurrentUser().getEmail();
        String[] email=user.split("@");
        login.setText("Hello "+email[0]);

        addinfo = findViewById(R.id.addinfo);
        addinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent=new Intent(home.this,addinformation.class);
                startActivity(nextIntent);
            }
        });
        updateinfo=findViewById(R.id.updateinfo);
        updateinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent=new Intent(home.this,UpdateProfile.class);
                startActivity(nextIntent);
            }
        });
        applyjobs=findViewById(R.id.applyjobs);
        applyjobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextIntent=new Intent(home.this,StartApplyJobs.class);
                startActivity(nextIntent);
            }
        });


        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.nav_profile:
                    {
                        Intent i=new Intent(home.this,ProfilePage.class);
                        startActivity(i);
                    }
                        break;
                    case R.id.nav_notification:
                        Toast.makeText(home.this, "notifications", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_joblist:
                        Toast.makeText(home.this, "joblist", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout:
                        mAuth.signOut();
                        Intent i = new Intent(home.this, MainActivity2.class);
                        startActivity(i);
                        break;

                }
                return false;
            }
        });

    }

    public void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        actionBarDrawerToggle.syncState();

    }

}




