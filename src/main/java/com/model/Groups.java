package com.model;

import javax.persistence.*;
import javax.persistence.Id;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
public class Groups {
	
	@Id
	@GeneratedValue
	private int groupId;
	private String groupName;
	private enum status{
		ACTIVE,
		BLOCKED,
		PENDING;
	}
	private String createdBy;
	
	
}
