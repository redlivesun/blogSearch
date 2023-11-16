package com.blog.search.application;

import com.blog.search.controller.SearchRequestParam;
import com.blog.search.domain.blog.Blogs;
import com.blog.search.domain.blog.service.BlogService;
import com.blog.search.domain.keyword.Keywords;
import com.blog.search.domain.keyword.service.KeywordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchFacadeServiceImpl implements SearchFacadeService {

    private final BlogService blogService;
    private final KeywordService keywordService;

    @Override
    public Blogs getBlogs(SearchRequestParam requestParam) {
        keywordService.upsertKeyword(requestParam.query());
        return blogService.getBlogs(requestParam);
    }

    @Override
    public Keywords getTop10Keywords() {
        return keywordService.getTop10Keywords();
    }
}
