package ru.job4j.job4jexam;

import java.util.List;

/**
 * Question
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 0.1
 */
public class Question {

    /**
     * id question
     */
    @SuppressWarnings("unused")
    private final int mId;

    /**
     * text question
     */
    private final String mText;

    /**
     * list options
     */
    private final List<Option> mOptions;

    /**
     * answer for question
     */
    private final int mAnswer;

    /**
     * init question
     */
    public Question(int id, String text, List<Option> options, int answer) {
        this.mId = id;
        this.mText = text;
        this.mOptions = options;
        this.mAnswer = answer;
    }

    /**
     * getText
     * @return text
     */
    public String getText() {
        return this.mText;
    }

    /**
     * getOptions
     * @return options
     */
    public List<Option> getOptions() {
        return mOptions;
    }

    /**
     * getAnswer
     * @return answer
     */
    public int getAnswer() {
        return mAnswer;
    }
}
