package com.example.mihai.moviequotesapp.views.contracts;

import com.example.mihai.moviequotesapp.models.Quote;

import java.io.IOException;

public interface GenerateQuoteContracts {

    interface View {

        void setPresenter(Presenter mPresenter);
        void showToast(Quote quote);
        String getQuoteBody();
        String getQuoteMovie();
        String getQuotedCharacter();
        float getRating();
    }

    interface Presenter {

        void generateQuote() throws IOException;

        void setView(View view);
    }

}
