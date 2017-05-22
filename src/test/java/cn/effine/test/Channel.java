package cn.effine.test;

/**
 * Created by effine on 2/8/17.
 */
public enum Channel {

    SMS(1, "SMS"),
    EMAIL(2, "EMAIL"),
    SITE(3, "site"),
    PUSH(4, "push");

    private int id;
    private String content;

    private Channel(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
