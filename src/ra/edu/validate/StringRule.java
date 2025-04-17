package ra.edu.validate;

public class StringRule {
    private final int maxLength;
    private String messageError;

    public StringRule(int maxLength, String messageError) {
        this.maxLength = maxLength;
        this.messageError = messageError;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getMessageError() {
        return messageError;
    }

    public boolean isValidString(String value) {
        if (value == null) {
            return false;
        }
        return value.length() <= this.maxLength;
    }
}
