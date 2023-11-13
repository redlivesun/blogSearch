package com.blog.search.domain.exception;

public class KeywordNotFoundException extends RuntimeException{
    public KeywordNotFoundException() {
        super("No such keyword");
    }
}
