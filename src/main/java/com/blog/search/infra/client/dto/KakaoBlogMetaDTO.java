package com.blog.search.infra.client.dto;

public record KakaoBlogMetaDTO(
        boolean is_end,
        long pageable_count,
        long total_count
) {
}
