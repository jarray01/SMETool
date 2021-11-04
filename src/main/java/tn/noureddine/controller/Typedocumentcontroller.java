package tn.noureddine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.noureddine.dao.Itypdocumentdao;
import tn.noureddine.entity.Theme;
import tn.noureddine.entity.Typedocument;

@Controller
@RequestMapping("/typedoc")
public class Typedocumentcontroller {
	@Autowired
	Itypdocumentdao typdocdao;
	public void setTypdocdao(Itypdocumentdao typdocdao) {
		this.typdocdao = typdocdao;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pagedeutilisateurs(Model m) {
		List<Typedocument> listetypedoc =  (List<Typedocument>) typdocdao.findAll();
		m.addAttribute("listetypedoc", listetypedoc);
		return "/typedoc/listetypedoc";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String pagesupprimertheme(Model m, @PathVariable Integer id) {
		typdocdao.deleteById(id);
		return "redirect:/typedoc/list";

	}
	@RequestMapping(value = "/edit/{id}")
	public String edittheme(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("typedoc", typdocdao.findById(id));
		return "/typedoc/modiftypedoc";
	}
	@RequestMapping(value = "/add")
	public String addthem(Model model) {
		model.addAttribute("typedoc", new Theme());
		return "/typedoc/addtypedoc";
	}
	@RequestMapping(value = "/savetypdoc", method = RequestMethod.POST)
	public String saveCustomer(Model model, @ModelAttribute("typedoc") Typedocument typedoc) {
		// customerService.saveCustomer(theCustomer);
		typdocdao.save(typedoc);
		return "redirect:/typedoc/list";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Typedocument typedoc) {
		typdocdao.save(typedoc);
		return "redirect:/typedoc/list";
	}
}
