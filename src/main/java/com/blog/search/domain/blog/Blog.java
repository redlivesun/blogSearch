package com.blog.search.domain.blog;

import com.blog.search.infra.client.dto.KakaoBlogDocumentDTO;
import com.blog.search.infra.client.dto.NaverBlogItemDTO;

import java.time.LocalDateTime;

public record Blog(String title,
                   String contents,
                   String blogger,
                   String link,
                   LocalDateTime updatedAt) {
    public static Blog from(KakaoBlogDocumentDTO k) {
        return new Blog(k.title(), k.contents(), k.blogname(), k.url(), k.getUpdatedAt());
    }

    public static Blog from(NaverBlogItemDTO n) {
        return new Blog(n.title(), n.description(), n.bloggername(), n.link(), n.getUpdatedAt());
    }
}
