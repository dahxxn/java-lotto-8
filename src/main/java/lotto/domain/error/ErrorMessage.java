package lotto.domain.error;

public enum ErrorMessage {
    PURCHASE_AMOUNT_ERROR_NOT_NUMERIC("구입 금액은 숫자여야 합니다."),
    PURCHASE_AMOUNT_ERROR_NOT_POSITIVE("구입 금액은 양수여야 합니다."),
    PURCHASE_AMOUNT_ERROR_OUT_OF_RANGE("구입 금액은 최대 9223372036854775807이하 여야 합니다."),
    PURCHASE_AMOUNT_ERROR_INVALID_UNIT("구입 금액은 1000원 단위여야 합니다."),

    LOTTO_ERROR_NOT_SIX_COUNT("로또 번호는 6개여야 합니다."),
    LOTTO_ERROR_NUMBER_OUT_OF_RANGE("로또 번호는 1과 45사이의 숫자여야 합니다."),
    LOTTO_ERROR_NUMBER_DUPLICATE("로또 번호는 중복되지 않는 숫자여야 합니다."),

    WINNING_LOTTO_ERROR_NOT_NUMERIC("당첨 번호는 숫자여야 합니다."),

    BONUS_NUMBER_ERROR_NOT_NUMERIC("보너스 번호는 숫자여야 합니다."),
    BONUS_NUMBER_ERROR_DUPLICATE("보너스 번호는 당첨 번호와 중복되지 않은 숫자여야 합니다."),
    BONUS_NUMBER_ERROR_OUT_OF_RANGE("보너스 번호는 1과 45사이의 숫자여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return PREFIX + message;
    }

}
