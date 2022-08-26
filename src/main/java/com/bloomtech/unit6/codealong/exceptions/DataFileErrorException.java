package com.bloomtech.unit6.codealong.exceptions;

public class DataFileErrorException extends RuntimeException {
        public DataFileErrorException() { super(); }
        public DataFileErrorException(String errorMessage) {
                super(errorMessage);
               }
}
