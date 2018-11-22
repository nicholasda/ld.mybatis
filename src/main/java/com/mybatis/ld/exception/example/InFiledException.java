package com.mybatis.ld.exception.example;

import com.mybatis.ld.exception.ExampleException;

/**
 * Created with IntelliJ IDEA.
 * User: satant
 * Date: 2018/3/28
 * Time: 10:42
 * Description:
 */
public class InFiledException extends ExampleException {

    public InFiledException(){

    }

    public InFiledException(String message){
        super(message);
    }
}