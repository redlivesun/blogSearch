package com.blog.search.application;

import com.blog.search.controller.SearchRequestParam;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchFacadeServiceTest {

    private static final SearchRequestParam REQUEST_PARAM = SearchRequestParam.of("서울",
                                                                                  10,
                                                                                  1,
                                                                                  "R");

    @Autowired
    private SearchFacadeService service;

    @Test
    void getBlogsTest() {
        var searchResult = service.getBlogs(REQUEST_PARAM);
        var keywordResult = service.getTop10Keywords();
        Assertions.assertThat(searchResult.isEmpty()).isFalse();
        Assertions.assertThat(keywordResult.isEmpty()).isFalse();
    }

}
