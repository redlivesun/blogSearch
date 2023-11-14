package com.blog.search.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum SortType {

    RECENCY("R"),
    ACCURACY("A");

    private static final Map<String, SortType> TYPE_MAP = Arrays.stream(values())
                                                                .collect(Collectors.toMap(SortType::getCode,
                                                                                          Function.identity()));

    private static final String KAKAO_RECENCY = "recency";
    private static final String KAKAO_ACCURACY = "accuracy";
    private static final String NAVER_RECENCY = "date";
    private static final String NAVER_ACCURACY = "sim";

    private String code;

    SortType(String code) {
        this.code = code;
    }

    public static SortType fromString(String type) {
        return TYPE_MAP.getOrDefault(type, ACCURACY);
    }

    public String getKakaoSortType() {
        return switch (this) {
            case RECENCY -> KAKAO_RECENCY;
            case ACCURACY -> KAKAO_ACCURACY;
        };
    }

    public String getNaverSortType() {
        return switch (this) {
            case RECENCY -> NAVER_RECENCY;
            case ACCURACY -> NAVER_ACCURACY;
        };
    }
}
