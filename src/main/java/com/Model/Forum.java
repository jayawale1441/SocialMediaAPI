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
public class Forum {
	
	@Id
	@GeneratedValue
	private int forumId;
	private String forumName;
	private String createdBy;
	private Date createdOn;
	private enum status{
		ACTIVE,
		BLOCKED,
		PENDING;		
	};
}
