package tn.noureddine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.noureddine.dao.Igouvernoratdao;
import tn.noureddine.entity.Gouvernorat;

@Controller
@RequestMapping("/gouvernorat")
public class Gouvcontoller  {
	@Autowired
	Igouvernoratdao gouvdao;
	public void setGouvdao(Igouvernoratdao gouvdao) {
		this.gouvdao = gouvdao;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pagegouv(Model m) {
		List<Gouvernorat> listegouvs = (List<Gouvernorat>) gouvdao.findAll();
		m.addAttribute("listegouvs", listegouvs);
		return "/gouvernorat/listegouvs";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String pagesupprimer(Model m, @PathVariable Integer id) {
		gouvdao.deleteById(id);
		return "redirect:/gouvernorat/list";

	}
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("gouv", gouvdao.findById(id));
		return "/gouvernorat/modifgouv";
	}
	@RequestMapping(value = "/add")
	public String addthem(Model model) {
		model.addAttribute("gouv", new Gouvernorat());
		return "/gouvernorat/addgouv";
	}
	@RequestMapping(value = "/savegouv", method = RequestMethod.POST)
	public String saveCustomer(Model model, @ModelAttribute("gouv") Gouvernorat gouv) {
		// customerService.saveCustomer(theCustomer);
		gouvdao.save(gouv);
		return "redirect:/gouvernorat/list";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Gouvernorat gouv) {
		gouvdao.save(gouv);
		return "redirect:/gouvernorat/list";
	}


}
