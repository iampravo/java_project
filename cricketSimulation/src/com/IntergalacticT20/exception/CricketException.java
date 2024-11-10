package com.IntergalacticT20.exception;

/**
 * Custom Checked Exception  for this application
 *
 * @author pravinkumarsingh
 * @email pravinsinghkumar@gmail.com
 */
public class CricketException extends Exception {
    public CricketException(String message) {
        super(message);
    }

    public void printErrorMessage() {
        System.out.println(this.getMessage());
    }
}
