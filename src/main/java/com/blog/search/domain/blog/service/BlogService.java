package com.blog.search.domain.blog.service;

import com.blog.search.controller.SearchRequestParam;
import com.blog.search.domain.blog.Blogs;

public interface BlogService {
    Blogs getBlogs(SearchRequestParam requestParam);
}
