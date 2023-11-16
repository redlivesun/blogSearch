package com.blog.search.exception;

public class KeywordNotFoundException extends RuntimeException{
    public KeywordNotFoundException() {
        super("No such keyword");
    }
}
