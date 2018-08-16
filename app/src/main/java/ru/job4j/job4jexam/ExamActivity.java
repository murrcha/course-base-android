package ru.job4j.job4jexam;

import android.content.Intent;
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

import ru.job4j.job4jexam.pojo.Option;
import ru.job4j.job4jexam.pojo.Question;

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
    private Button previousButton;
    private Button nextButton;
    private Button hintButton;
    private Button backToList;
    private TextView questionText;
    private RadioGroup variantsGroup;

    /**
     * List questions
     */
    private final List<Question> questions = Arrays.asList(
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
    private int position = 0;

    /**
     * List answers of user
     */
    private final List<Integer> answers = new ArrayList<>(questions.size());

    /**
     * Count true answers
     */
    private int trueAnswerCount = 0;

    /**
     * Method fillForm fill UI elements
     */
    private void fillForm() {
        Question question = this.questions.get(this.position);
        questionText.setText(question.getText());
        for (int index = 0; index != variantsGroup.getChildCount(); index++) {
            RadioButton radioButton = (RadioButton) variantsGroup.getChildAt(index);
            Option option = question.getOptions().get(index);
            radioButton.setId(option.getId());
            radioButton.setText(option.getText());
        }
        variantsGroup.clearCheck();
        previousButton.setEnabled(position != 0);
        nextButton.setEnabled(false);
    }

    /**
     * Method showAnswer show in toast user's answer and correct answer
     */
    private void showAndCheckAnswer() {
        int id = variantsGroup.getCheckedRadioButtonId();
        Question question = this.questions.get(position);
        int answer = question.getAnswer();
        Toast.makeText(this,
                String.format("Your answer is %s, correct is %s", id, answer),
                Toast.LENGTH_SHORT
        ).show();
        if (id == answer) {
            trueAnswerCount++;
        }
    }

    /**
     * Method saveAnswer save checked answer
     */
    private void saveAnswer() {
        this.answers.add(position, variantsGroup.getCheckedRadioButtonId());
    }

    /**
     * Method restoreAnswer if pressed back button
     */
    private void restoreAnswer() {
        if (!answers.isEmpty()) {
            variantsGroup.check(answers.get(position));
        }
    }

    /**
     * ${@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);
        previousButton = findViewById(R.id.previous);
        nextButton = findViewById(R.id.next);
        hintButton = findViewById(R.id.hint_button);
        backToList = findViewById(R.id.exams_list_button);
        questionText = findViewById(R.id.question);
        variantsGroup = findViewById(R.id.variants);
        variantsGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                nextButton.setEnabled(true);
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAndCheckAnswer();
                saveAnswer();
                position++;
                if (position == questions.size()) {
                    Intent intent = ResultActivity.newIntent(ExamActivity.this, trueAnswerCount);
                    startActivity(intent);
                    position--;
                    trueAnswerCount = 0;
                } else {
                    fillForm();
                }
            }
        });
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                fillForm();
                restoreAnswer();
            }
        });
        hintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answer = questions.get(position).getAnswer();
                String question = questions.get(position).getText();
                Intent intent = HintActivity.newIntent(ExamActivity.this, question, answer);
                startActivity(intent);
            }
        });
        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        fillForm();
    }
}
