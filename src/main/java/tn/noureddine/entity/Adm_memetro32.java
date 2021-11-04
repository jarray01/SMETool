package tn.noureddine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="adm_me_metro32")
public class Adm_memetro32 {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(columnDefinition = "serial")
	private Integer gid;
	private Integer adm_ide;
	private String  adm_cod;
	private String  adm_gov;
	private String  adm_del;
	public Adm_memetro32() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Adm_memetro32(Integer gid, Integer adm_ide, String adm_cod, String adm_gov, String adm_del) {
		super();
		this.gid = gid;
		this.adm_ide = adm_ide;
		this.adm_cod = adm_cod;
		this.adm_gov = adm_gov;
		this.adm_del = adm_del;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getAdm_ide() {
		return adm_ide;
	}
	public void setAdm_ide(Integer adm_ide) {
		this.adm_ide = adm_ide;
	}
	public String getAdm_cod() {
		return adm_cod;
	}
	public void setAdm_cod(String adm_cod) {
		this.adm_cod = adm_cod;
	}
	public String getAdm_gov() {
		return adm_gov;
	}
	public void setAdm_gov(String adm_gov) {
		this.adm_gov = adm_gov;
	}
	public String getAdm_del() {
		return adm_del;
	}
	public void setAdm_del(String adm_del) {
		this.adm_del = adm_del;
	}
	

	

}
