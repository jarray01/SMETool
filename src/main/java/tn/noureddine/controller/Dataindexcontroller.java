package tn.noureddine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import tn.noureddine.dao.Idataindexdao;
import tn.noureddine.dao.Iindexdao;


@Controller
@RequestMapping("/dataindex")
public class Dataindexcontroller {
	@Autowired
	Idataindexdao dataindexdao;
	public void setDataindexdao(Idataindexdao dataindexdao) {
		this.dataindexdao = dataindexdao;
	}
}
