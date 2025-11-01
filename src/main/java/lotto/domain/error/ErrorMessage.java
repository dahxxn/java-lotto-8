package lotto.domain.error;

public enum ErrorMessage {
    PURCHASE_AMOUNT_ERROR_NOT_NUMERIC("구입 금액은 숫자여야 합니다."),
    PURCHASE_AMOUNT_ERROR_NOT_POSITIVE("구입 금액은 양수여야 합니다."),
    PURCHASE_AMOUNT_ERROR_OUT_OF_RANGE("구입 금액은 최대 9223372036854775807이하 여야 합니다."),
    PURCHASE_AMOUNT_ERROR_INVALID_UNIT("구입 금액은 1000원 단위여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

}
