package com.blog.search.infra.persistence;

import com.blog.search.domain.keyword.Keyword;
import com.blog.search.exception.KeywordNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KeywordRepositoryTest {

    private static final String TEST_KEYWORD_1 = "뉴욕";

    @Autowired
    KeywordRepository keywordRepository;

    @BeforeEach
    void beforeEach() {
        var keyword = getTestMock(TEST_KEYWORD_1);
        var result = keywordRepository.findKeywordByKeyword(TEST_KEYWORD_1);
        if (result.isEmpty()) {
            keywordRepository.save(keyword);
        }
    }

    @Test
    void findOneTest() {
        //given, when
        var keyword = keywordRepository.findKeywordByKeyword(TEST_KEYWORD_1).orElseThrow(KeywordNotFoundException::new);
        //then
        Assertions.assertThat(keyword).extracting("keyword").isEqualTo(TEST_KEYWORD_1);
    }

    @Test
    void increaseTest() {
        //given
        var keyword = keywordRepository.findKeywordByKeyword(TEST_KEYWORD_1).orElseThrow(KeywordNotFoundException::new);
        //when
        keyword.increaseCount();
        keywordRepository.save(keyword);
        //then
        var result = keywordRepository.findKeywordByKeyword(TEST_KEYWORD_1).orElseThrow(KeywordNotFoundException::new);
        Assertions.assertThat(result).extracting("count").isEqualTo(2L);
    }

    @Test
    void saveTest() {
        //given
        Keyword keyword = getTestMock("잠실");
        keywordRepository.save(keyword);
        //when
        var result = keywordRepository.findKeywordByKeyword("잠실").orElseThrow(KeywordNotFoundException::new);
        //then
        Assertions.assertThat(result).isNotNull();
    }

    private Keyword getTestMock(String testKeyword1) {
        return new Keyword(testKeyword1, 1);
    }
}
