package com.example.fitbud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.core.widget.TextViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.fitbud.Model.Global;
import com.example.fitbud.Model.UserClass;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawer;
    MaterialToolbar toolbar;
    NavigationView navigationView;
    TextView tvEmail;
    TextView tvUserType;
    Global userDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDetails =(Global) getApplicationContext();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer_layout);

        tvEmail = navigationView.getHeaderView(0).findViewById(R.id.nav_email);
       tvEmail.setText(userDetails.getEmail());

       tvUserType = navigationView.getHeaderView(0).findViewById(R.id.nav_user_type);
       tvUserType.setText(userDetails.getUserType());

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MenuFragment()).commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.drawer_exercises:
            intent = new Intent(this,ExercisesActivity.class);
            startActivity(intent);
            break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}