package com.ssafy.ssap.dto;

import com.ssafy.ssap.domain.qna.QuestionCategoryNs;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class QuestionDetailResponseDto {

    private int id;
    private String title;
    private String detail;
    private Integer hit;
    private LocalDateTime regist_time;
    // private boolean bestSelected;
    private QuestionCategoryNs category;
    //    private String userNickname;
    //    private Boolean isLike;
    private Integer questionScore;

}
