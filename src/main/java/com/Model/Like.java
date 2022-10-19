package com.Model;
import javax.persistence.*;
import javax.persistence.Id;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
public class Like {
	@Id
	@GeneratedValue
	private int likeId;
	private String likeBy;
}
