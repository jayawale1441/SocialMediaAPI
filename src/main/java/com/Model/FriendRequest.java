package com.Model;

import java.util.Date;
import javax.persistence.*;
import javax.persistence.Id;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
public class FriendRequest {
	@Id
	@GeneratedValue
	private int requestId;
	private String by;
	private String to;
	private Date date;
	private enum status{
		ACCEPTED,
		REJECTED,
		PENDING;
	}
}
