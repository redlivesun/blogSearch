package com.blog.search.domain.blog.service;

import com.blog.search.controller.SortType;
import com.blog.search.domain.blog.Blogs;

public interface BlogService {
    Blogs getBlogs(String keyword, int page, int size, SortType sortType);
}
