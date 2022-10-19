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
public class UserProfile {
	@Id
	private String userId;
	private String bio;
	private String status;	
	
}
