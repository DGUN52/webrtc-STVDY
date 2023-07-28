package com.ssafy.ssap.domain.qna;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ArticleImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "blob")
    @Lob
    private byte[] path;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    @Nullable
    private Question question;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "answer_id")
    @Nullable
    private Answer answer;
}
