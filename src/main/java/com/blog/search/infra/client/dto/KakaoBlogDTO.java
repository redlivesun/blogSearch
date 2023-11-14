package com.blog.search.infra.client.dto;

import java.util.List;

public record KakaoBlogDTO(List<KakaoBlogDocumentDTO> documents,
                           KakaoBlogMetaDTO meta) {

    public static KakaoBlogDTO emptyOf() {
        return new KakaoBlogDTO(null, null);
    }

    public boolean isEmpty() {
        return documents == null || documents().isEmpty() || meta == null;
    }
}
