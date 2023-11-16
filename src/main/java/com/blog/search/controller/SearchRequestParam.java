package com.blog.search.controller;

import lombok.Builder;

@Builder
public record SearchRequestParam(String query, int size, int page, SortType sort) {

    public static SearchRequestParam of(String query, int size, int page, String sort) {
        return SearchRequestParam.builder()
                                 .query(query)
                                 .size(size)
                                 .page(page)
                                 .sort(SortType.fromString(sort))
                                 .build();
    }

    public String getCacheKey() {
        return query + ":" + page + ":" + size + ":" + sort.getCode();
    }

    public int getStart() {
        return page * size;
    }
}
