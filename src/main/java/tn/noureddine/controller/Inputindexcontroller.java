package tn.noureddine.controller;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import tn.noureddine.dao.Idataindexdao;
import tn.noureddine.dao.Iindexdao;
import tn.noureddine.dao.Istudyareadao;
import tn.noureddine.entity.Dataindex;
import tn.noureddine.entity.Index;
import tn.noureddine.entity.Studyarea;


@Controller
@RequestMapping("/inputindex")
public class Inputindexcontroller {
	private static final String EXTENSION = ".jpg";
    private static final String SERVER_LOCATION = "/server/images";
	@Autowired
	Iindexdao indexdao;
	@Autowired
	Idataindexdao dataindexdao;
	@Autowired
	Istudyareadao studyareadao;
	public void setIndexdao(Iindexdao indexdao) {
		this.indexdao = indexdao;
	}
	public void setDataindexdao(Idataindexdao dataindexdao) {
		this.dataindexdao = dataindexdao;
	}
public void setStudyareadao(Istudyareadao studyareadao) {
	this.studyareadao = studyareadao;
}
	@RequestMapping(value = "/add")
	public String add(Model model) {
		//model.addAttribute("doc", new Index());
		
	List<Index> listeindex =  (List<Index>) indexdao.findAll();
	model.addAttribute("listeindex", listeindex);
	List<Studyarea> listestudy =  (List<Studyarea>) studyareadao.findAll();
	model.addAttribute("listestudy", listestudy);
		return "/input/inputindex";
	}
//, consumes = "multipart/form-data"
	@PostMapping(value = "/upload")
	    public String uploadSimple(Model model,@RequestParam("file") MultipartFile file,@RequestParam("dateindex") Date dateindex,@RequestParam("ind") Integer ind,@RequestParam("std") Integer std) throws IOException {
		// dataindexdao.saveAll(CsvUtils.read(Dataindex.class, file.getInputStream()));
		
		 List<String> indexeslong = new ArrayList<>();
		 List<String> indexeslat = new ArrayList<>();
		 List<String> indexesval = new ArrayList<>();
		 try (Scanner s = new Scanner(file.getInputStream())) {
		     while (s.hasNext()) {
		    	// System.out.println(s.nextLine());
		    	 String[] arrOfStr = s.nextLine().split(";");
		    	 for (int i=0; i < arrOfStr.length; i= i+3) { 
		            
		             indexeslong.add(arrOfStr[i]);
		        }
		    	 for (int i=1; i < arrOfStr.length; i= i+3) { 
			            
		             indexeslat.add(arrOfStr[i]);
		        }
		    	 for (int i=2; i < arrOfStr.length; i= i+3) { 
			            
		             indexesval.add(arrOfStr[i]);
		        }
		     }
		 }   
		     for (int i=0; i <indexeslat.size(); i++) { 
		    	 Dataindex dindex = new Dataindex();
				// Index ii= new Index(2, "NDII");
				 Index ii = indexdao.getindexbyid(ind);
				 Studyarea st = studyareadao.getstudybyid(std);
				    dindex.setIndex(ii);
			    	dindex.setStudyarea(st);
				    dindex.setDateindex(dateindex);
	        	 dindex.setLongi(Double.parseDouble(indexeslong.get(0)));
	        	 dindex.setLat(Double.parseDouble(indexeslat.get(1)));
	        	 dindex.setValue(Double.parseDouble(indexesval.get(2)));
	    	      dataindexdao.save(dindex);
              }
	  
				List<Index> listeindex =  (List<Index>) indexdao.findAll();
				model.addAttribute("listeindex", listeindex);

				return "/input/inputindex";
	    }
	 @RequestMapping(value = "/list")
		public String listtt(Model model,@RequestParam Integer indexx) {
			//model.addAttribute("doc", new Index());
			
		//List<Index> listeindex =  (List<Index>) indexdao.findAll();
		//model.addAttribute("listeindex", listeindex);
		List<Dataindex> listedi =  (List<Dataindex>) dataindexdao.getdatabyindex(indexx);
		model.addAttribute("listedi", listedi);
		List<Index> listeindex =  (List<Index>) indexdao.findAll();
		model.addAttribute("listeindex", listeindex);

		return "/input/inputindex";
		
		}
	

	
}
