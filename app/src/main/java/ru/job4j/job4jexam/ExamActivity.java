package ru.job4j.job4jexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ExamActivity
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 0.1
 */
public class ExamActivity extends AppCompatActivity {

    /**
     * UI elements
     */
    private Button mPreviousButton;
    private Button mNextButton;
    private TextView mQuestionText;
    private RadioGroup mVariants;

    /**
     * List questions
     */
    private final List<Question> mQuestions = Arrays.asList(
            new Question(1, "How many primitive variables does Java have?",
                    Arrays.asList(
                      new Option(1, "1.1"), new Option(2, "1.2"),
                      new Option(3, "1.3"), new Option(4, "1.4")
                    ), 4
            ),
            new Question(
                    2, "What is Java Virtual Machine?",
                    Arrays.asList(
                            new Option(1, "2.1"), new Option(2, "2.2"),
                            new Option(3, "2.3"), new Option(4, "2.4")
                    ), 4
            ),
            new Question(
                    3, "What is happen if we try unboxing null?",
                    Arrays.asList(
                            new Option(1, "3.1"), new Option(2, "3.2"),
                            new Option(3, "3.3"), new Option(4, "3.4")
                    ), 4
            )
    );

    /**
     * Current position at list questions
     */
    private int mPosition = 0;

    /**
     * List answers of user
     */
    private List<Integer> mAnswers = new ArrayList<>(mQuestions.size());

    /**
     * Method fillForm fill UI elements
     */
    private void fillForm() {
        Question question = this.mQuestions.get(this.mPosition);
        mQuestionText.setText(question.getText());
        for (int index = 0; index != mVariants.getChildCount(); index++) {
            RadioButton radioButton = (RadioButton) mVariants.getChildAt(index);
            Option option = question.getOptions().get(index);
            radioButton.setId(option.getId());
            radioButton.setText(option.getText());
        }
        mVariants.clearCheck();
        mPreviousButton.setEnabled(mPosition != 0);
        mNextButton.setEnabled(false);
    }

    /**
     * Method showAnswer show in toast user's answer and correct answer
     */
    private void showAnswer() {
        int id = mVariants.getCheckedRadioButtonId();
        Question question = this.mQuestions.get(mPosition);
        Toast.makeText(this,
                String.format("Your answer is %s, correct is %S", id, question.getAnswer()),
                Toast.LENGTH_SHORT
        ).show();
    }

    /**
     * Method saveAnswer save checked answer
     */
    private void saveAnswer() {
        this.mAnswers.add(mPosition, mVariants.getCheckedRadioButtonId());
    }

    /**
     * Method restoreAnswer if pressed back button
     */
    private void restoreAnswer() {
        if (!mAnswers.isEmpty()) {
            mVariants.check(mAnswers.get(mPosition));
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        mPreviousButton = findViewById(R.id.previous);
        mNextButton = findViewById(R.id.next);
        mQuestionText = findViewById(R.id.question);
        mVariants = findViewById(R.id.variants);
        mVariants.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (mPosition != mQuestions.size() - 1) {
                    mNextButton.setEnabled(true);
                }
            }
        });
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAnswer();
                saveAnswer();
                mPosition++;
                fillForm();

            }
        });
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPosition--;
                fillForm();
                restoreAnswer();
            }
        });
        fillForm();
    }
}
