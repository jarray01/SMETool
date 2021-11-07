package tn.noureddine.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tn.noureddine.config.IUtilisateurdao;
import tn.noureddine.dao.IShapedao;

import tn.noureddine.dao.Imodeldao;
import tn.noureddine.dao.Istudyareadao;

import tn.noureddine.entity.Modele;
import tn.noureddine.entity.Shape;
import tn.noureddine.entity.Studyarea;

@Controller
@RequestMapping("/shape")
public class Shapecontroller {
	@Autowired
	IShapedao shapedao;

	@Autowired
	IUtilisateurdao userdao;
	@Autowired
	Imodeldao modeldao;
	@Autowired
	Istudyareadao sadao;


	public void setUserdao(IUtilisateurdao userdao) {
		this.userdao = userdao;
	}

	
	public void setShapedao(IShapedao shapedao) {
		this.shapedao = shapedao;
	}

	
	public void setModeldao(Imodeldao modeldao) {
		this.modeldao = modeldao;
	}
	public void setSadao(Istudyareadao sadao) {
		this.sadao = sadao;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pageliste(Model m) {
		List<Shape> listeshapes = (List<Shape>) shapedao.findAll();
		m.addAttribute("listeshapes", listeshapes);
		return "/shape/nv";
	}

	@RequestMapping(value = "/lspatiale", method = RequestMethod.GET)
	public String pagelspatiale(Model m, @RequestParam String name) {
		List<Shape> ll = new ArrayList<Shape>();
		Integer[] listeshapes = (Integer[]) shapedao.getlistespatiale(name);
		for (int i = 0; i < listeshapes.length; i++) {
			System.out.println(listeshapes[i]);
			Shape s = (Shape) shapedao.getshapebyid(listeshapes[i]);
			System.out.println(s.getDescription());
			ll.add(s);
		}

		m.addAttribute("listeshapess", ll);

		return "/shape/requettespatiale";
		// return "";
	}

	@RequestMapping(value = "/spacequery", method = RequestMethod.GET)
	public String pageliste1(Model m) {
		List<Shape> listeshapes = (List<Shape>) shapedao.findAll();
		m.addAttribute("listeshapes", listeshapes);
		return "/shape/requettespatiale";
	}



	


	

	

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String pagesupprimer(Model m, @PathVariable Integer id) {
		shapedao.deleteById(id);
		return "redirect:/shape/list";

	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("gouv", shapedao.findById(id));
		return "/shape/modifshape";
	}



	@RequestMapping(value = "/saveshape", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("shape") Shape shape) {
		shapedao.save(shape);
		return "redirect:/shape/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savee(Shape shape) {
		shapedao.save(shape);
		return "redirect:/shape/list";
	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String pagemap() {

		return "/shape/map";
	}

	@RequestMapping(value = "/affichermap", method = RequestMethod.GET)
	public String pagemaps() {

		return "/shape/affichemap";
	}
	@RequestMapping(value = "/afficherpng", method = RequestMethod.GET)
	public String pagepng() {

		return "/shape/affpng";
	}
	@RequestMapping(value = "/afficherfin", method = RequestMethod.GET)
	public String pagefin(Model m) {
	//	List<Shape> listeshapes = (List<Shape>) shapedao.getshapevecteur();
		//m.addAttribute("listeshapes", listeshapes);
		List<Modele> listemodels = (List<Modele>) modeldao.findAll() ;
		m.addAttribute("listemodels", listemodels);
		List<Studyarea> listesa = (List<Studyarea>) sadao.findAll() ;
		m.addAttribute("listesa", listesa);
		return "/shape/nv";
	}
	@RequestMapping(value = "/requetteresultats", method = RequestMethod.POST)
	public String pagerrr(Model m) {
		List<Shape> listerasters = (List<Shape>) shapedao.getshaperaster();
		m.addAttribute("listerasters", listerasters);
		return "/shape/nv";
	}
	@RequestMapping(value = "/requetteresultatss", method = RequestMethod.POST)
	public String pagertrr(Model m) {
		List<Shape> listerasters = (List<Shape>) shapedao.getshaperaster();
		m.addAttribute("listerasters", listerasters);
		return "/shape/nv";
	}
	@RequestMapping(value = "/requetteresultatssff", method = RequestMethod.POST)
	public String pagerrrtv(Model m ,@RequestParam Integer modelId, @RequestParam Integer studyId, @RequestParam String datecarte) {
		
		if (datecarte.toString() == "") {
			List<Shape> listerasters = (List<Shape>) shapedao.getshaperasterparmodelareasansdate(modelId, studyId);
			m.addAttribute("listerasters", listerasters);}
		if (datecarte != "") {
			Date dd = Date.valueOf(datecarte);
			List<Shape> listerasters = (List<Shape>) shapedao.getshaperasterparmodelarea(modelId, studyId, dd);
			m.addAttribute("listerasters", listerasters);
			}
		List<Modele> listemodels = (List<Modele>) modeldao.findAll() ;
		m.addAttribute("listemodels", listemodels);
		List<Studyarea> listesa = (List<Studyarea>) sadao.findAll() ;
		m.addAttribute("listesa", listesa);
		
		return "/shape/nv";
	}

	@RequestMapping(value = "/requetteresultats/{idmodel}", method = RequestMethod.POST)
	public String pagerr(Model m, @PathVariable Integer idmodel) {
		List<Shape> listerasters = (List<Shape>) shapedao.getshaperasterparc(idmodel);
		m.addAttribute("listerasters", listerasters);
		return "/shape/nv";
	}
	

	

}
