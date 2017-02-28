package com.ipartek.formacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ipartek.formacion.service.interfaces.CursoService;

@Controller
@RequestMapping(value="/cursos")
public class CursoController {
	
    @Autowired
	private CursoService cS;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAll(Model model){
		System.out.println(cS.getAll().size());
		model.addAttribute("listadocursos",cS.getAll());
		return "cursos/cursos";
	}
	
	
	
	@RequestMapping("/{codigo}")
	public String getById(@PathVariable("codigo") long codigo, Model model)
	{
		model.addAttribute("curso", cS.getById(codigo));
		return "cursos/curso";
	}
}
