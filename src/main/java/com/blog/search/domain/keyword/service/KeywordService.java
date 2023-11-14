package com.blog.search.domain.keyword.service;

import com.blog.search.domain.keyword.Keywords;

public interface KeywordService {
    void upsertKeyword(String keyword);

    Keywords getTop10Keywords();
}
