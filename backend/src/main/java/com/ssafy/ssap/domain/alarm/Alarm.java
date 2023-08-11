package com.ssafy.ssap.domain.alarm;

import static jakarta.persistence.FetchType.*;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ssafy.ssap.domain.user.User;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alarm")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Alarm {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(length = 30)
	private String title;

	@NotNull
	@Column(columnDefinition = "text")
	private String detail;

 	@Column(name = "image_path")
	private String imagePath;

	@NotNull
	@Column(name = "is_read", columnDefinition = "bit(1)")
	private Boolean isRead;

	@NotNull
	@Column(name = "linkedUrl", length = 45)
	private String linkedUrl;

	@NotNull
	@CreationTimestamp
	@Column(name = "regist_time", columnDefinition = "timestamp")
	private LocalDateTime registTime;

	@NotNull
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	private User user;

}
