package com.blog.search.infra.client.dto;

import java.util.List;

public record NaverBlogDTO(String lastBuildDate,
                           long total,
                           int start,
                           int display,
                           List<NaverBlogItemDTO> items) {
    public static NaverBlogDTO emptyOf() {
        return new NaverBlogDTO(null, 0, 0, 0, null);
    }

    public boolean isEmpty() {
        return total <= 0 || items == null || items.isEmpty();
    }
}
