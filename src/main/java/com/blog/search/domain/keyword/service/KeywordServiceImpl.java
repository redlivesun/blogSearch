package com.blog.search.domain.keyword.service;

import com.blog.search.domain.keyword.Keyword;
import com.blog.search.domain.keyword.Keywords;
import com.blog.search.infra.persistence.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository keywordRepository;

    @Override
    public void upsertKeyword(String keyword) {
        keywordRepository.findKeywordByKeyword(keyword).ifPresentOrElse(k1 -> {
            k1.increaseCount();
            keywordRepository.save(k1);
        }, () -> keywordRepository.save(new Keyword(keyword, 1L)));
    }

    @Override
    public Keywords getTop10Keywords() {
        return Keywords.of(keywordRepository.findTop10ByOrderByCountDesc());
    }
}
