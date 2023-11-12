package com.blog.search.controller;

public record SearchRequestParam(
        String query,
        int size,
        int page,
        String sort
) {
}
