package com.tic_tac_toe.model;

public class ErrorInfo {
    private String errorMessage;
    private Object[] errorMessageFormatArgs;

    public ErrorInfo(String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorMessageFormatArgs = new Object[0];
    }

    public ErrorInfo(String errorMessage, Object ...errorMessageFormatArgs) {
        this(errorMessage);
        this.errorMessageFormatArgs = errorMessageFormatArgs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object[] getErrorMessageFormatArgs() {
        return errorMessageFormatArgs;
    }

    public void setErrorMessageFormatArgs(Object[] errorMessageFormatArgs) {
        this.errorMessageFormatArgs = errorMessageFormatArgs;
    }
}
