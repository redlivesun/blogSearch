package com.blog.search.controller;

import com.blog.search.application.SearchFacadeService;
import com.blog.search.domain.blog.Blogs;
import com.blog.search.domain.keyword.Keywords;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class SearchController {

    private final SearchFacadeService service;

    @GetMapping("/search/v1/blogs")
    public Blogs getBlogs(@RequestParam String query,
                          @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "10") int size,
                          @RequestParam(defaultValue = "R") String sort) {
        return service.getBlogs(query, page, size, SortType.fromString(sort));
    }

    @GetMapping("/search/v1/keywords")
    public Keywords getTop10Keywords() {
        return service.getTop10Keywords();
    }

}
