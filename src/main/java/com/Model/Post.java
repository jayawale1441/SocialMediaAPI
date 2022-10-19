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
public class Post {
	@Id
	@GeneratedValue
	private int postId;
	private String postedBy;
	private Date date;
	private String description;
}
