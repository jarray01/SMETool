package tn.noureddine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.noureddine.dao.Istudyareadao;
import tn.noureddine.entity.Studyarea;
@Controller
@RequestMapping("/studyarea")
public class Studyareacontroller {
	@Autowired
	Istudyareadao stddao;
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pagegouv(Model m) {
		List<Studyarea> listesstd =  (List<Studyarea>) stddao.findAll();
		m.addAttribute("listesstd", listesstd);
		return "/studyarea/listestd";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String pagesupprimer(Model m, @PathVariable Integer id) {
		stddao.deleteById(id);
		return "redirect:/studyarea/list";

	}
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("gouv", stddao.findById(id));
		return "/studyarea/modifstd";
	}
	@RequestMapping(value = "/add")
	public String addthem(Model model) {
		model.addAttribute("gouv", new Studyarea());
		return "/studyarea/addstd";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(Model model, @ModelAttribute("gouv") Studyarea gouv) {
		// customerService.saveCustomer(theCustomer);
		stddao.save(gouv);
		return "redirect:/studyarea/list";
	}
}
