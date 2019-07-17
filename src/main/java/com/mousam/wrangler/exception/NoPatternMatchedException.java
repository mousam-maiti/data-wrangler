package com.mousam.wrangler.exception;

public class NoPatternMatchedException extends Exception{
    private static final long serialVersionUID = 1L;

    public static final String noPatternFound = "Provided pattern doesn't match the passed line.";

    public NoPatternMatchedException() {
    }

    public NoPatternMatchedException(String message) {
        super(message);
    }

    public NoPatternMatchedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPatternMatchedException(Throwable cause) {
        super(cause);
    }
}
