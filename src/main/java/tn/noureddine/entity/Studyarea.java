package tn.noureddine.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;
@Entity
public class Studyarea {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
	private String name;
	private String jsonsa;
	public Studyarea() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Studyarea(Integer id, String name, String jsonsa) {
		super();
		this.id = id;
		this.name = name;
		this.jsonsa = jsonsa;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJsonsa() {
		return jsonsa;
	}
	public void setJsonsa(String jsonsa) {
		this.jsonsa = jsonsa;
	}
	
	

}
