package com.blog.search.infra.persistence;

import com.blog.search.domain.Keyword;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface KeywordRepository extends PagingAndSortingRepository<Keyword, Long>, CrudRepository<Keyword, Long> {

    Optional<Keyword> findKeywordByKeyword(String keyword);

}
