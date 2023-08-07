package com.ssafy.ssap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class QuestionDetailResponseDto {

    private Integer id;
    private String title;
    private String detail;
    private Integer hit;
    private LocalDateTime regist_time;
    private Boolean bestSelected;
    private String category;
    private String userNickname;
    private Integer questionScore;

    public int getQuestionScore() {
        return questionScore == null ? 0 : questionScore;
    }

}
