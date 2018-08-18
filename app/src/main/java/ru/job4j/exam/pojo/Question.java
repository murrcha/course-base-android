package ru.job4j.exam.pojo;

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
    private final int id;

    /**
     * text question
     */
    private final String text;

    /**
     * list options
     */
    private final List<Option> options;

    /**
     * answer for question
     */
    private final int answer;

    /**
     * init question
     */
    public Question(int id, String text, List<Option> options, int answer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    /**
     * getText
     * @return text
     */
    public String getText() {
        return this.text;
    }

    /**
     * getOptions
     * @return options
     */
    public List<Option> getOptions() {
        return options;
    }

    /**
     * getAnswer
     * @return answer
     */
    public int getAnswer() {
        return answer;
    }
}
