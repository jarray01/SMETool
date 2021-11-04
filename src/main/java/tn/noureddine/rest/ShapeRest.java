package tn.noureddine.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tn.noureddine.dao.IShapedao;

@CrossOrigin
@RestController
@RequestMapping(value = "/service/shape")
public class ShapeRest {
	@Autowired
	IShapedao shpdao;
	public void setDao(IShapedao dao) {
		this.shpdao = dao;
	}
	@RequestMapping(value="/list", method=RequestMethod.GET, produces = "application/json")
	public String fct(){
		String s= "{\"type\":\"FeatureCollection\",\"features\":[";
		
		s= s.concat(shpdao.getgeojson());
		s =s.concat("]}");
		return s;
		
		
	}
	@RequestMapping(value="/list/{id}", method=RequestMethod.GET, produces = "application/json")
	public String fct1(@PathVariable("id") Integer id){
		String s= "{\"type\":\"FeatureCollection\",\"features\":[";
		
		s= s.concat(shpdao.getgeojson1(id));
		s =s.concat("]}");
		return s;
		
		
	}
	@RequestMapping(value="/list/nom/{id}", method=RequestMethod.GET, produces = "application/json")
	public String fctnom(@PathVariable("id") Integer id){
		
		return shpdao.getnom(id);
		
		
	}
	
	@RequestMapping(value="/lim_me32", method=RequestMethod.GET, produces = "application/json")
	public String fctlim(){
		String s= "{\"type\":\"FeatureCollection\",\"features\":[";
		
		s= s.concat(shpdao.getgeojson());
		s =s.concat("]}");
		return s;
		
		
	}
	@RequestMapping(value="/test", method=RequestMethod.GET, produces = "application/json")
	public String fctnom1(){	
		return shpdao.getgeo();	
	}
	@RequestMapping(value="/chammakh", method=RequestMethod.GET, produces = "application/json")
	public String fctchk(){	
		return shpdao.getjsonck();	
	}
	@RequestMapping(value="/dardhaoui", method=RequestMethod.GET, produces = "application/json")
	public String fctdd(){	
		return shpdao.getjsonck();	
	}
	
	@RequestMapping(value="/rf12012017rf", method=RequestMethod.GET, produces = "application/json")
	public String fctrf(){	
		return shpdao.getjsonrf();	
	}


}
