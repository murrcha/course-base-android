package ru.job4j.job4jexam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * HintActivity
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since
 */
public class HintActivity extends AppCompatActivity {

    /**
     * Extra key for question and answer
     */
    private static final String EXTRA_QUESTION = "question";
    private static final String EXTRA_ANSWER = "answer";

    /**
     * UI elements
     */
    private TextView questionText;
    private TextView answerText;
    private Button backButton;

    /**
     * Method getDataFromIntent get question and answer from intent
     */
    private void getDataFromIntent() {
        Intent intent = getIntent();
        String question = intent.getStringExtra(EXTRA_QUESTION);
        int answer = intent.getIntExtra(EXTRA_ANSWER, 0);
        if (question != null) {
            questionText.setText(question);
        }
        answerText.setText(String.valueOf(answer));
    }

    /**
     * Method newIntent create new intent
     * @param packageContext context
     * @param question question
     * @param answer answer
     * @return intent
     */
    public static Intent newIntent(Context packageContext, String question, int answer) {
        Intent intent = new Intent(packageContext, HintActivity.class);
        intent.putExtra(EXTRA_QUESTION, question);
        intent.putExtra(EXTRA_ANSWER, answer);
        return intent;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        questionText = findViewById(R.id.question_text);
        answerText = findViewById(R.id.answer_text);
        backButton = findViewById(R.id.back_button);
        getDataFromIntent();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
