package lotto.view;

public class OutputView {
    public static void printErrorMessage(final IllegalArgumentException error) {
        printMessage(error.getMessage());
    }

    private static void printMessage(final String message) {
        System.out.println(message);
    }
}
