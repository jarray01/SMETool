package tn.noureddine.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tn.noureddine.config.IUtilisateurdao;
import tn.noureddine.config.Role;
import tn.noureddine.config.Utilisateur;
import tn.noureddine.dao.IShapedao;
import tn.noureddine.dao.Idataindexdao;
import tn.noureddine.dao.Istudyareadao;
import tn.noureddine.entity.Dataindex;
import tn.noureddine.entity.Index;
import tn.noureddine.entity.Shape;
import tn.noureddine.entity.Studyarea;

@Controller
public class Appcontroller {
	@Autowired
	IUtilisateurdao userdao;
	public void setUserdao(IUtilisateurdao userdao) {
		this.userdao = userdao;
	}
	@Autowired
	Idataindexdao dindexdao;
	public void setDindexdao(Idataindexdao dindexdao) {
		this.dindexdao = dindexdao;
	}
	@Autowired
	Istudyareadao stddao;
	public void setStddao(Istudyareadao stddao) {
		this.stddao = stddao;
	}
	@RequestMapping(value = "/403")
	public String fct403(Model m) {
		return "403";
	}

	@RequestMapping(value = "/")
	public String fct1( HttpSession session) {
		
			return "redirect:/login";
		
	}
	@RequestMapping(value = "/home")
	public String fcthome(Model m, HttpServletRequest request, HttpSession session) {
	if (session.getAttribute("user") == "") {
		return "redirect:/login";
	}
	else {
		return "home";
	}
	
	}
    @RequestMapping(value = "/chart")
	public String fctchart(Model m, HttpServletRequest request, HttpSession session) {
    	List<Studyarea> listestudy =  (List<Studyarea>) stddao.findAll();
    	m.addAttribute("listestudy", listestudy);
		if (session.getAttribute("user") == "") {
			return "redirect:/login";
		}
		else {
			Map<String, String> surveyMap=new LinkedHashMap<>();
			Map<Date,Float> surveyMapndvi=new LinkedHashMap<>();
			Map<Date,Float> surveyMapndii=new LinkedHashMap<>();
			Map<Date,Float> surveyMapvv=new LinkedHashMap<>();
			Map<Date,Float> surveyMapvh=new LinkedHashMap<>();
			List<String> listesm = new ArrayList<>();
			
			Map<Date, Double>  mappedResultsm = new HashMap<>();
			Map<Date, Double>  queryResultndvi = new HashMap<>();
			Map<Date, Double>  queryResultndii = new HashMap<>();
			Map<Date, Double>  queryResultvv = new HashMap<>();
			Map<Date, Double>  queryResultvh = new HashMap<>();
			List<Object[]> queryResult =  dindexdao.getdatabyindex1(3);
			for (Object[] obj : queryResult ) {
			    Date ld = (Date) obj[0];
				System.out.println(ld);
			    Double count = (Double) obj[1];
			    System.out.println("valavg"+ count);
			    mappedResultsm.put(ld, count);
			}
			List<Object[]> queryResult1 =  dindexdao.getdatabyindex1(1);
			for (Object[] obj : queryResult1) {
			    Date ld = (Date) obj[0];
				System.out.println(ld);
			    Double count = (Double) obj[1];
			    System.out.println("valavg"+ count);
			    queryResultndvi.put(ld, count);
			}
			List<Object[]> queryResult2 =  dindexdao.getdatabyindex1(2);
			for (Object[] obj : queryResult2 ) {
			    Date ld = (Date) obj[0];
				System.out.println(ld);
			    Double count = (Double) obj[1];
			    System.out.println("valavg"+ count);
			    queryResultndii.put(ld, count);
			}
			List<Object[]> queryResult3 =  dindexdao.getdatabyindex1(4);
			for (Object[] obj : queryResult3 ) {
			    Date ld = (Date) obj[0];
				System.out.println(ld);
			    Double count = (Double) obj[1];
			    System.out.println("valavg"+ count);
			    queryResultvv.put(ld, count);
			}
			List<Object[]> queryResult4 =  dindexdao.getdatabyindex1(5);
			for (Object[] obj : queryResult4 ) {
			    Date ld = (Date) obj[0];
				System.out.println(ld);
			    Double count = (Double) obj[1];
			    System.out.println("valavg"+ count);
			    queryResultvh.put(ld, count);
			}
			
		
	        
			   m.addAttribute("surveyMap", mappedResultsm);
			   m.addAttribute("surveyMapndvi", queryResultndvi);
			   m.addAttribute("surveyMapndii", queryResultndii);
			   m.addAttribute("surveyMapvv", queryResultvv);
			   m.addAttribute("surveyMapvh", queryResultvh);
			return "chart";
		}
		
	}
	@RequestMapping(value = "/vide")
	public String fctvide(Model m) {
		return "vide";
	}

