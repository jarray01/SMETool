package tn.noureddine.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.noureddine.config.IUtilisateurdao;
import tn.noureddine.config.Utilisateur;
@CrossOrigin
@RestController
@RequestMapping(value = "/service/user")
public class UserRestt {
	@Autowired
	IUtilisateurdao userdao;
	public void setDao(IUtilisateurdao dao) {
		this.userdao = dao;
	}
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public List<Utilisateur> fct(){
		return (List<Utilisateur>) userdao.findAll();
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Utilisateur fct1(@RequestBody Utilisateur u){
		return userdao.save(u);	
	}
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
		public boolean fct2(@PathVariable("id") Integer id ){
			userdao.deleteById(id);
			return true;
		}
}
