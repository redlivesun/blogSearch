package com.blog.search.application;

import com.blog.search.controller.SortType;
import com.blog.search.domain.blog.Blogs;
import com.blog.search.domain.keyword.Keywords;

public interface SearchFacadeService {
    Blogs getBlogs(String query, int page, int size, SortType sortType);

    Keywords getTop10Keywords();
}
