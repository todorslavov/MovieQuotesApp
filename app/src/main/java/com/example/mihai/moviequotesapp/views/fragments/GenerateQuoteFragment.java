package com.example.mihai.moviequotesapp.views.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.mihai.moviequotesapp.R;
import com.example.mihai.moviequotesapp.models.Quote;
import com.example.mihai.moviequotesapp.views.activities.CreateQuoteActivity;
import com.example.mihai.moviequotesapp.views.activities.UpdateQuoteActivity;
import com.example.mihai.moviequotesapp.views.contracts.GenerateQuoteContracts;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GenerateQuoteFragment extends Fragment implements GenerateQuoteContracts.View{

    @BindView(R.id.et_quote_text)
    EditText mQuoteText;
    @BindView(R.id.et_movie)
    EditText mMovie;
    @BindView(R.id.et_character)
    EditText mQuotedCharacter;
    @BindView(R.id.rb_rating)
    RatingBar mQuoteRating;
    @BindView(R.id.btn_button)
    Button mButton;

    private GenerateQuoteContracts.Presenter mPresenter;

    @Inject
    public GenerateQuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_create_quote, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresenter(GenerateQuoteContracts.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void showToast() {

        getActivity().runOnUiThread(() -> {
            Toast.makeText(getContext(), "Success!", Toast.LENGTH_SHORT)
                    .show();
        });
    }

    @Override
    public void setQuoteBody(String quoteBody) {
        mQuoteText.setText(quoteBody);
    }

    @Override
    public void setQuoteMovie(String movie) {
        mMovie.setText(movie);
    }

    @Override
    public void setQuotedCharacter(String quotedCharacter) {
        mQuotedCharacter.setText(quotedCharacter);
    }

    @Override
    public void setRating(float rating) {
        mQuoteRating.setRating(rating);
    }

    @Override
    public void changeButton() {
        mButton.setText("Update");
    }

    @Override
    public void showError(IllegalArgumentException e) {
        getActivity().runOnUiThread( () -> {
            Toast.makeText(getContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        });
    }


    @OnClick(R.id.btn_button)
    public void onClick(){
        try {

            String quoteText = mQuoteText.getText().toString();
            String quoteMovie = mMovie.getText().toString();
            String quotedCharacter = mQuotedCharacter.getText().toString();
            float rating = mQuoteRating.getRating();
            mPresenter.generateQuote(quoteText, quoteMovie, quotedCharacter, rating);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
