package tn.noureddine.controller;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.noureddine.config.Role;
import tn.noureddine.config.Utilisateur;
import tn.noureddine.dao.Iauteurdao;
import tn.noureddine.dao.Idocumentdao;
import tn.noureddine.dao.Itypdocumentdao;
import tn.noureddine.entity.Auteur;
import tn.noureddine.entity.Document;
import tn.noureddine.entity.Typedocument;

@Controller
@RequestMapping("/input")
public class Inputcontroller {
	@Autowired
	Idocumentdao docdao;

	public void setDocdao(Idocumentdao docdao) {
		this.docdao = docdao;
	}
	
	
	@RequestMapping(value = "/add")
	public String inputadd(Model model) {
		model.addAttribute("user", new Utilisateur());
		return "input/inputselect";
	}

	



	

}
