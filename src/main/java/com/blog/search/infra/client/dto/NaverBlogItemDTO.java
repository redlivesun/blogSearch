package com.blog.search.infra.client.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public record NaverBlogItemDTO(String title,
                               String link,
                               String description,
                               String bloggername,
                               String bloggerlink,
                               Date postdate) {
    public LocalDateTime getUpdatedAt() {
        return Objects.isNull(postdate)
                ? LocalDateTime.now()
                : postdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
