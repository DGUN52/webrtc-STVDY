package com.ssafy.ssap.domain.user;

import static jakarta.persistence.FetchType.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.ssap.domain.todo.Todo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "`user`")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(name = "email", length = 45, unique = true)
	private String email;

	@NotNull
	@Column(name = "password", length = 100)
	private String password;

	@NotNull
	@Column(name = "name", length = 20)
	private String name;

	@NotNull
	@Column(name = "nickname", length = 45)
	private String nickname;

	@Column(name = "api_key", length = 100)
	private String apiKey;

	@NotNull
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "state")
	@JsonIgnore
	private UserStateNs state;

	@NotNull
	@CreationTimestamp
	@Column(name = "regist_time", columnDefinition = "timestamp")
	private LocalDateTime registTime;

	@Builder.Default
	@OneToMany(mappedBy = "user")
	List<Interest> interestList = new ArrayList<>();

	@Builder.Default
	@OneToMany(mappedBy = "user")
	List<Todo> todoList = new ArrayList<>();

	@CreationTimestamp
	@Column(name = "state_time", columnDefinition = "timestamp")
	private LocalDateTime stateTime;

	@Column(name = "activated")
	private boolean activated;

	@Column(name = "profile_image_path")
	private String profileImagePath;

	@Column(name = "room_image_path")
	private String roomImagePath;

	@ManyToMany
	@JoinTable(
		name = "user_authority",
		joinColumns = {@JoinColumn(name = "user_no", referencedColumnName = "id")},
		inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
	private Set<Authority> authorities;

	public void changeState(UserStateNs newState) {
		this.setState(newState);
	}

	public void deleteApiKey() {
		this.apiKey = null;
	}
}
