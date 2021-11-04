package tn.noureddine.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Dataindex {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
	private double  longi ;
	private double  lat ;
	private double  value;
	private Date  dateindex ;
	@ManyToOne
	@JoinColumn(name="id_index",columnDefinition = "Integer")
	private Index index;
	@ManyToOne
	@JoinColumn(name="id_study",columnDefinition = "Integer")
	private Studyarea studyarea;

	public Dataindex() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dataindex(double longi, double lat, double value, Date dateindex, Index index, Studyarea studyarea) {
		super();
		this.longi = longi;
		this.lat = lat;
		this.value = value;
		this.dateindex = dateindex;
		this.index = index;
		this.studyarea = studyarea;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public double getLongi() {
		return longi;
	}
	public void setLongi(double longi) {
		this.longi = longi;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public Date getDateindex() {
		return dateindex;
	}
	public void setDateindex(Date dateindex) {
		this.dateindex = dateindex;
	}
	public Index getIndex() {
		return index;
	}
	public void setIndex(Index index) {
		this.index = index;
	}
	
	public Studyarea getStudyarea() {
		return studyarea;
	}
	public void setStudyarea(Studyarea studyarea) {
		this.studyarea = studyarea;
	}
	@Override
	public String toString() {
		return "Dataindex [id=" + id + ", longi=" + longi + ", lat=" + lat + ", value=" + value + ", dateindex="
				+ dateindex + ", index=" + index + ", studyarea=" + studyarea + "]";
	}
	
	
	

}
