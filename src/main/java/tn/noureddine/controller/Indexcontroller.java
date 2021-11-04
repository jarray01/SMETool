package tn.noureddine.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import tn.noureddine.dao.Iindexdao;


@Controller
@RequestMapping("/index")
public class Indexcontroller {
	@Autowired
	Iindexdao indexdao;
	public void setIndexdao(Iindexdao indexdao) {
		this.indexdao = indexdao;
	}

}
