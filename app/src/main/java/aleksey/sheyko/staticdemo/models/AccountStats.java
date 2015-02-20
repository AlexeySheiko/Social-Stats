package aleksey.sheyko.staticdemo.models;

import java.io.Serializable;

public class AccountStats implements Serializable {
    private int mId;
    private String mLabel;
    private int mValue;

    public AccountStats() {
    }

    public AccountStats(String label, int value) {
        mLabel = label;
        mValue = value;
    }

    public AccountStats(int id, String label, int value) {
        mId = id;
        mLabel = label;
        mValue = value;
    }

    public int getId() {
        return mId;
    }

    public String getLabel() {
        return mLabel;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }
}
