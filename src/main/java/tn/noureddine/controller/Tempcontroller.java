package tn.noureddine.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.noureddine.dao.Istationdao;
import tn.noureddine.dao.Itempdao;
import tn.noureddine.entity.Dataindex;
import tn.noureddine.entity.Index;
import tn.noureddine.entity.Station;
import tn.noureddine.entity.Studyarea;
import tn.noureddine.entity.Temp;

@Controller
@RequestMapping("/temp")
public class Tempcontroller {
	@Autowired
	Itempdao tempdao;
	@Autowired
	Istationdao stationdao;
	public void setStationdao(Istationdao stationdao) {
		this.stationdao = stationdao;
	}
	public void setModeldao(Itempdao tempdao) {
		this.tempdao = tempdao;
	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String pagegouv(Model m) {
	List<Temp> listestemp =  (List<Temp>) tempdao.findAll();
		m.addAttribute("listestemp", listestemp);
		List<Station> listesstation =   (List<Station>) stationdao.findAll();
		m.addAttribute("listesstation", listesstation);
		return "/temperature/temp";
	}
	@RequestMapping(value = "/listt", method = RequestMethod.POST)
	public String pagegouv11(Model m, @RequestParam Integer idstation) {
	List<Temp> listestemp =  (List<Temp>) tempdao.gettempbystation(idstation);
		m.addAttribute("listestemp", listestemp);
		List<Station> listesstation =   (List<Station>) stationdao.findAll();
		m.addAttribute("listesstation", listesstation);
		return "/temperature/temp";
	}
	@PostMapping(value = "/upload")
    public String uploadSimple(Model model,@RequestParam("file") MultipartFile file,@RequestParam("datetemp") Date datetemp,@RequestParam("station") Integer station) throws IOException {
	// dataindexdao.saveAll(CsvUtils.read(Dataindex.class, file.getInputStream()));

	 List<String> indexesval = new ArrayList<>();
	 try (Scanner s = new Scanner(file.getInputStream())) {
	     while (s.hasNext()) {
	    	// System.out.println(s.nextLine());
	    	 String[] arrOfStr = s.nextLine().split(";");
	    	 for (int i=0; i < arrOfStr.length; i= i+3) { 
	            
	    		 indexesval.add(arrOfStr[i]);
	        }
	    	
	     }
	 }   
	     for (int i=0; i <indexesval.size(); i++) { 
	    
			Station st = stationdao.getstationbyid(station);
			Temp t = new Temp();
			t.setStation(st);
			t.setDatet(datetemp);
			t.setValue(Double.parseDouble(indexesval.get(0)));;
			   tempdao.save(t);
          }
  
	     List<Station> listesstation =   (List<Station>) stationdao.findAll();
			model.addAttribute("listesstation", listesstation);

			return "/temperature/temp";
    }
}
