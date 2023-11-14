package com.blog.search.infra.persistence;

import com.blog.search.domain.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    Optional<Keyword> findKeywordByKeyword(String keyword);

    List<Keyword> findTop10ByOrderByCountDesc();
}
