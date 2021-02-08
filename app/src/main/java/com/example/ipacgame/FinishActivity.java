package com.example.ipacgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FinishActivity extends AppCompatActivity {


    private Button restart;
    private Button leave;

    @Override
    protected  void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        boolean WIN_OR_LOSE_BOOLEAN =   getIntent().getBooleanExtra("isWinOrLose",true);

         restart = ((Button)findViewById(R.id.restart));
        leave = ((Button)findViewById(R.id.leave));

        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishApp();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMainView();
            }
        });
    }

    private void finishApp() {
        finish();
    }

    private void launchMainView() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
