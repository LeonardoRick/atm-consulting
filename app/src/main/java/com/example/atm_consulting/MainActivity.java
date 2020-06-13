package com.example.atm_consulting;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // sending email
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });

        // Drawerlauout and NavigationView, both required to use navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        // Note that menu ID is the same as the fragment on mobile_navigation.xml
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_services,
                R.id.nav_clients, R.id.nav_info
        )
                .setDrawerLayout(drawer)
                .build();

        // navController host fragment that will show each tab
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // Left Menu configuration making navigationView appear when this menu is clicked
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        // Conection of navigationView and fragment navController,
        // Making navigationView, in did, work when one menu item is clicked
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void sendEmail() {
        Intent intent = new Intent( Intent.ACTION_SEND);


        intent.putExtra(Intent.EXTRA_EMAIL, "leonardo@lanytecnologia.com.br");
        intent.putExtra(Intent.EXTRA_SUBJECT, "App contact");
        intent.putExtra(Intent.EXTRA_TEXT, "Hello, I want two quote some values ");

        intent.setType("message/rfc822"); //right type of intent to send email
        startActivity(intent.createChooser(intent, "Pick your e-mail app"));
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}