package com.example.pranckall;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.pranckall.fragments.ContactsFragment;
import com.example.pranckall.fragments.FavouritesFragment;
import com.example.pranckall.fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button logout;
    private BottomNavigationView navigationView;
    private Fragment fragment;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logout = findViewById(R.id.logout);
        navigationView = findViewById(R.id.main_navigation);
        mFirebaseAuth = FirebaseAuth.getInstance();

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.favourites:
                        fragment = new FavouritesFragment();
                        break;
                    case R.id.contacts:
                        fragment = new ContactsFragment();
                        break;
                }
                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                }
                return true;
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactsFragment()).commit();

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this, StartActivity.class));
                finish();
            }
        });
    }
}
