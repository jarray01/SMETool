package tn.noureddine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.noureddine.dao.Ithemedao;
import tn.noureddine.entity.Theme;

@Controller
@RequestMapping("/theme")
public class Themecontoller {
	@Autowired
	Ithemedao themedao;
	public void setThemedao(Ithemedao themedao) {
		this.themedao = themedao;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pagedeutilisateurs(Model m) {
		List<Theme> listethemes = (List<Theme>) themedao.findAll();
		m.addAttribute("listethemes", listethemes);
		return "/theme/listethemes";
	}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String pagesupprimertheme(Model m, @PathVariable Integer id) {
		themedao.deleteById(id);
		return "redirect:/theme/list";

	}
	@RequestMapping(value = "/edit/{id}")
	public String edittheme(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("theme", themedao.findById(id));
		return "/theme/modiftheme";
	}
	@RequestMapping(value = "/add")
	public String addthem(Model model) {
		model.addAttribute("theme", new Theme());
		return "/theme/addtheme";
	}
	@RequestMapping(value = "/saveTheme", method = RequestMethod.POST)
	public String saveCustomer(Model model, @ModelAttribute("theme") Theme theme) {
		// customerService.saveCustomer(theCustomer);
		themedao.save(theme);
		return "redirect:/theme/list";
	}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Theme theme) {
		themedao.save(theme);
		return "redirect:/theme/list";
	}

}
