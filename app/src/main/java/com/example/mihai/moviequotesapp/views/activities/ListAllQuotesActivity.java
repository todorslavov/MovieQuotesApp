package com.example.mihai.moviequotesapp.views.activities;

import android.os.Bundle;

import com.example.mihai.moviequotesapp.Constants;
import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.views.contracts.QuotesListContracts;
import com.example.mihai.moviequotesapp.views.fragments.DrawerFragment;
import com.example.mihai.moviequotesapp.views.fragments.ListQuotesFragment;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ListAllQuotesActivity extends DaggerAppCompatActivity {

    @Inject
    public QuotesListContracts.Presenter mListPresenter;

    @Inject
    public ListQuotesFragment mQuotesListFragment;

    @Inject
    public DrawerFragment mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragment_layout);

        mDrawer.setID(Constants.QUOTES_LIST_ACTIVITY_ID);
        setSupportActionBar(mDrawer.getToolbar());

        mQuotesListFragment.setPresenter(mListPresenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, mQuotesListFragment)
                .commit();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer, mDrawer)
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
        mListPresenter.setView(mQuotesListFragment);
    }
}
