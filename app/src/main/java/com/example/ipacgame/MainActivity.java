package com.example.ipacgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected final static  String WIN_OR_LOSE_BOOLEAN = "isWinOrLose";

    private int numberOfTryLeft = 10;
    private int numberToFind;

    private  TextView remainingTry;
    private  TextView moreOrLess;
    private Button validate;
    private  EditText numberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        remainingTry = ((TextView) findViewById(R.id.remain_try));
        moreOrLess = ((TextView) findViewById(R.id.more_or_less));
        validate = ((Button) findViewById(R.id.validate));
        numberInput = ((EditText) findViewById(R.id.enter_number));

        remainingTry.setText(getString(R.string.remain_try, numberOfTryLeft));

        numberToFind = (int) (Math.random() * 100);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!numberInput.getText().toString().isEmpty()) {
                    numberOfTryLeft--;
                    if (Integer.parseInt(numberInput.getText().toString()) == numberToFind) {
                        launchFinishView(true);
                        moreOrLess.setText("Victory");
                    } else if (Integer.parseInt(numberInput.getText().toString()) > numberToFind) {

                        moreOrLess.setText(getString(R.string.less));
                        remainingTry.setText(getString(R.string.remain_try, numberOfTryLeft));
                        displayTextWithFade();
                    } else {
                        moreOrLess.setText(getString(R.string.more));
                        remainingTry.setText(getString(R.string.remain_try, numberOfTryLeft));
                        displayTextWithFade();

                    }
                    if (numberOfTryLeft == 0) {
                        moreOrLess.setText("Perdu");
                        launchFinishView();
                    }
                }
            }
        });


        numberInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    validate.performClick();
                }
                return false;
            }
        });
    }

    private void launchFinishView() {
        launchFinishView(false);
    }

    private void launchFinishView(boolean win) {
        Intent intent = new Intent(this,FinishActivity.class);
        intent.putExtra(WIN_OR_LOSE_BOOLEAN,win);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out);
        startActivity(intent);
        finish();
    }

    private  void performClick() {
        if (!numberInput.getText().toString().isEmpty()) {
            numberOfTryLeft--;
            if (Integer.parseInt(numberInput.getText().toString()) == numberToFind) {
                launchFinishView(true);
                moreOrLess.setText("Victory");
            } else if (Integer.parseInt(numberInput.getText().toString()) > numberToFind) {

                moreOrLess.setText(getString(R.string.less));
                remainingTry.setText(getString(R.string.remain_try, numberOfTryLeft));
            } else {
                moreOrLess.setText(getString(R.string.more));
                remainingTry.setText(getString(R.string.remain_try, numberOfTryLeft));
            }
            if (numberOfTryLeft == 0) {
                moreOrLess.setText("Perdu");
                launchFinishView();
            }
        }
    }
    private void displayTextWithFade() {
        moreOrLess.setVisibility(View.VISIBLE);
        moreOrLess.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                hideTextWithFade();
            }
        }, 1000);
    }
    private void hideTextWithFade() {
        moreOrLess.setVisibility(View.INVISIBLE);
        moreOrLess.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));
    }
}
