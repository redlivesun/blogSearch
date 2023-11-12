package com.blog.search.infra.client.dto;

import java.util.Date;

public record KakaoBlogDocumentDTO(String blogname,
                                   String contents,
                                   Date datetime,
                                   String thumbnail,
                                   String title,
                                   String url) {
}
