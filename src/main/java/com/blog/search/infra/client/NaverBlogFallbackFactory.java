package com.blog.search.infra.client;

import com.blog.search.infra.client.dto.NaverBlogDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NaverBlogFallbackFactory implements FallbackFactory<NaverBlogClient> {
    @Override
    public NaverBlogClient create(Throwable cause) {
        return (clientId, clientSecret, query, display, start, sort) -> {
            log.error("app-search -> Naver blog search error, query={}, size={}, page={}, sort={}, {}",
                      query,
                      display,
                      start,
                      sort,
                      cause.getStackTrace());
            return NaverBlogDTO.emptyOf();
        };
    }
}
