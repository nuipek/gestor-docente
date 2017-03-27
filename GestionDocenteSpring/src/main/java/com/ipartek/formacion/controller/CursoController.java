package com.ipartek.formacion.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.persistence.Curso;
import com.ipartek.formacion.service.interfaces.AlumnoService;
import com.ipartek.formacion.service.interfaces.CursoService;

@Controller
@RequestMapping(value="/cursos")
public class CursoController {
	
    @Autowired
	private CursoService cS;

    
    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);
	
	@RequestMapping(method=RequestMethod.GET)
	public String getAll(Model model){
		System.out.println(cS.getAll().size());
		model.addAttribute("listadocursos",cS.getAll());
		return "cursos/cursos";
	}
	
	
	@RequestMapping(value="/{codigocurso}/detalles/{codigodetalle}")
	public ModelAndView getDetalleByCurso(@PathVariable("codigocurso") long codigocurso, 
										  @PathVariable("codigodetalle") long codigodetalle)
	{
		ModelAndView mav = null; //new ModelAndView("alumnos/alumno");
	
		//mav.addObject("alumno", aS.getById(codigo));
		return mav;
	}
	
	@RequestMapping(value="/{codigocurso}/alumnos/{codigodetalle}")
	public ModelAndView getAlumnoByCurso(@PathVariable("codigocurso") long codigocurso, 
										  @PathVariable("codigodetalle") long codigodetalle)
	{
		ModelAndView mav = null; //new ModelAndView("alumnos/alumno");
	
		//mav.addObject("alumno", aS.getById(codigo));
		return mav;
	}
	
	
	
	
	@RequestMapping("/{codigo}")
	public String getById(@PathVariable("codigo") long codigo, Model model)
	{
		Curso curso =  cS.getById(codigo);
		logger.info(curso.toString());
		//System.out.println(curso.getModulos().size());
		//System.out.println("Alumnos" + (curso.getModulos()).  .size());
		model.addAttribute("curso",curso);
		return "cursos/curso";
	}
}
