package tn.noureddine.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity(name="emp_document")
public class Document {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer id;
	private String nom;
	@Column(name = "image")
	private byte[] image;
	private String titre;
	private String description;
	@ManyToOne
	@JoinColumn(name="id_typdoc",columnDefinition = "Integer")
	private Typedocument type;
	@ManyToOne
	@JoinColumn(name="id_aut",columnDefinition = "Integer")
	private Auteur auteur;
	public Document() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Document(Integer id, String nom, byte[] image, String titre, String description, Typedocument type,
			Auteur auteur) {
		super();
		this.id = id;
		this.nom = nom;
		this.image = image;
		this.titre = titre;
		this.description = description;
		this.type = type;
		this.auteur = auteur;
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
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Typedocument getType() {
		return type;
	}
	public void setType(Typedocument type) {
		this.type = type;
	}
	public Auteur getAuteur() {
		return auteur;
	}
	public void setAuteur(Auteur auteur) {
		this.auteur = auteur;
	}
	
	
	

}
