package com.ssafy.ssap.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ssafy.ssap.domain.todo.Todo;
import com.ssafy.ssap.domain.user.User;
import com.ssafy.ssap.repository.TodoRepository;
import com.ssafy.ssap.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

	private final TodoRepository todoRepository;
	private final UserService userService;
	private final UserRepository userRepository;

	/**
	 * todo 생성
	 */
	public Integer create(String objective) throws Exception {
		String userEmail = userService.getMyUserWithAuthorities().getEmail();
		User user = userRepository.findOneWithAuthoritiesByEmail(userEmail)
			.orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));

		Todo todo = Todo.builder()
			.objective(objective)
			.doneFlag(false)
			.registTime(LocalDateTime.now())
			.user(user)
			.build();

		todoRepository.save(todo);

		return todo.getId();
	}

	public void checkDoneFlag(Integer todoId) {
		String userEmail = userService.getMyUserWithAuthorities().getEmail();
		User user = userRepository.findOneWithAuthoritiesByEmail(userEmail)
			.orElseThrow(() -> new IllegalArgumentException("사용자 정보를 찾을 수 없습니다."));
		Todo todo = todoRepository.findById(todoId)
			.orElseThrow(() -> new IllegalArgumentException("todo 정보를 찾을 수 없습니다."));
		;
		Boolean flag = todo.getDoneFlag();

		if (user.getId() != todo.getUser().getId()) {
			new IllegalArgumentException("사용자 정보가 일치하지 않습니다.");
		}

		todo.setDoneFlag(!flag);
		todoRepository.save(todo);
	}

}
