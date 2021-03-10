package org.ggm.vhr.entity;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table ( name ="map" )
@JsonIgnoreProperties(value = { "hibernateLazyInitializer"})
public class MapEntity  implements Serializable {

	private static final long serialVersionUID =  8950988169957235515L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
   	@Column(name = "id" )
	private Long id;

   	@Column(name = "map_name" )
	private String mapName;

   	@Column(name = "map_key" )
	private String mapKey;

   	@Column(name = "hr")
	private String hr;
}
