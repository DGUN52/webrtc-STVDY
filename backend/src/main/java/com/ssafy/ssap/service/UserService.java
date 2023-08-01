package com.ssafy.ssap.service;

import java.util.Collections;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssap.domain.user.Authority;
import com.ssafy.ssap.domain.user.User;
import com.ssafy.ssap.dto.user.LoginResponseDto;
import com.ssafy.ssap.dto.user.UserDto;
import com.ssafy.ssap.exception.DuplicateMemberException;
import com.ssafy.ssap.exception.NotFoundMemberException;
import com.ssafy.ssap.repository.UserRepository;
import com.ssafy.ssap.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public UserDto join(UserDto userDto) {
		if (userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail()).orElse(null) != null) {
			throw new DuplicateMemberException("이미 가입되어 있는 사용자입니다.");
		}

		Authority authority = Authority.builder()
			.authorityName("ROLE_USER")
			.build();

		User user = User.builder()
			.email(userDto.getEmail())
			.password(passwordEncoder.encode(userDto.getPassword()))
			.name(userDto.getName())
			.nickname(userDto.getNickname())
			.authorities(Collections.singleton(authority))
			.activated(true)
			.build();

		return UserDto.from(userRepository.save(user));
	}

	@Transactional(readOnly = true)
	public UserDto getUserWithAuthorities(String email) {
		return UserDto.from(userRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
	}

	public LoginResponseDto getUserWithEmail(String email) {
		return LoginResponseDto.from(userRepository.findOneWithAuthoritiesByEmail(email).orElse(null));
	}

	@Transactional(readOnly = true)
	public UserDto getMyUserWithAuthorities() {
		return UserDto.from(
			SecurityUtil.getCurrentUsername()
				.flatMap(userRepository::findOneWithAuthoritiesByEmail)
				.orElseThrow(() -> new NotFoundMemberException("Member not found"))
		);
	}

	/**
	 * email로 id(pk)값 찾기
	 */
	public Integer getUserIdByUsername(String email) {
		Optional<User> optionalUser = userRepository.findOneWithAuthoritiesByEmail(email);
		return optionalUser.map(User::getId).orElse(null);
	}

}
