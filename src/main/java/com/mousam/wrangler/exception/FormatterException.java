package com.mousam.wrangler.exception;

public class FormatterException extends Exception{
    private static final long serialVersionUID = 1L;

    public static final String formatException = "Formatter exception!!";

    public FormatterException() {
    }

    public FormatterException(String message) {
        super(message);
    }

    public FormatterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FormatterException(Throwable cause) {
        super(cause);
    }
}
