package com.camarederic.entertainingmath;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class ActivitySubtraction extends AppCompatActivity {

    TextView score2;
    TextView life2;
    TextView time2;
    TextView question2;
    EditText answer2;
    Button buttonOk2;
    Button buttonNextQuestion2;

    Random random = new Random();

    int number1;
    int number2;
    int realAnswer2;
    int userAnswer2;
    int userScore2 = 0;
    int userLife2 = 3;

    CountDownTimer timer2;
    private static final long START_TIMER_IN_MILIS_2 = 60000;
    Boolean timer_running2;
    long time_left_in_milis2 = START_TIMER_IN_MILIS_2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        score2 = findViewById(R.id.text_view_score2);
        life2 = findViewById(R.id.text_view_life2);
        time2 = findViewById(R.id.text_view_time2);
        question2 = findViewById(R.id.text_view_question2);
        answer2 = findViewById(R.id.edit_text_answer2);
        buttonOk2 = findViewById(R.id.button_ok2);
        buttonNextQuestion2 = findViewById(R.id.button_question2);

        gameContinue();

        buttonOk2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userAnswer2 = Integer.valueOf(answer2.getText().toString());

                pauseTimer();


                if (userAnswer2 == realAnswer2){
                    userScore2 = userScore2 + 10;
                    score2.setText("" + userScore2);
                    question2.setText(R.string.text_congratulations_2);


                }else {
                    userLife2 = userLife2 - 1;
                    life2.setText("" + userLife2);
                    question2.setText(R.string.text_sorry_2);

                }


            }
        });

        buttonNextQuestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answer2.setText("");
                resetTimer();

                if (userLife2 <= 0){

                    Toast.makeText(getApplicationContext(), R.string.toast_subtraction_message, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ActivitySubtraction.this, Result2.class);
                    intent.putExtra("score_2", userScore2);
                    startActivity(intent);
                    finish();

                }else {

                    gameContinue();

                }


            }
        });
    }

    public void gameContinue(){
        number1 = random.nextInt(100);
        number2 = random.nextInt(100);

        realAnswer2 = number1 - number2;
        question2.setText(number1 + " - " + number2);
        startTimer();

    }

    public void startTimer(){
        timer2 = new CountDownTimer(time_left_in_milis2, 1000) {
            @Override
            public void onTick(long millisUntiFinished) {

                time_left_in_milis2 = millisUntiFinished;
                updateText();

            }

            @Override
            public void onFinish() {

                timer_running2 = false;
                pauseTimer();
                resetTimer();
                updateText();
                userLife2 = userLife2 - 1;
                life2.setText("" + userLife2);
                question2.setText(R.string.text_time_is_up_2);

            }
        }.start();

        timer_running2 = true;
    }

    private void updateText(){
        int second = (int) ((time_left_in_milis2 / 1000) % 60);
        String time_left = String.format(Locale.getDefault(), "%02d", second);
        time2.setText(time_left);

    }

    private void pauseTimer() {
        timer2.cancel();
        timer_running2 = false;

    }

    private void resetTimer(){
        time_left_in_milis2 = START_TIMER_IN_MILIS_2;
        updateText();

    }
}