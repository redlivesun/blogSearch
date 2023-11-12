package com.blog.search.infra.client;

import com.blog.search.infra.client.dto.NaverBlogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "naverBlogClient",
             url = "https://openapi.naver.com",
             fallbackFactory = NaverBlogFallbackFactory.class)
public interface NaverBlogClient {
    @GetMapping("/v1/search/blog.json")
    NaverBlogDTO getBlogs(@RequestHeader("X-Naver-Client-Id") String clientId,
                          @RequestHeader("X-Naver-Client-Secret") String clientSecret,
                          @RequestParam("query") String query,
                          @RequestParam("display") int display,
                          @RequestParam("start") int start,
                          @RequestParam("sort") String sort);

}
