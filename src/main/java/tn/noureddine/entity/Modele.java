package tn.noureddine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Modele {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
	private String descriptionm;
	public Modele() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Modele(Integer id, String descriptionm) {
		super();
		this.id = id;
		this.descriptionm = descriptionm;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescriptionm() {
		return descriptionm;
	}
	public void setDescriptionm(String descriptionm) {
		this.descriptionm = descriptionm;
	}
	
}
