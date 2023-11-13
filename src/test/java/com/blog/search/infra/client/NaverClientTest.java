package com.blog.search.infra.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Value("${client.naver.clientId}")
    String clientId;
    @Value("${client.naver.clientSecret}")
    String clientSecret;
    @Autowired
    private NaverBlogClient client;

    @Test
    void getBlogsTest() {
        //given, when
        var blogs = client.getBlogs(clientId, clientSecret, "용산구", 10, 1, "sim");
        //then
        Assertions.assertThat(blogs).isNotNull().extracting("items").asList().hasSize(10);
    }
}
