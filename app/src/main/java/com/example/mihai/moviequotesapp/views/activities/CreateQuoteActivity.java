package com.example.mihai.moviequotesapp.views.activities;

import android.os.Bundle;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.GenerateQuoteFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class CreateQuoteActivity extends DaggerAppCompatActivity {

    @Inject
    public GenerateQuoteFragment mGenerateQuoteFragment;

    @Inject
    public GenerateQuoteContracts.Presenter mPresenter;

    @Inject
    public DrawerFragment mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragment_layout);

        mDrawer.setID(Constants.CREATE_ACTIVITY_ID);
        setSupportActionBar(mDrawer.getToolbar());

        mGenerateQuoteFragment.setPresenter(mPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer, mDrawer)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mGenerateQuoteFragment)
                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDrawer.setupDrawer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(mGenerateQuoteFragment);
    }
}
