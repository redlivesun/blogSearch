package com.blog.search.domain.keyword;

import com.blog.search.domain.keyword.service.KeywordService;
import com.blog.search.infra.persistence.KeywordRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeywordServiceTest {

    @Autowired
    private KeywordService keywordService;

    @Autowired
    private KeywordRepository keywordRepository;

    @BeforeEach
    void beforeEach() {
        keywordService.upsertKeyword("북촌");
    }

    @Test
    void insertKeywordTest() {
        keywordService.upsertKeyword("충주시");
        var actual = keywordRepository.findKeywordByKeyword("충주시").orElse(new Keyword());
        Assertions.assertThat(actual).isNotNull().extracting("count").isEqualTo(1L);
    }

    @Test
    void updateKeywordTest() {
        keywordService.upsertKeyword("북촌");
        var actual = keywordRepository.findKeywordByKeyword("북촌").orElse(new Keyword());
        Assertions.assertThat(actual).isNotNull().extracting("count").isNotEqualTo(1L);
    }

}
