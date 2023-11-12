package com.blog.search.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    @GetMapping("/search/v1/blogs")
    public ResponseEntity<?> getBlogs() {
        return ResponseEntity.ok().build();
    }

}
