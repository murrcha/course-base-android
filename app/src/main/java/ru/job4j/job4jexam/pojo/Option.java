package ru.job4j.job4jexam.pojo;

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
    private final int mId;

    /**
     * text option
     */
    private final String mText;

    /**
     * init option
     */
    public Option(int id, String text) {
        this.mId = id;
        this.mText = text;
    }

    /**
     * getId
     * @return id
     */
    public int getId() {
        return mId;
    }

    /**
     * getText
     * @return text
     */
    public String getText() {
        return mText;
    }
}
