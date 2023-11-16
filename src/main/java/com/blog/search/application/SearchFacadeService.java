package com.blog.search.application;

import com.blog.search.controller.SearchRequestParam;
import com.blog.search.domain.blog.Blogs;
import com.blog.search.domain.keyword.Keywords;

public interface SearchFacadeService {
    Blogs getBlogs(SearchRequestParam requestParam);

    Keywords getTop10Keywords();
}
