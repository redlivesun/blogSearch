package com.blog.search.infra.client.dto;

public record NaverBlogItem(
        String title,
        String link,
        String description,
        String bloggername,
        String bloggerlink,
        String postdate
) {
}
