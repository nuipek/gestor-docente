package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@Controller // marcar la clase como controlador -- como si fuera un servlet
@RequestMapping(value="/profesores") // que peticiones tiene que gestionar
public class ProfesorController {

 private static final Logger logger = LoggerFactory.getLogger(ProfesorController.class);
 private ModelAndView mav=null;
 @Inject
 private ProfesorService pS = null;
	
 @RequestMapping(method=RequestMethod.GET)
 public ModelAndView getAll()
 {
	 mav = new ModelAndView("profesores/profesores");
	 List<Profesor> profesores =  pS.getAll();
	
	 mav.addObject("listaProfesores", profesores);
	 /*
	 for(Profesor profe : profesores){
		 System.out.println(profe.getNombre());
		 System.out.println(profe.getApellidos());
	 }
	 */
	 
	 logger.info("Pasa por getAll()");
	
	 return mav;
 }
 
}
