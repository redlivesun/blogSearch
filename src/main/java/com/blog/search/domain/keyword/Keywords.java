package com.blog.search.domain.keyword;

import java.util.List;

public record Keywords(List<Keyword> keywords) {
    public static Keywords of(List<Keyword> list) {
        return list == null || list.isEmpty()
                ? new Keywords(List.of())
                : new Keywords(list);
    }
}
