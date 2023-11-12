package com.blog.search.infra.client;

import com.blog.search.infra.client.dto.KakaoBlogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoBlogClient",
             url = "https://dapi.kakao.com",
             fallbackFactory = KakaoBlogFallbackFactory.class)
public interface KakaoBlogClient {

    @GetMapping("/v2/search/blog")
    KakaoBlogDTO getBlogs(@RequestHeader("Authorization") String authorization,
                          @RequestParam("query") String query,
                          @RequestParam("size") int size,
                          @RequestParam("page") int page,
                          @RequestParam("sort") String sort);

}
