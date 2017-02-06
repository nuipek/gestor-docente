package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@Controller
@RequestMapping(value="/alumnos")
public class AlumnoController {
	
	private static final Logger logger = LoggerFactory.getLogger(AlumnoController.class);

	private ModelAndView mav = null;
	@Inject//equivale a @Autowired
	private AlumnoService aS = null;
	
	@RequestMapping( method=RequestMethod.GET)
	public ModelAndView getAll(){
		mav = new ModelAndView("alumnos/alumnos");
		// Cargar la lista de alumnos 
		List alumnos  = aS.getAll();
		// Enganchar la lista al ModelAndView es igual a añadirla a la request
		mav.addObject("listadoAlumnos", alumnos);
	
		logger.trace("Pasa por getAll()");
		return mav; 
	}
	
	@RequestMapping(value="/deleteAlumno/{id}")
	public String  deleteAlumno(@PathVariable("id") int codigo)
	{
		aS.delete(codigo);
		return "redirect:/alumnos"; // Le indicamos que vaya a la URL y se procese el metodo GET 
									// que procese el getAll
	}
	@RequestMapping(value="/{id}")
	public ModelAndView getById(@PathVariable("id") int codigo)
	{
		ModelAndView mav = new ModelAndView("alumnos/alumno");
		mav.addObject("alumno", aS.getById(codigo));
		return mav;
	}
	
	@RequestMapping(value="/addAlumno")
	public String addAlumno(Model model){
		model.addAttribute("alumno", new Alumno());
		return "alumnos/alumno";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveAlumno(@ModelAttribute("alumno") Alumno alumno, Model model){
		String destino="";
		if (alumno.getCodigo() > Alumno.CODIGO_NULO){
			aS.update(alumno);
		}
		else
		{
		 aS.create(alumno);	
		}
		
		return destino;
		
	}
}
