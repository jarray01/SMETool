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

import tn.noureddine.dao.Iauteurdao;
import tn.noureddine.dao.Idocumentdao;
import tn.noureddine.dao.Itypdocumentdao;
import tn.noureddine.entity.Auteur;
import tn.noureddine.entity.Document;
import tn.noureddine.entity.Typedocument;

@Controller
@RequestMapping("/document")
public class Doccontroller {
	private static final String EXTENSION = ".jpg";
    private static final String SERVER_LOCATION = "/server/images";
	@Autowired
	Idocumentdao docdao;

	public void setDocdao(Idocumentdao docdao) {
		this.docdao = docdao;
	}
	@Autowired
	Itypdocumentdao typdocdao;
	public void setTypdocdao(Itypdocumentdao typdocdao) {
		this.typdocdao = typdocdao;
	}
	@Autowired
	Iauteurdao auteurdao;
	public void setAuteurdao(Iauteurdao auteurdao) {
		this.auteurdao = auteurdao;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pageliste(Model m) {
		List<Document> listedocs = (List<Document>) docdao.findAll();
		m.addAttribute("listedocs", listedocs);
		return "/document/listedocs";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String pagesupprimer(Model m, @PathVariable Integer id) {
		docdao.deleteById(id);
		return "redirect:/document/list";

	}

	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("doc", docdao.findById(id));
        List<Typedocument> listetypedoc = (List<Typedocument>) typdocdao.findAll();
		List<Auteur> listeauteurs =  (List<Auteur>) auteurdao.findAll();
		model.addAttribute("listetypedoc", listetypedoc);
		model.addAttribute("listeauteurs", listeauteurs);
		return "/document/modifdoc";
	}

	@RequestMapping(value = "/add")
	public String add(Model model) {
		model.addAttribute("doc", new Document());
		List<Typedocument> listetypedoc = (List<Typedocument>) typdocdao.findAll();
		
		List<Auteur> listeauteurs =  (List<Auteur>) auteurdao.findAll();
		model.addAttribute("listetypedoc", listetypedoc);
		model.addAttribute("listeauteurs", listeauteurs);
		return "/document/adddoc";
	}

	@RequestMapping(value = "/savedoc", method = RequestMethod.POST)
	public String save(Model model, @ModelAttribute("doc") Document doc,@RequestParam("uploadfile") MultipartFile file) throws IOException {
		doc.setNom(file.getOriginalFilename());
		doc.setImage(file.getBytes());
		docdao.save(doc);
		return "redirect:/document/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savee(Document doc) {
		docdao.save(doc);
		return "redirect:/document/list";
	}
	
	@RequestMapping(value = "/download/{id}",method = RequestMethod.GET)
	public ResponseEntity<byte[]> getFile(@PathVariable Integer id) {
		Optional<Document> fileOptional = docdao.findById(id);
		
		if(fileOptional.isPresent()) {
			Document file = fileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getNom() + "\"")
					.body(file.getImage());	
		}
		
		return ResponseEntity.status(404).body(null);
	}



	

}
