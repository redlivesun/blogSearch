package com.blog.search.domain.keyword;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record Keywords(List<Keyword> keywords) {
    public static Keywords of(List<Keyword> list) {
        return list == null || list.isEmpty()
                ? new Keywords(List.of())
                : new Keywords(list);
    }

    @JsonIgnore
    public boolean isEmpty() {
        return keywords == null || keywords.isEmpty();
    }
}
