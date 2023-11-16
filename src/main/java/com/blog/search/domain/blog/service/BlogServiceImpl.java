package com.blog.search.domain.blog.service;

import com.blog.search.controller.SearchRequestParam;
import com.blog.search.domain.blog.Blogs;
import com.blog.search.exception.BlogNotFoundException;
import com.blog.search.infra.client.BlogApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogApiService blogApiService;

    @Override
    @Cacheable(cacheNames = "blogs", key = "#p0.getCacheKey()",
               unless = "#result.isEmpty()")
    public Blogs getBlogs(SearchRequestParam requestParam) {
        var kakaoBlogs = blogApiService.getKakaoBlogs(requestParam);
        if (kakaoBlogs.isEmpty()) {
            var naverBlogs = blogApiService.getNaverBlogs(requestParam);
            if (naverBlogs.isEmpty()) {
                throw new BlogNotFoundException();
            }
            return Blogs.from(blogApiService.getNaverBlogs(requestParam));
        }
        return Blogs.from(kakaoBlogs);
    }
}
