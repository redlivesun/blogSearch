package com.blog.search.infra.client;

import com.blog.search.controller.SearchRequestParam;
import com.blog.search.infra.client.dto.KakaoBlogDTO;
import com.blog.search.infra.client.dto.NaverBlogDTO;

public interface BlogApiService {
    KakaoBlogDTO getKakaoBlogs(SearchRequestParam requestParam);
    NaverBlogDTO getNaverBlogs(SearchRequestParam requestParam);
}
