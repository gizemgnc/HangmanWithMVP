package com.example.hangmanwithmvp;

import android.widget.EditText;

public interface MainActivityContract {

    interface View {

        void bindViews();

        void initOnClicks();

        void initGameView(String currentWord, int remainingLife);

        void setGameLayoutVisibility(int visibility);

        void setRemainingLifeVisibility(int visibility);

        void setResultButtonLayoutVisibility(int visibility);

        void setSuccessfulMessageVisibility(int visibility);

        void setFailMessageVisibility(int visibility);

        void setRemainingLife(int remainingLife);

        void setCurrentWord(String currentWord);

        void initInputArea();

        void initSuccessfulView();

        void initResultButtonLayout();

        void initFailView();

        void finishActivity();

        void showPleaseEnterALetterMessage();
    }

    interface Presenter {

        void setView(View view);

        void created();

        void onTryClicked(EditText et_char_input);

        void onPlayAgainClick();

        void onExitClick();
    }
}
