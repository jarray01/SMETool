package tn.noureddine.entity;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
@Entity
public class Pluvo {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
  private Date datep;
  private Double value;
  @ManyToOne
	@JoinColumn(name="id_st",columnDefinition = "Integer")
	private Station station;
public Pluvo() {
	super();
	// TODO Auto-generated constructor stub
}
public Pluvo(Integer id, Date datep, Double value, Station station) {
	super();
	this.id = id;
	this.datep = datep;
	this.value = value;
	this.station = station;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Date getDatep() {
	return datep;
}
public void setDatep(Date datep) {
	this.datep = datep;
}
public Double getValue() {
	return value;
}
public void setValue(Double value) {
	this.value = value;
}
public Station getStation() {
	return station;
}
public void setStation(Station station) {
	this.station = station;
}
@Override
public String toString() {
	return "Pluvo [id=" + id + ", datep=" + datep + ", value=" + value + ", station=" + station + "]";
}
  
}
