package com.blog.search.infra.client;

import com.blog.search.controller.SortType;
import com.blog.search.infra.client.dto.KakaoBlogDTO;
import com.blog.search.infra.client.dto.NaverBlogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BlogApiServiceImpl implements BlogApiService {

    private final KakaoBlogClient kakaoBlogClient;
    private final NaverBlogClient naverBlogClient;

    @Value("${client.kakao.authorization}")
    String authorization;

    @Value("${client.naver.clientId}")
    String clientId;

    @Value("${client.naver.clientSecret}")
    String clientSecret;

    @Override
    public KakaoBlogDTO getKakaoBlogs(String keyword, int page, int size, SortType sortType) {
        return kakaoBlogClient.getBlogs(authorization, keyword, size, page, sortType.getKakaoSortType());
    }

    @Override
    public NaverBlogDTO getNaverBlogs(String keyword, int page, int size, SortType sortType) {
        return naverBlogClient.getBlogs(clientId,
                                        clientSecret,
                                        keyword,
                                        size,
                                        getStart(page, size),
                                        sortType.getNaverSortType());
    }

    private int getStart(int page, int size) {
        return page * size;
    }
}
