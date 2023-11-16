package com.blog.search.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class SearchControllerTest {

    @Autowired
    MockMvc mvc;

    @DisplayName("기본 조회 테스트")
    @Test
    void getBlogsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/search/v1/blogs").param("query", "용산구")).andExpect(status().isOk());
    }

    @DisplayName("파라미터 조회 테스트")
    @Test
    void getBlogsFullParamsTest() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("query", "용산구");
        requestParams.add("page", "1");
        requestParams.add("size", "10");
        requestParams.add("sort", "A");
        mvc.perform(MockMvcRequestBuilders.get("/search/v1/blogs").queryParams(requestParams)).andExpect(status().isOk());
    }

    @DisplayName("파라미터 query 제외 실패 테스트")
    @Test
    void getBlogs_invalid_test_1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/search/v1/blogs")).andExpect(status().isBadRequest());
    }

    @DisplayName("파라미터 page MIN 실패 테스트")
    @Test
    void getBlogs_invalid_test_2() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("query", "용산구");
        requestParams.add("page", "0");
        mvc.perform(MockMvcRequestBuilders.get("/search/v1/blogs").queryParams(requestParams))
           .andExpect(status().isBadRequest());
    }

    @DisplayName("파라미터 size MIN 실패 테스트")
    @Test
    void getBlogs_invalid_test_3() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("query", "용산구");
        requestParams.add("page", "1");
        requestParams.add("size", "0");
        mvc.perform(MockMvcRequestBuilders.get("/search/v1/blogs").queryParams(requestParams))
           .andExpect(status().isBadRequest());
    }

    @DisplayName("파라미터 sort 오입력 실패 테스트")
    @Test
    void getBlogs_invalid_test_4() throws Exception {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("query", "용산구");
        requestParams.add("page", "1");
        requestParams.add("size", "10");
        requestParams.add("sort", "K");
        mvc.perform(MockMvcRequestBuilders.get("/search/v1/blogs").queryParams(requestParams))
           .andExpect(status().isBadRequest());
    }

    @DisplayName("키워드 성공 테스트")
    @Test
    void getKeywordsTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/search/v1/blogs").param("query", "용산구")).andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/search/v1/keywords")).andExpect(status().isOk());
    }
}
