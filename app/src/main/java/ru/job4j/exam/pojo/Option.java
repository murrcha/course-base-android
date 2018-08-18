package ru.job4j.exam.pojo;

/**
 * Option
 *
 * @author Ksenya Kaysheva (murrcha@me.com)
 * @since 0.1
 */
public class Option {

    /**
     * id option
     */
    private final int id;

    /**
     * text option
     */
    private final String text;

    /**
     * init option
     */
    public Option(int id, String text) {
        this.id = id;
        this.text = text;
    }

    /**
     * getId
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * getText
     * @return text
     */
    public String getText() {
        return text;
    }
}
