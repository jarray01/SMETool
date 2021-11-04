package tn.noureddine.controller;

import java.io.IOException;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import tn.noureddine.dao.IShapedao;




@Component
public class MyTask {
	@Autowired
	IShapedao shapedao;
	@Scheduled(cron = "*/30 * * * * ?")
	public void scheduledTask1() throws IOException {
	
		List<String> listgeom = shapedao.getgeometrycolumln();	
		for ( String s : listgeom) {
		//	shapedao.insertlistgson(s);	 
		//	System.out.println(s);
		//	shapedao.insertlistgson(s);
		}	
	}
	
	

	
}
