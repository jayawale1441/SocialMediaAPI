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
public class Comment {
	@Id
	@GeneratedValue
	private int commentId;
	private String commentText;
	private String commentBy;
}
