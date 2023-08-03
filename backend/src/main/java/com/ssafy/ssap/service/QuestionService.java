package com.ssafy.ssap.service;

import com.ssafy.ssap.common.MessageFormat;
import com.ssafy.ssap.domain.qna.Question;
import com.ssafy.ssap.domain.qna.QuestionCategoryNs;
import com.ssafy.ssap.domain.user.User;
import com.ssafy.ssap.dto.QuestionCreateDto;
import com.ssafy.ssap.dto.QuestionDetailResponseDto;
import com.ssafy.ssap.dto.QuestionListResponseDto;
import com.ssafy.ssap.repository.QueryRepository;
import com.ssafy.ssap.repository.QuestionRepository;
import com.ssafy.ssap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QueryRepository queryRepository;
    private final UserRepository userRepository;

    /**
     * 질문 생성
     */
    @Transactional
    public Integer create(QuestionCreateDto questionCreateDto) throws Exception {
        User user = userRepository.getReferenceById(questionCreateDto.getUserNo());

        Question question = Question.builder()
                .title(questionCreateDto.getTitle())
                .detail(questionCreateDto.getContent())
                .registTime(LocalDateTime.now())
                .category(new QuestionCategoryNs(questionCreateDto.getCategory()))
                .hit(0)
                .user(user)
                .build();

        questionRepository.save(question);

        return question.getId();
    }

    /**
     * 질문 수정
     */
    @Transactional
    public Integer update(Integer questionNo, QuestionCreateDto questionCreateDto) {
        Question question = questionRepository.findById(questionNo)
                .orElseThrow(() -> new IllegalArgumentException(MessageFormat.NO_QUETION_ID));

        return question.update(questionCreateDto.getTitle(), questionCreateDto.getContent(), new QuestionCategoryNs(questionCreateDto.getCategory()));
    }

    /**
     * 질문 삭제
     * TODO: 연쇄적으로 연관된 FK 삭제되는지 확인 작업 필요
     */
    public void delete(Integer questionNo) {
        questionRepository.deleteById(questionNo);
    }

    /**
     * 전체 목록 조회
     * 전체, 키워드 검색, 사용자 번호 검색을 지원
     * 페이징 지원
     */
    public Page<QuestionListResponseDto> getList(String keyword, String nickname, Pageable pageable) {
        return queryRepository.findAllQuestionWithKeywordAndNickName(keyword, nickname, pageable);
    }

    public QuestionDetailResponseDto detail(Integer questionNo) {
        return queryRepository.findQuestionById(questionNo);
    }
}
