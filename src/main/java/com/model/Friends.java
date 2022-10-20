package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Friends {
	
	@Id
	@GeneratedValue
	private String friendId;
	private String emailId;
	private String receivedMessage;
	
	/*@JsonIgnore
	@ManyToOne
	private Users userId;*/

}
