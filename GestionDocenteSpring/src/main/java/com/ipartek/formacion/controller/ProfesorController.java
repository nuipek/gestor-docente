package com.ipartek.formacion.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.dbms.persistence.Alumno;
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
	 mav = new ModelAndView("profesores");
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
 
 @RequestMapping(value="save", method=RequestMethod.POST)
	public String saveProfesor( Model model, @ModelAttribute("profesor")  @Valid Profesor profesor,
							 BindingResult bindingResult){
		String destino="";
		
		if (bindingResult.hasErrors()){
			logger.info("Profesor tiene errores");
			destino = "profesor";
		}
		else
		{
			destino="redirect:/profesores";
			if (profesor.getCodigo() > Profesor.CODIGO_NULO){
				logger.info("UPDATE PROFESOR - " + profesor.toString() );
				pS.update(profesor);
			}
			else
			{
				logger.info("CREAR PROFESOR - " + profesor.toString() );
				pS.create(profesor);	
			}
		}
	return destino;
		
	}
 
 @RequestMapping(value="/addProfesor")
 public String addProfesor(Model model){
	Profesor profesor = new Profesor();
	model.addAttribute("profesor", profesor);
	return "profesor";
	 
  }
 
 @RequestMapping(value="/deleteProfesor/{id}")
 public String delete(@PathVariable("id") int codigo){
	 
	 pS.delete(codigo);
	 return "redirect:/profesores";
 }
 
 @RequestMapping(value="/{id}")
 public ModelAndView getById(@PathVariable("id")  int codigo){
	 ModelAndView mav = new ModelAndView("profesor");
	 Profesor profesor = pS.getById(codigo);
	 mav.addObject("profesor", profesor);
	 return mav;
 }
}
