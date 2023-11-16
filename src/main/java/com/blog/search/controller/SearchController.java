package com.blog.search.controller;

import com.blog.search.application.SearchFacadeService;
import com.blog.search.domain.blog.Blogs;
import com.blog.search.domain.keyword.Keywords;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
public class SearchController {

    private final SearchFacadeService service;

    @GetMapping("/search/v1/blogs")
    public Blogs getBlogs(@RequestParam
                          @NotEmpty(message = "검색어가 입력되지 않았습니다.") String query,

                          @RequestParam(defaultValue = "1")
                          @Min(value = 1, message = "페이지는 1보다 커야합니다.")
                          @Max(value = 50, message = "페이지는 50보다 작아야 합니다.") int page,

                          @RequestParam(defaultValue = "10")
                          @Min(value = 1, message = "페이지 사이즈는 1보다 커야합니다.")
                          @Max(value = 50, message = "페이지 사이즈는 50보다 작아야 합니다.") int size,

                          @RequestParam(defaultValue = "R")
                          @Pattern(regexp = "^[RA]$",
                                   message = "정렬 방식은 R, A 로만 입력해주세요.") String sort) {
        return service.getBlogs(SearchRequestParam.of(query, size, page, sort));
    }

    @GetMapping("/search/v1/keywords")
    public Keywords getTop10Keywords() {
        return service.getTop10Keywords();
    }

}
