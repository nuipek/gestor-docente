package com.ipartek.formacion.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ipartek.formacion.alumno.AlumnoServiceRemote;
import com.ipartek.formacion.cliente.ClienteServiceRemote;
import com.ipartek.formacion.controller.pojo.Mensaje;
import com.ipartek.formacion.controller.pojo.MensajeType;
import com.ipartek.formacion.controller.validator.FileValidator;
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

    @Autowired
	private ServletContext servletContext;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CursoController.class);

	
	@InitBinder("curso")  // Esta clase llama al init del servlet de spring para binder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(validator);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), false, 10));
	 
	}
	
    @InitBinder("fichero")
    public void initBinderfichero(WebDataBinder binder) {
    
       binder.addValidators(new FileValidator());
   }


    
	@RequestMapping(method=RequestMethod.GET)
	public String getAll(Model model){
		System.out.println(cS.getAll().size());
		model.addAttribute("listadocursos",cS.getAll());
		return "cursos";
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
		LOGGER.info(curso.toString());
		//System.out.println(curso.getModulos().size());
		//System.out.println("Alumnos" + (curso.getModulos()).  .size());
		model.addAttribute("curso",curso);
		model.addAttribute("listadoProfesores",profesorEJB.getAll());
		model.addAttribute("listadoClientes",clienteEJB.getAll());
		model.addAttribute("listadoAlumnos",AlumnoEJB.getAll());
		return "cursoform";
	}
	
	@RequestMapping(value="/addCurso")
	public String addCurso(Model model){
		Curso curso =  new Curso();
		curso.setActivo(true);
		model.addAttribute("curso",curso);
		model.addAttribute("listadoProfesores",profesorEJB.getAll());
		model.addAttribute("listadoClientes",clienteEJB.getAll());
		model.addAttribute("listadoAlumnos",AlumnoEJB.getAll());
		return "cursoform";
	}
	
	@RequestMapping(value="/deleteCurso/{codigo}")
	public String deleteCurso(@PathVariable("codigo") int codigo){
		
		cS.delete(codigo);
		// Se mantiene el contrabarra del redirect porque no se llava al resolver es un redireccionamiento
		// dentro del requestmapping
		
		return "redirect:/cursos";
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveCurso(@Validated @RequestParam("fichero") MultipartFile file,
				@ModelAttribute("curso") @Valid Curso curso, BindingResult bindingResult, ModelMap model,
				RedirectAttributes redirectMap) throws IOException {	
		/*
		 *  Model 
		 *  ModelMap
		 *  RedirectAttributes 
		 */
		
		String destino = "";
		String txt = "";
		Mensaje mensaje = null;
		
		LOGGER.info(curso.toString());
		if (bindingResult.hasErrors()) {
			// bindingResult.
			LOGGER.info("curso tiene errores");
			model.addAttribute("listadoProfesores", profesorEJB.getAll());
			model.addAttribute("listadoAlumnos", AlumnoEJB.getAll());
			model.addAttribute("listadoClientes", clienteEJB.getAll());
			
			txt = "Los datos de formulario contienen errores";
			mensaje = new Mensaje(txt,MensajeType.MSG_TYPE_DANGER);
			
			 model.addAttribute("mensaje", mensaje);
			destino = "cursoform";
			
		} else {
			destino = "redirect:/cursos";

			txt = "";
			// obtengo el chorro de datos

			 curso.setTemario(uploadFile(file));
			
			if (curso.getCodigo() > Curso.CODIGO_NULO) {
				LOGGER.info(curso.toString());
				LOGGER.info(curso.getProfesor().toString());
				LOGGER.info(curso.getAlumnos().toString());

				try {
					cS.update(curso);
					txt = "El Curso se ha actualizado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
					LOGGER.info(txt);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion update. " + e.toString());
					txt = "Ha habido problemas en la actualización.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					// destino = "cursos/cursoform";
				}

			} else {
				LOGGER.info(curso.toString());
				LOGGER.info(curso.getProfesor().toString());
				LOGGER.info(curso.getAlumnos().toString());
				try {
					cS.create(curso);
					txt = "El curso se ha creado correctamente.";
					mensaje = new Mensaje(MensajeType.MSG_TYPE_SUCCESS);
					LOGGER.info(txt);
				} catch (Exception e) {
					LOGGER.info("Se ha lanzadado una excepcion create. " + e.toString());
					mensaje = new Mensaje(MensajeType.MSG_TYPE_DANGER);
					txt = "Ha habido problemas en la creación del curso.";
					// destino = "cursos/cursoform";
				}
			}
			mensaje.setMsg(txt);
			redirectMap.addFlashAttribute("mensaje", mensaje);
		}
		return destino;
	
	}
	
	private String uploadFile(MultipartFile file) throws IOException {
		String destino = null;
		InputStream in = file.getInputStream();
		// /resources/docs/

		String root = File.separator + "resources" + File.separator + "docs" + File.separator;
		// ruta absoluta del contexto de la aplicación
		String ruta = servletContext.getRealPath(root);
		String copiar = ruta + File.separator + file.getOriginalFilename();
		/*
		LOGGER.info(root);
		LOGGER.info(ruta);
		LOGGER.info("Nombre" + file.getOriginalFilename());
		LOGGER.info("copiar fichero al repositorio" + copiar);
		*/
		// crearme el archivo fisico que no tiene nada con un
		File destination = new File(copiar);
		if (!destination.isDirectory()) {
			// se copia el chorro de bits al archivo fisico
			FileUtils.copyInputStreamToFile(in, destination);
			LOGGER.info(destination.getAbsolutePath());

			LOGGER.info(ruta + File.separator + file.getOriginalFilename());
			// guardo dentro de Curso --> Temario la ruta del fichero

			destino = file.getOriginalFilename();
		}
		return destino;
	}
	
	
	
	@RequestMapping(value="/informeCursos/{codigo}")
	public String informeCurso(@PathVariable("codigo") int codigo, Model model){
		 model.addAttribute("curso",cS.getById(codigo));
		 return "cursodetalle" ;
	}
	
	
	
}
