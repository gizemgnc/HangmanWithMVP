package com.example.hangmanwithmvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View {

    private MainActivityPresenter presenter;

    private TextView tv_life;
    private LinearLayout gameLayout;
    private TextView tv_word;
    private EditText et_char_input;
    private Button btn_try;
    private Button btn_exit;
    private Button btn_again;
    private TextView tv_successful_message;
    private TextView tv_fail_message;
    private LinearLayout resultButtonLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GameDataManagement gameDataManagement = new GameDataManagement();
        this.presenter = new MainActivityPresenter(gameDataManagement);
        this.presenter.setView(this);
        this.presenter.created();
    }

    @Override
    public void bindViews() {
        tv_life = findViewById(R.id.tv_life);
        gameLayout = findViewById(R.id.ll_game);
        tv_word = findViewById(R.id.tv_word);
        et_char_input = findViewById(R.id.et_char_input);
        btn_try = findViewById(R.id.btn_try);
        btn_exit = findViewById(R.id.btn_exit);
        btn_again = findViewById(R.id.btn_play_again);
        tv_successful_message = findViewById(R.id.tv_successful_message);
        tv_fail_message = findViewById(R.id.tv_fail_message);
        resultButtonLayout = findViewById(R.id.rl_result_button);
    }

    @Override
    public void initOnClicks() {
        this.btn_try.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onTryClicked(et_char_input);
            }

        });

        this.btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onPlayAgainClick();
            }
        });

        this.btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onExitClick();
            }
        });
    }

    @Override
    public void initGameView(String currentWord, int remainingLife) {
        setGameLayoutVisibility(View.VISIBLE);
        setRemainingLifeVisibility(View.VISIBLE);
        setResultButtonLayoutVisibility(View.GONE);
        setSuccessfulMessageVisibility(View.GONE);
        setFailMessageVisibility(View.GONE);
        setRemainingLife(remainingLife);
        setCurrentWord(currentWord);

    }

    @Override
    public void initInputArea() {
        this.et_char_input.setText("");

    }

    @Override
    public void initSuccessfulView() {
        setGameLayoutVisibility(View.GONE);
        setSuccessfulMessageVisibility(View.VISIBLE);
    }

    @Override
    public void initResultButtonLayout() {
        setResultButtonLayoutVisibility(View.VISIBLE);
    }

    @Override
    public void initFailView() {
        setGameLayoutVisibility(View.GONE);
        setFailMessageVisibility(View.VISIBLE);
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public void showPleaseEnterALetterMessage() {
        Toast.makeText(this, "Lütfen Bir Harf Giriniz!", Toast.LENGTH_LONG).show();
    }


    @Override
    public void setGameLayoutVisibility(int visibility) {
        this.gameLayout.setVisibility(visibility);
    }

    @Override
    public void setRemainingLifeVisibility(int visibility) {
        this.tv_life.setVisibility(visibility);
    }

    @Override
    public void setResultButtonLayoutVisibility(int visibility) {
        this.resultButtonLayout.setVisibility(visibility);
    }

    @Override
    public void setSuccessfulMessageVisibility(int visibility) {
        this.tv_successful_message.setVisibility(visibility);

    }

    @Override
    public void setFailMessageVisibility(int visibility) {
        this.tv_fail_message.setVisibility(visibility);
    }

    @Override
    public void setRemainingLife(int remainingLife) {
        this.tv_life.setText("Kalan can: " + String.valueOf(remainingLife));
    }

    @Override
    public void setCurrentWord(String currentWord) {
        this.tv_word.setText(currentWord);
    }


}
