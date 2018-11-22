package com.mybatis.ld.example;


import com.mybatis.ld.exception.ExampleException;

/**
 * @author satant
 */
@FunctionalInterface
public interface IExample {
    public void end() throws ExampleException;
}
