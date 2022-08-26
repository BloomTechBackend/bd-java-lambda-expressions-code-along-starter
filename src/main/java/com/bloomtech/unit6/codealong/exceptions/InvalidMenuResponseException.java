package com.bloomtech.unit6.codealong.exceptions;

public class InvalidMenuResponseException extends RuntimeException {
        public InvalidMenuResponseException() {
                super();
        }
        public InvalidMenuResponseException(String message) {
                super(message);
        }
}
