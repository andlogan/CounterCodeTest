package com.codeTest.servlet;

public class counterObject {

    static int count = 0;

    public synchronized int getCount(){
        return count;
    }

    public synchronized void incrementCount(int increment){
        count = count + increment;
    }

    public synchronized void decrementCount(int decrement){
        count = count - decrement;
    }
}