	@RequestMapping(value = "/admin")
	public String home(Model m) {
		// m.addAttribute("message", " This is Spring Web Application example using
		// Spring boot, JSP");

		return "index";
	}
	@RequestMapping(value = "/verifier", method = RequestMethod.POST)
	public String pageverif1(Model m, HttpServletRequest request, HttpSession session) {
		System.out.println("1");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Utilisateur user = userdao.verif(username, password);
		if (user == null) {
			
			session.setAttribute("user", "");
			session.setAttribute("role", "");
			return "redirect:/login";
		} else {
			System.out.println(user.getNom());
			Role role = user.getRole();
			session.setAttribute("role", role);
			session.setAttribute("user", user);
			session.setAttribute("firstname", user.getNom());
			session.setAttribute("lastname", user.getPrenom());
			return "redirect:/home";
		}
	}
@RequestMapping(value = "/login")
	public String loginn(Model m, HttpServletRequest request, HttpSession session){
	session.setAttribute("user", "");
	session.setAttribute("role", "");
	     return "login";
	}
	 @RequestMapping("/root")
	    public ModelAndView root() {
	        return new ModelAndView("root");
	    }
	    @RequestMapping("/user")
	    public ModelAndView user() {
	        return new ModelAndView("user");
	    }
	
	    @RequestMapping(value = "/logout")
		public String fctlogout(Model m, HttpServletRequest request, HttpSession session) {
	    	session.setAttribute("role", "");
			session.setAttribute("user", "");
			return "login";
		}
	    
	    @RequestMapping(value = "/chart1")
		public String fcthome2(Model m, HttpServletRequest request, HttpSession session, @RequestParam("std") Integer std) {
	    	List<Studyarea> listestudy =  (List<Studyarea>) stddao.findAll();
			   m.addAttribute("listestudy", listestudy);
				Map<String, String> surveyMap=new LinkedHashMap<>();
				Map<Date,Float> surveyMapndvi=new LinkedHashMap<>();
				Map<Date,Float> surveyMapndii=new LinkedHashMap<>();
				Map<Date,Float> surveyMapvv=new LinkedHashMap<>();
				Map<Date,Float> surveyMapvh=new LinkedHashMap<>();
				List<String> listesm = new ArrayList<>();
				
				Map<Date, Double>  mappedResultsm = new HashMap<>();
				Map<Date, Double>  queryResultndvi = new HashMap<>();
				Map<Date, Double>  queryResultndii = new HashMap<>();
				Map<Date, Double>  queryResultvv = new HashMap<>();
				Map<Date, Double>  queryResultvh = new HashMap<>();
				List<Object[]> queryResult =  dindexdao.getdatabyindexbystudy(3, std);
				for (Object[] obj : queryResult ) {
				    Date ld = (Date) obj[0];
					System.out.println(ld);
				    Double count = (Double) obj[1];
				    System.out.println("valavg"+ count);
				    mappedResultsm.put(ld, count);
				}
				List<Object[]> queryResult1 =  dindexdao.getdatabyindexbystudy(1, std);
				for (Object[] obj : queryResult1) {
				    Date ld = (Date) obj[0];
					System.out.println(ld);
				    Double count = (Double) obj[1];
				    System.out.println("valavg"+ count);
				    queryResultndvi.put(ld, count);
				}
				List<Object[]> queryResult2 =   dindexdao.getdatabyindexbystudy(2, std);
				for (Object[] obj : queryResult2 ) {
				    Date ld = (Date) obj[0];
					System.out.println(ld);
				    Double count = (Double) obj[1];
				    System.out.println("valavg"+ count);
				    queryResultndii.put(ld, count);
				}
				List<Object[]> queryResult3 =   dindexdao.getdatabyindexbystudy(4, std);
				for (Object[] obj : queryResult3 ) {
				    Date ld = (Date) obj[0];
					System.out.println(ld);
				    Double count = (Double) obj[1];
				    System.out.println("valavg"+ count);
				    queryResultvv.put(ld, count);
				}
				List<Object[]> queryResult4 =   dindexdao.getdatabyindexbystudy(5, std);
				for (Object[] obj : queryResult4 ) {
				    Date ld = (Date) obj[0];
					System.out.println(ld);
				    Double count = (Double) obj[1];
				    System.out.println("valavg"+ count);
				    queryResultvh.put(ld, count);
				}
				
			
		        System.out.println(mappedResultsm);
				   m.addAttribute("surveyMap", mappedResultsm);
				   m.addAttribute("surveyMapndvi", queryResultndvi);
				   m.addAttribute("surveyMapndii", queryResultndii);
				   m.addAttribute("surveyMapvv", queryResultvv);
				   m.addAttribute("surveyMapvh", queryResultvh);
				      
				   
				return "chart";
			}
			
		}
	
