package org.ggm.vhr.entity;

import javax.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description  
 * @Author  yz
 * @Date 2021-03-02 
 */

@Setter
@Getter
@ToString
@Entity
@Table ( name ="study_time" )
public class StudyTimeEntity  implements Serializable {

	private static final long serialVersionUID =  5952970667116588963L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "date" )
	private String date;

   	@Column(name = "time" )
	private Long time;

}
