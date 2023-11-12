package com.blog.search.infra.client;

import com.blog.search.infra.client.dto.KakaoBlogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KakaoBlogFallbackFactory implements FallbackFactory<KakaoBlogClient> {
    @Override
    public KakaoBlogClient create(Throwable cause) {
        return (authorization, query, size, page, sort) -> {
            log.error("app-search -> Kakao blog search error, query={}, size={}, page={}, sort={}, {}",
                      query,
                      size,
                      page,
                      sort,
                      cause.getStackTrace());
            return KakaoBlogDTO.emptyOf();
        };
    }
}
