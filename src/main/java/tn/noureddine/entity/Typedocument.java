package tn.noureddine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Typedocument {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
	private String description;
	public Typedocument() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Typedocument(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
