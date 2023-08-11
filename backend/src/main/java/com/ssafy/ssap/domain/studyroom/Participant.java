package com.ssafy.ssap.domain.studyroom;

import com.ssafy.ssap.domain.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Participant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Setter
	@Column(name = "is_out", columnDefinition = "bit")
	@ColumnDefault("0")
	private Boolean isOut;

	@Column(name = "connection_id", length = 100)
	private String connectionId;

	@Setter
	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "role")
	private ParticipantRoleNs role;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "room_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Room room;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
}
