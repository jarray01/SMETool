package tn.noureddine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Etat {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
	private String descfr;
	public Etat() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Etat(Integer id, String descfr) {
		super();
		this.id = id;
		this.descfr = descfr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescfr() {
		return descfr;
	}
	public void setDescfr(String descfr) {
		this.descfr = descfr;
	}
	
}
