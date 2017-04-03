package com.ipartek.formacion.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.alumno.AlumnoServiceRemote;
import com.ipartek.formacion.cliente.ClienteServiceRemote;
import com.ipartek.formacion.persistence.Curso;
import com.ipartek.formacion.profesor.ProfesorServiceRemote;
import com.ipartek.formacion.service.interfaces.AlumnoService;
import com.ipartek.formacion.service.interfaces.ClienteService;
import com.ipartek.formacion.service.interfaces.CursoService;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@Controller
@RequestMapping(value="/cursos")
public class CursoController {
	
    @Autowired
	private CursoService cS;
    
	@Autowired
	ProfesorServiceRemote profesorEJB;
    
    @Autowired
	ClienteServiceRemote clienteEJB;
    
    @Autowired
	AlumnoServiceRemote AlumnoEJB;
    
    @Resource(name="cursoValidator")
    Validator validator;

    
    private static final Logger logger = LoggerFactory.getLogger(CursoController.class);
	
	@InitBinder  // Esta clase llama al init del servlet de spring para binder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), false, 10));
	 
	}
    
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
		model.addAttribute("listadoProfesores",profesorEJB.getAll());
		model.addAttribute("listadoClientes",clienteEJB.getAll());
		model.addAttribute("listadoAlumnos",AlumnoEJB.getAll());
		return "cursos/cursoform";
	}
	
	@RequestMapping(value="/addCurso")
	public String addCurso(Model model){
		Curso curso =  new Curso();
		curso.setActivo(true);
		model.addAttribute("curso",curso);
		model.addAttribute("listadoProfesores",profesorEJB.getAll());
		model.addAttribute("listadoClientes",clienteEJB.getAll());
		model.addAttribute("listadoAlumnos",AlumnoEJB.getAll());
		return "cursos/cursoform";
	}
	
	@RequestMapping(value="/deleteCurso/{codigo}")
	public String deleteCurso(@PathVariable("codigo") int codigo){
		
		cS.delete(codigo);
		return "redirect:/cursos";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveCursos(Model model, @ModelAttribute("curso") @Valid  Curso curso){ 
	   String destino = "";
	   destino = "redirect:/cursos";
	   System.out.println(curso.getCodigo());
		if (curso.getCodigo() > 0){
			logger.info(curso.toString()+"Es una operacion de actualizacion de datos del curso");
			cS.update(curso);
		}
		else
		{
			cS.create(curso);
			logger.info("Es una operacion para crear un nuevo curso");
		}
		
		return destino;
	}
	
	@RequestMapping(value="/informeCursos/{codigo}")
	public String informeCurso(@PathVariable("codigo") int codigo, Model model){
		 model.addAttribute("curso",cS.getById(codigo));
		 return "cursos/curso" ;
	}
	
	
	
}
