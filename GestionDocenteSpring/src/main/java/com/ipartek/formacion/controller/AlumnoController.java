package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
		List alumnos  = null;
		// Enganchar la lista al ModelAndView es igual a añadirla a la request
		mav.addObject("listadoAlumnos", alumnos);
		logger.trace("Pasa por getAll()");
		return mav; 
	}
}
