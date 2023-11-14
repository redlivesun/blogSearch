package com.blog.search.infra.client.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public record KakaoBlogDocumentDTO(String blogname,
                                   String contents,
                                   Date datetime,
                                   String thumbnail,
                                   String title,
                                   String url) {

    public LocalDateTime getUpdatedAt() {
        return Objects.isNull(datetime)
                ? LocalDateTime.now()
                : datetime.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
