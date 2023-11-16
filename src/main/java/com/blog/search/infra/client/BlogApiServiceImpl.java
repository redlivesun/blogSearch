package com.blog.search.infra.client;

import com.blog.search.controller.SearchRequestParam;
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
    public KakaoBlogDTO getKakaoBlogs(SearchRequestParam requestParam) {
        return kakaoBlogClient.getBlogs(authorization,
                                        requestParam.query(),
                                        requestParam.size(),
                                        requestParam.page(),
                                        requestParam.sort().getKakaoSortType());
    }

    @Override
    public NaverBlogDTO getNaverBlogs(SearchRequestParam requestParam) {
        return naverBlogClient.getBlogs(clientId,
                                        clientSecret,
                                        requestParam.query(),
                                        requestParam.size(),
                                        requestParam.getStart(),
                                        requestParam.sort().getNaverSortType());
    }

}
