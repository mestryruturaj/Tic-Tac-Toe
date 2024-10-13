package com.tic_tac_toe.utility;

import com.tic_tac_toe.exception.GameConstraintViolationException;
import com.tic_tac_toe.model.ErrorInfo;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static com.tic_tac_toe.enums.MessageColor.RED_TEXT;
import static com.tic_tac_toe.enums.MessageColor.RESET;

public class InputOutputUtility {
    private static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
    private static final String QUIT = ":quit;";

    public static void writeToConsole(String message) {
        System.out.printf(message);
    }

    public static void writeErrorInfo(ErrorInfo errorInfo) {
        System.out.printf(RED_TEXT.getColor() + errorInfo.getErrorMessage() + RESET.getColor(), errorInfo.getErrorMessageFormatArgs());
    }

    public static String readInputOrQuit(String inputPrompt) {
        String input = null;
        while (input == null) {
            System.out.printf(inputPrompt);
            input = scanner.nextLine();
            if (!validateInput(input)) {
                input = null;
            }
        }

        if (QUIT.equals(input)) {
            throw new GameConstraintViolationException(QUIT);
        }

        return input;
    }

    public static int readRowOrColInput(String inputPrompt, String errorPrompt, int lower, int upper) {
        while (true) {
            String input = readInputOrQuit(inputPrompt);
            try {
                int output = Integer.parseInt(input);
                if (output >= lower && output <= upper) {
                    return output;
                }
            } catch (NumberFormatException ignored) {
            }
            System.out.println(RED_TEXT.getColor() + errorPrompt + RESET.getColor());
        }
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static boolean checkIfQuit(String message) {
        return QUIT.equals(message);
    }

    public static boolean validateInput(String input) {
        String regexp = ".*[.\\-%].*";
        if (input.matches(regexp)) {
            System.out.println(RED_TEXT.getColor() + "The given input (" + input + ") contains prohibited special characters." + RESET.getColor());
            System.out.println(RED_TEXT.getColor() + "Please try again." + RESET.getColor());
            return false;
        }
        return true;
    }
}

/*
 * 1. Symbol should be unique, Symbol should 1-2 chars long
 * 2. Should be a number Input row should be valid, should not be a used cell
 * */
