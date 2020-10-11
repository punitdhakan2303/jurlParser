package com.challengeone.wcs.exception;

public class URLParserException extends Exception{

    /**
     * Default Constructor
     */
    public URLParserException() {
    }

    /**
     * Constructor with a specified detailed message
     *
     * @param   message   the detail message.
     */
    public URLParserException(String message) {
        super(message);
    }
}

