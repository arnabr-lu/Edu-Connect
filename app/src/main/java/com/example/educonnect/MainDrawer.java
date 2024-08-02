package com.example.educonnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.example.educonnect.ui.home.HomeFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.educonnect.databinding.ActivityMainDrawer2Binding;
import com.google.firebase.auth.FirebaseAuth;

public class MainDrawer extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainDrawer2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainDrawer2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainDrawer.toolbar);
//        binding.appBarMainDrawer.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null)
//                        .setAnchorView(R.id.fab).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;





        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.application, R.id.nav_slideshow, R.id.logOut)
                .setOpenableLayout(drawer)
                .build();

        FragmentActivity activity = MainDrawer.this;
        Fragment frgmnt = new Generate_Form();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();





        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.nav_home) {
                    // Handle navigation to home fragment
                    Fragment frgmt = new HomeFragment();
                    chngFrgmnt(fragmentManager, frgmt);
                }
                else if (menuItem.getItemId() == R.id.application) {
                    // Handle navigation to home fragment
                }
                else if (menuItem.getItemId() == R.id.nav_gallery) {
                    // Handle navigation to home fragment
                }
                else if (menuItem.getItemId() == R.id.nav_slideshow) {
                    // Handle navigation to home fragment
                }
                else if (menuItem.getItemId() == R.id.logOut) {
                    // Handle navigation to home fragment
                }
                else if (menuItem.getItemId() == R.id.nav_home) {
                    // Handle navigation to home fragment
                }

                return true; // Consume the event (optional)
            }
        });


        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
    void chngFrgmnt(FragmentManager fragmentManager, Fragment frgmnt) {
        fragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment_content_main_drawer, frgmnt) // Replace with your container ID
                .addToBackStack(null)
                .commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }




}