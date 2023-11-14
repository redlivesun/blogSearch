package com.blog.search.application;

import com.blog.search.controller.SortType;
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
    public Blogs getBlogs(String query, int page, int size, SortType sortType) {
        keywordService.upsertKeyword(query);
        return blogService.getBlogs(query, page, size, sortType);
    }

    @Override
    public Keywords getTop10Keywords() {
        return keywordService.getTop10Keywords();
    }
}
