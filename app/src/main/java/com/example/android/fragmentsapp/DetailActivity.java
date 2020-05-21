package com.example.android.fragmentsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class DetailActivity extends AppCompatActivity implements DetailFragment.FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("PERSON_KEY")) {
                Person person = intent.getParcelableExtra("PERSON_KEY");
                DetailFragment fragment = DetailFragment.newInstance(person);
                FragmentManager fragmentManager =
                        getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .add(R.id.fragment, fragment)
                        .commit();
            }
        }
    }

    @Override
    public void onFragmentFinish(Person person) {
        Log.i("DetailActivity", "onFragmentFinish: " + person.getAge());
    }
}
