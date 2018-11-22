package com.mybatis.ld.exception.example;

import com.mybatis.ld.exception.ExampleException;

/**
 * Created with IntelliJ IDEA.
 * User: satant
 * Date: 2018/3/28
 * Time: 9:26
 * Description:
 */
public class NoUpdateFieldException extends ExampleException{

    public NoUpdateFieldException(){

    }

    public NoUpdateFieldException(String message){
        super(message);
    }
}