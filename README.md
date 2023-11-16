# 블로그 검색 서비스

## 서비스 기능
1. 블로그 검색

- 키워드를 통해 블로그를 검색할 수 있어야 합니다.

- 검색 결과에서 Sorting(정확도순, 최신순) 기능을 지원해야 합니다.

- 검색 결과는 Pagination 형태로 제공해야 합니다.

- 검색 소스는 카카오 API의 키워드로 블로그 검색(https://developers.kakao.com/docs/latest/ko/daum-search/dev-guide#search-blog)을 활용합니다.

- 추후 카카오 API 이외에 새로운 검색 소스가 추가될 수 있음을 고려해야 합니다.


2. 인기 검색어 목록

- 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공합니다.
- 검색어 별로 검색된 횟수도 함께 표기해 주세요.


## 추가 기능
- 트래픽이 많고, 저장되어 있는 데이터가 많음을 염두에 둔 구현
  - Caffeine Cache 추가

- 동시성 이슈가 발생할 수 있는 부분을 염두에 둔 구현 (예시. 키워드 별로 검색된 횟수의 정확도)
  - Spring Transactional 적용

- 카카오 블로그 검색 API에 장애가 발생한 경우, 네이버 블로그 검색 API를 통해 데이터 제공
  - FeignClient fallback factory -> return empty -> Naver API 사용

## 서비스 스팩과 사용 이유
- Spring Boot 3.1.5
- JAVA 17
- Spring Cloud 2022.0.4
- Lombok
- Spring Cloud openfeign
  - Interface, Annotation 기반으로 사용이 간단함
  - call 실패 시 fallbackFactory 로 쉬운 fallback 처리 
  - Resilience4j 와 연동 가능
- Spring Cloud circuitbreaker-resilience4j
  - openFeign 과 연동 가능
  - bulkhead thread pool 사용
    - 동시 요청 수 제한으로 부하 관리, 네트워크 병목 현상 방지
    - 서비스 격리를 통한 안정성 유지
- Spring Boot validation
  - parameter 검증


### API 명세서
- 블로그 검색
  - HTTP GET : /search/v1/blogs
    - Request Parameters
      - query [Mandatory] : 검색 질의어
      - size [Optional, default = 10, min 1, max 50] : 페이지 사이즈
      - page [Optional, default = 1, min 1, max 50] : 페이지 번호
      - sort [Optional, default = R, R=최신순, A=일치순] : 정렬순
    - Response Body
```
    {
        "blogs" = [
            {
                "title" : String(블로그 제목),
                "contents" : String(블로그 내용),
                "blogger" : String(블로거 이름),
                "link" : String(블로그 URL),
                "updatedAt" : LocalDateTime(작성 일시)
            },
            ...
            ..
            .
        ]
    }
```

- 인기 검색어 목록
  - HTTP GET : /search/v1/hot-keywords
    - Request Parameters
      - none
    - Response Body
```
    {
        "keywords" = [
            {
                "keyword" : String(키워드 질의어),
                "count" : Number(카운트),
                "updatedAt" : LocalDateTime(최근 질의 시간),
                "createdAt" : LocalDateTime(최초 질의 시간)
            },
            ...
            ..
            .
        ]
    }
```

## API TEST
1. https://github.com/redlivesun/blogSearch/blob/master/jar/search.jar jar 파일 다운
   2. https://drive.google.com/file/d/1XbW_wmOXlW1wgcLPbv7QuruwzL67wlrr/view?usp=sharing
2. java -jar search.jar
3. API Test
   - Blog 검색
      - CURL -GET http://localhost:8080/search/v1/blogs?query=kakao
   - 핫 키워드 검색
     - CURL -GET http://localhost:8080/search/v1/hot-keywords