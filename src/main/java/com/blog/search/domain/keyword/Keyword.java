package com.blog.search.domain.keyword;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "KEYWORD")
@Entity
public class Keyword {

    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "KEYWORD",
            nullable = false)
    private String keyword;

    @Column(name = "COUNT")
    private long count;

    @CreatedDate
    @Column(name = "UPDATED_AT",
            columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

    @LastModifiedDate
    @Column(name = "CREATED_AT",
            columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    public Keyword(String keyword) {
        this.keyword = keyword;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    public Keyword(String keyword, long count) {
        this.keyword = keyword;
        this.count = count;
        this.updatedAt = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Keyword keyword = (Keyword) o;
        return getId() != null && Objects.equals(getId(), keyword.getId()) && Objects.equals(getKeyword(),
                                                                                             keyword.getKeyword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keyword);
    }

    public void increaseCount() {
        count++;
        updatedAt = LocalDateTime.now();
    }
}
