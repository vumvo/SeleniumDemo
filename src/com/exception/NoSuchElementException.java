package com.exception;



/**
 * Created by vumvo on 10/21/2015.
 */
public class NoSuchElementException extends RuntimeException {
    String locator;

    public NoSuchElementException(String locator){
        super();
        this.locator = locator;
    }

    @Override
    public String getMessage(){
        StackTraceElement[] stack = this.getStackTrace();
        for (StackTraceElement e : stack){
            String pageObjectPattern = ".pageObjects.";
            if (e.getClassName().contains(pageObjectPattern)){
                String className = e.getClassName();
                String message = className.substring(className.indexOf(pageObjectPattern)+pageObjectPattern.length());
                message = "No such Element with locator [" +this.locator.toString() + "] - Page: " + message + "!!!";
                return message;
            }
        }
        return super.getMessage();
    }

}
