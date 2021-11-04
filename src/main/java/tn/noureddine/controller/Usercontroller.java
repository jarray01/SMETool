package tn.noureddine.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.noureddine.config.IUtilisateurdao;
import tn.noureddine.config.Role;
import tn.noureddine.config.Utilisateur;
import tn.noureddine.dao.IRoledao;

@Controller
@RequestMapping("/users")
public class Usercontroller {
	@Autowired
	IUtilisateurdao userdao;
	
	@Autowired
	IRoledao roledao;
	
	
	@RequestMapping(value = "/modifuser", method = RequestMethod.GET)
	public String pagemodifuser(Model m) {
		return "/user/modifuser";

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pagedeutilisateurs(Model m) {
		List<Utilisateur> listeusers = (List<Utilisateur>) userdao.findAll();
		m.addAttribute("listeusers", listeusers);
		return "/user/listeusers";
	}

	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.GET)
	public String pagesupprimeruser(Model m, @PathVariable Integer id) {
		userdao.deleteById(id);
		return "redirect:/users/list";

	}

	@RequestMapping(value = "/edit/{id}")
	public String editStudent(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("user", userdao.findById(id));
		List<Role> listerole = (List<Role>) roledao.findAll();
		model.addAttribute("listerole", listerole);
		return "/user/modifuser";
	}

	@RequestMapping(value = "/add")
	public String addStudent(Model model) {
		model.addAttribute("user", new Utilisateur());
		List<Role> listerole = (List<Role>) roledao.findAll();
		model.addAttribute("listerole", listerole);
		
		return "/user/adduser";
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveCustomer(Model model, @ModelAttribute("user") Utilisateur user) {
		// customerService.saveCustomer(theCustomer);
		userdao.save(user);
		return "redirect:/users/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Utilisateur user) {
		userdao.save(user);
		return "redirect:/users/list";
	}

	

}
