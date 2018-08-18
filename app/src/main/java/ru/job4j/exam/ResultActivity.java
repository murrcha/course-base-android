package ru.job4j.exam;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * ResultActivity
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 0.1
 */
public class ResultActivity extends AppCompatActivity {

    /**
     * Extra key for true answer count
     */
    private static final String EXTRA_TRUE_ANSWER_COUNT = "trueAnswerCount";

    /**
     * UI element
     */
    private TextView resultText;

    /**
     * Method getDataFromIntent get true answer count from intent
     */
    private void getDataFromIntent() {
        Intent intent = getIntent();
        int trueAnswerCount = intent.getIntExtra(EXTRA_TRUE_ANSWER_COUNT, 0);
        resultText.setText(String.format("True answer count: %s из 3", trueAnswerCount));
    }

    /**
     * Method newIntent create new intent
     * @param packageContext context
     * @param trueAnswerCount count
     * @return intent
     */
    public static Intent newIntent(Context packageContext, int trueAnswerCount) {
        Intent intent = new Intent(packageContext, ResultActivity.class);
        intent.putExtra(EXTRA_TRUE_ANSWER_COUNT, trueAnswerCount);
        return intent;
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultText = findViewById(R.id.result_text);
        getDataFromIntent();
    }
}
