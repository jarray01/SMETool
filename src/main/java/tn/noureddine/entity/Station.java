package tn.noureddine.entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Entity;
@Entity
public class Station {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
	private String description;
	private Double longst;
	private Double lat;
	private String emplacement;
	public Station() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Station(Integer id, String description, Double longst, Double lat, String emplacement) {
		super();
		this.id = id;
		this.description = description;
		this.longst = longst;
		this.lat = lat;
		this.emplacement = emplacement;
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
	public Double getLongst() {
		return longst;
	}
	public void setLongst(Double longst) {
		this.longst = longst;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public String getEmplacement() {
		return emplacement;
	}
	public void setEmplacement(String emplacement) {
		this.emplacement = emplacement;
	}
	@Override
	public String toString() {
		return "Station [id=" + id + ", description=" + description + ", longst=" + longst + ", lat=" + lat
				+ ", emplacement=" + emplacement + "]";
	}
	
	
}
