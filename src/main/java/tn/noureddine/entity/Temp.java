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
public class Temp {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
    private Date datet;
    private Double value;
    @ManyToOne
	@JoinColumn(name="id_st",columnDefinition = "Integer")
	private Station station;
	public Temp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Temp(Integer id, Date datet, Double value, Station station) {
		super();
		this.id = id;
		this.datet = datet;
		this.value = value;
		this.station = station;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDatet() {
		return datet;
	}
	public void setDatet(Date datet) {
		this.datet = datet;
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
		return "Temp [id=" + id + ", datet=" + datet + ", value=" + value + ", station=" + station + "]";
	}
    

}
