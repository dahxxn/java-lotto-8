package lotto.domain.error;

public enum ErrorMessage {
    PURCHASE_AMOUNT_ERROR_NOT_NUMERIC("구입 금액은 숫자여야 합니다."),
    ;

    private static final String PREFIX = "[ERROR] ";
    private String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

}
