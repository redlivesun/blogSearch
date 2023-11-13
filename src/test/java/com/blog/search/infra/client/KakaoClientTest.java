package com.blog.search.infra.client;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KakaoClientTest {

    @Autowired
    KakaoBlogClient kakaoBlogClient;

    @Value("${client.kakao.authorization}")
    String authorization;

    @Test
    void callTest() {
        //given, when
        var result = kakaoBlogClient.getBlogs(authorization, "용산구", 10, 1, "accuracy");
        //then
        Assertions.assertThat(result).isNotNull().extracting("documents").asList().hasSize(10);
    }

}
