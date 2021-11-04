package tn.noureddine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.noureddine.dao.Imodeldao;
import tn.noureddine.dao.Istudyareadao;
import tn.noureddine.entity.Modele;
import tn.noureddine.entity.Studyarea;
import tn.noureddine.entity.Theme;

@Controller
@RequestMapping("/model")
public class Modelcontroller {
	@Autowired
	Imodeldao modeldao;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pagegouv(Model m) {
		List<Modele> listesmod =  (List<Modele>) modeldao.findAll();
		m.addAttribute("listesmod", listesmod);
		return "/model/listemod";
	}
	@RequestMapping(value = "/add")
	public String addthem(Model model) {
		model.addAttribute("gouv", new Modele());
		return "/model/addmod";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String pagesupprimer(Model m, @PathVariable Integer id) {
		modeldao.deleteById(id);
		return "redirect:/model/list";

	}
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("gouv", modeldao.findById(id));
		return "/model/modifmod";
	}
	@RequestMapping(value = "/savemod", method = RequestMethod.POST)
	public String saveCustomer(Model model, @ModelAttribute("gouv") Modele gouv) {
		// customerService.saveCustomer(theCustomer);
		modeldao.save(gouv);
		return "redirect:/model/list";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Modele modele) {
		modeldao.save(modele);
		return "redirect:/model/list";
	}

}
