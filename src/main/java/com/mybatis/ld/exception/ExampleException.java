package com.mybatis.ld.exception;

@SuppressWarnings("serial")
public class ExampleException extends RuntimeException{

    public ExampleException(){

    }
    public ExampleException(String message) {
        super(message);
    }
}
