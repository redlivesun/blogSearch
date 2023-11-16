package com.blog.search.domain.keyword.service;

import com.blog.search.exception.KeywordNotFoundException;
import com.blog.search.domain.keyword.Keyword;
import com.blog.search.domain.keyword.Keywords;
import com.blog.search.infra.persistence.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class KeywordServiceImpl implements KeywordService {

    private final KeywordRepository keywordRepository;

    @Override
    @Transactional
    public void upsertKeyword(String keyword) {
        keywordRepository.findKeywordByKeyword(keyword).ifPresentOrElse(k1 -> {
            k1.increaseCount();
            keywordRepository.save(k1);
        }, () -> keywordRepository.save(new Keyword(keyword, 1L)));
    }

    @Override
    @Cacheable(cacheNames = "keywords",
               unless = "#result.isEmpty()")
    public Keywords getTop10Keywords() {
        var keywords = keywordRepository.findTop10ByOrderByCountDesc();
        if (keywords.isEmpty()) {
            throw new KeywordNotFoundException();
        }
        return Keywords.of(keywordRepository.findTop10ByOrderByCountDesc());
    }
}
