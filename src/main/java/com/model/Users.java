package com.model;

import java.util.*;

import javax.persistence.*;

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
	@ElementCollection(targetClass=String.class,fetch=FetchType.EAGER)
	private List<String> friendList=new ArrayList<>();
	
	//@OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL,mappedBy="userId")
	//private List<Friends> friendList;
	
	
}
