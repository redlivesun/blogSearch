package com.blog.search.infra.client;

import com.blog.search.controller.SortType;
import com.blog.search.infra.client.dto.KakaoBlogDTO;
import com.blog.search.infra.client.dto.NaverBlogDTO;

public interface BlogApiService {
    KakaoBlogDTO getKakaoBlogs(String keyword, int page, int size, SortType sortType);
    NaverBlogDTO getNaverBlogs(String keyword, int page, int size, SortType sortType);
}
