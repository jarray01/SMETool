package tn.noureddine.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import tn.noureddine.config.Utilisateur;
@Entity
public class Shape {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
	private String nom;
	private String description;
	private Date datecreation;
	private Date datemiseajour;
	private String copyright;
	private Double  x1;
	private Double  x2;
	private Double  y1;
	private Double  y2;
	
	
	@ManyToOne
	@JoinColumn(name="iduser",columnDefinition = "Integer")
	private Utilisateur user;
	

	@ManyToOne
	@JoinColumn(name="id_model",columnDefinition = "Integer")
	private Modele modele;
	@ManyToOne
	@JoinColumn(name="id_study",columnDefinition = "Integer")
	private Studyarea study;
	public Shape() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Shape(Integer id, String nom, String description, Date datecreation, Date datemiseajour, String copyright,
			Double x1, Double x2, Double y1, Double y2,  Utilisateur user,
			Modele modele, Studyarea study) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.datecreation = datecreation;
		this.datemiseajour = datemiseajour;
		this.copyright = copyright;
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.user = user;
	
		this.modele = modele;
		this.study = study;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDatecreation() {
		return datecreation;
	}
	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}
	public Date getDatemiseajour() {
		return datemiseajour;
	}
	public void setDatemiseajour(Date datemiseajour) {
		this.datemiseajour = datemiseajour;
	}
	public String getCopyright() {
		return copyright;
	}
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public Utilisateur getUser() {
		return user;
	}
	public void setUser(Utilisateur user) {
		this.user = user;
	}
	
	
	public Double getX1() {
		return x1;
	}
	public void setX1(Double x1) {
		this.x1 = x1;
	}
	public Double getX2() {
		return x2;
	}
	public void setX2(Double x2) {
		this.x2 = x2;
	}
	public Double getY1() {
		return y1;
	}
	public void setY1(Double y1) {
		this.y1 = y1;
	}
	public Double getY2() {
		return y2;
	}
	public void setY2(Double y2) {
		this.y2 = y2;
	}
	public Modele getModele() {
		return modele;
	}
	public void setModele(Modele modele) {
		this.modele = modele;
	}

	public Studyarea getStudy() {
		return study;
	}

	public void setStudy(Studyarea study) {
		this.study = study;
	}
	

}
