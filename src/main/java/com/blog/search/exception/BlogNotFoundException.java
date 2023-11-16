package com.blog.search.exception;

public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException() {
        super("Blogs is empty.");
    }
}
