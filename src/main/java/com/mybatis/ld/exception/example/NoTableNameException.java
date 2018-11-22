package com.mybatis.ld.exception.example;

import com.mybatis.ld.exception.ExampleException;

/**
 * Created with IntelliJ IDEA.
 * User: satant
 * Date: 2018/3/28
 * Time: 9:15
 * Description:
 */
public class NoTableNameException extends ExampleException{

    public NoTableNameException(){

    }

    public NoTableNameException(String message){
        super(message);
    }
}