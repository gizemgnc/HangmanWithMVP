package com.example.hangmanwithmvp;

import android.text.TextUtils;
import android.widget.EditText;


public class MainActivityPresenter implements  MainActivityContract.Presenter {

    private MainActivityContract.View view;
    private GameDataManagement gameDataManagement;

    public MainActivityPresenter(GameDataManagement gameDataManagement){
        this.gameDataManagement = gameDataManagement;
    }

    @Override
    public void setView(MainActivityContract.View view) {
        this.view = view;

    }

    @Override
    public void created() {
        this.view.bindViews();
        this.view.initOnClicks();
        this.prepareViewForGame();
    }

    private void prepareViewForGame() {
        this.gameDataManagement.initGameData();
        this.view.initGameView(this.gameDataManagement.getCurrentWord(),this.gameDataManagement.getRemainingLife());
        this.view.initInputArea();
    }

    @Override
    public void onTryClicked(EditText et_char_input) {

        if(!(et_char_input.getText().toString().equals(""))  && !TextUtils.isDigitsOnly(et_char_input.getText())){
            String text = et_char_input.getText().toString();
            this.view.initInputArea();
            this.gameDataManagement.clearPositionList();
            this.gameDataManagement.checkIsWordContainsLetter(text);

            if(!(gameDataManagement.getPositionListArray().isEmpty())){
                this.gameDataManagement.changeCurrentWord(text);
                this.view.setCurrentWord(this.gameDataManagement.getCurrentWord());

                if(!this.gameDataManagement.isThereAnyUnderscore()){
                    this.view.initSuccessfulView();
                    this.view.initResultButtonLayout();
                }
            }else{
                int remainingLife = this.gameDataManagement.getDecreasedRemainingLife();
                this.view.setRemainingLife(remainingLife);
                if(remainingLife == 0){
                    this.view.initFailView();
                    this.view.initResultButtonLayout();
                }
            }
        }else {
            view.showPleaseEnterALetterMessage();
        }

    }

    @Override
    public void onPlayAgainClick() {
        this.prepareViewForGame();

    }

    @Override
    public void onExitClick() {
        this.view.finishActivity();

    }
}
