package org.ggm.vhr.entity;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  yz
 * @Date 2021-03-01 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="questions" )
public class QuestionsEntity  implements Serializable {

	private static final long serialVersionUID =  7462891779303445663L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "question" )
	private String question;

   	@Column(name = "experience" )
	private String experience;

}
