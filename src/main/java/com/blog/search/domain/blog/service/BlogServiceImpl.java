package com.blog.search.domain.blog.service;

import com.blog.search.controller.SortType;
import com.blog.search.domain.blog.Blogs;
import com.blog.search.infra.client.BlogApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

    private final BlogApiService blogApiService;

    @Override
    public Blogs getBlogs(String keyword, int page, int size, SortType sortType) {
        var kakaoBlogs = blogApiService.getKakaoBlogs(keyword, page, size, sortType);
        if (kakaoBlogs.isEmpty()) {
            return Blogs.from(blogApiService.getNaverBlogs(keyword, page, size, sortType));
        }
        return Blogs.from(kakaoBlogs);
    }
}
