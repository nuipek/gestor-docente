package com.ipartek.formacion.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ipartek.formacion.controller.validator.ClienteValidator;
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;

@Controller
@RequestMapping(value="/clientes")
public class ClienteController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	private ModelAndView mav=null;
	@Inject
	private ClienteService cS=null;
	
	@Resource(name="clienteValidator")
	private Validator validator=null;
	
	@InitBinder  // Esta clase llama al init del servlet de spring para binder
	private void initBinder(WebDataBinder binder){
		logger.info("InitBinder" + validator.toString());
		binder.setValidator(validator);
	}
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView getAll(){
		mav = new ModelAndView("clientes/clientes");
		mav.addObject("listadoClientes", cS.getAll());
		logger.trace("getAll");
		return mav;
	}
	
	@RequestMapping(value="/addCliente")
	public String addCliente(Model model){
		model.addAttribute("cliente",new Cliente());
		return "/clientes/cliente";
	}
	
	@RequestMapping(value="/deleteCliente/{codigo}")
	public String deleteCliente(@PathVariable("codigo") int codigo){
		cS.delete(codigo);
		logger.info("El codigo seleccionado para borrar es " + codigo);
		return "redirect:/clientes";
	}
	
	@RequestMapping(value="/{codigo}")
	public ModelAndView getById(@PathVariable("codigo") int codigo)
	{
	
		mav = new ModelAndView("clientes/cliente");
		mav.addObject("cliente", cS.getById(codigo));
		//model.addAttribute("cliente",cS.getById(codigo));
		mav.setViewName("clientes/cliente");
		return mav;
	}
	
	@RequestMapping(value="/informeCliente/{codigo}")
	public ModelAndView verInforme(@PathVariable("codigo") int codigo)
	{   ModelAndView mav = new ModelAndView("clientes/informe");
	logger.info("Aqui informeCliente");
	   //model.addAttribute(cS.getInforme(codigo));
	   Cliente cliente = cS.getInforme(codigo);
	   logger.info(cliente.toString());
		mav.addObject("cliente",cliente);
		return mav;
	}
	
	@RequestMapping(value="save", method=RequestMethod.POST)
	public String saveCliente(Model model, @ModelAttribute("cliente") @Validated  Cliente cliente, 
							  BindingResult bindingResult){
		
		String destino="";
		logger.info("El formulario de salvado");
		
		if (bindingResult.hasErrors()){
			logger.error("El formulario tiene errores");
			destino = "clientes/cliente";
		}
		else
		{
			destino = "redirect:/clientes";
			if (cliente.getCodigo() > Cliente.CODIGO_NULO){
				cS.update(cliente);
				logger.info("Es una operacion de actualizacion de datos");
			}
			else
			{
				cS.create(cliente);
				logger.info("Es una operacion para crear un nuevo cliente");
			}
		}
		return destino;
	}
	
	
	
	
}
