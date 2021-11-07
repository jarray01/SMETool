package tn.noureddine.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
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

@Controller
@RequestMapping("/input")
public class Inputcontroller {
	
	
	@RequestMapping(value = "/add")
	public String inputadd(Model model) {
		model.addAttribute("user", new Utilisateur());
		return "input/inputselect";
	}

	@RequestMapping(value = "/process")
	public String inputpro(Model model, @RequestParam("Coord1") String Coord1, @RequestParam("Coord2") String Coord2,
			@RequestParam("Coord3") String Coord3, @RequestParam("Coord4") String Coord4,
			@RequestParam("datedeb") Date datedeb, @RequestParam("datefin") Date datefin,
			@RequestParam("datevisua") String datevisua) throws IOException {
		//model.addAttribute("user", new Utilisateur());
		String pathPython = "/scripts/PipelineSentinel.py";
		String[] cmd = new String[9];
		cmd[0] = "python";
		cmd[1] = pathPython;
		cmd[2] = Coord1;
		cmd[3] = Coord2;
		cmd[4] = Coord3;
		cmd[5] = Coord4;
		cmd[6] = datedeb.toString();
		cmd[7] = datefin.toString();
		cmd[8] = datevisua;
		Runtime r = Runtime.getRuntime();
		Process p = r.exec(cmd);
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String s;
		while ((s = in.readLine()) != null) {
			System.out.println(s);
		}
		
		return "input/inputselect";
	}




	

}
