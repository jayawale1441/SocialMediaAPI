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
public class Users {
	@Id
	private String userId;
	private String emailId;
	private String password;
	private int age;
}
