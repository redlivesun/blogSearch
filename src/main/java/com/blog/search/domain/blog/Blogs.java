package com.blog.search.domain.blog;

import com.blog.search.infra.client.dto.KakaoBlogDTO;
import com.blog.search.infra.client.dto.NaverBlogDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public record Blogs(List<Blog> result) {

    public static Blogs emptyOf() {
        return new Blogs(List.of());
    }

    public static Blogs from(KakaoBlogDTO k) {
        if (k.isEmpty()) {
            return emptyOf();
        }
        return new Blogs(k.documents().stream().map(Blog::from).toList());
    }

    public static Blogs from(NaverBlogDTO n) {
        if (n.isEmpty()) {
            return emptyOf();
        }
        return new Blogs(n.items().stream().map(Blog::from).toList());
    }

    @JsonIgnore
    public boolean isEmpty() {
        return result == null || result.isEmpty();
    }
}
