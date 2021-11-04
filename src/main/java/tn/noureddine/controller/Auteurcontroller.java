package tn.noureddine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.noureddine.dao.Iauteurdao;
import tn.noureddine.entity.Auteur;

@Controller
@RequestMapping("/auteur")
public class Auteurcontroller {
	@Autowired
	Iauteurdao auteurdao;
	public void setAuteurdao(Iauteurdao auteurdao) {
		this.auteurdao = auteurdao;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pagedeutilisateurs(Model m) {
		List<Auteur> listeauteurs =  (List<Auteur>) auteurdao.findAll();
		m.addAttribute("listeauteurs", listeauteurs);
		return "/auteur/listeauteurs";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String pagesupprimertheme(Model m, @PathVariable Integer id) {
		auteurdao.deleteById(id);;
		return "redirect:/auteur/list";

	}
	@RequestMapping(value = "/edit/{id}")
	public String edittheme(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("auteur", auteurdao.findById(id));
		return "/auteur/modifauteur";
	}
	@RequestMapping(value = "/add")
	public String addthem(Model model) {
		model.addAttribute("auteur", new Auteur());
		return "/auteur/addauteur";
	}
	@RequestMapping(value = "/saveauteur", method = RequestMethod.POST)
	public String saveCustomer(Model model, @ModelAttribute("auteur") Auteur auteur) {
		// customerService.saveCustomer(theCustomer);
		auteurdao.save(auteur);
		return "redirect:/auteur/list";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Auteur auteur) {
		auteurdao.save(auteur);
		return "redirect:/auteur/list";
	}
}
