package com.ipartek.formacion.api.restfulservers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteService;

@RestController
@RequestMapping(value="/api/clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService cS;
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ResponseEntity<Cliente> getById(@PathVariable("codigo")int id){
		
		ResponseEntity<Cliente> response=null;
		Cliente cliente = cS.getById(id);
		
		if (cliente == null){
			
		  response = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
		else{
			response = new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
		}
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> getAll(){
		
		ResponseEntity<List<Cliente>> response = null;
		
		List<Cliente> clientes = cS.getAll();
		
		if (clientes == null || clientes.isEmpty()){
			response = new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
		}else
		{
			response = new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
		}
		
		return response;
	}
	
	@RequestMapping(value="/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Cliente> deleteById(@PathVariable("codigo") int id){
		
		ResponseEntity<Cliente> response = null;
		
		Cliente cliente = cS.getById(id);
		
		if (cliente == null){
			response = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}else{
			try {
				cS.delete(id);
				response = new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				response = new ResponseEntity<Cliente>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
			
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create
								   (@RequestBody Cliente cliente,UriComponentsBuilder ucBuilder){
		
		ResponseEntity<Void>response = null;
		Cliente cli=null;
		
	    boolean duplicado = cS.clienteIdentificativoDuplicado(cliente.getIdentificativo(), cliente.getCodigo());
		
		if (duplicado){
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}else{
			try {
				cli = cS.create(cliente);
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/clientes/{codigo}")
						.buildAndExpand(cli.getCodigo()).toUri());
				
				response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
				
			} catch (Exception e) {
				response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
		return response;
	}
	
	@RequestMapping(value="/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@PathVariable("codigo") int id, @RequestBody Cliente cliente){
		ResponseEntity<Cliente> response = null;
		Cliente cli = cS.getById(id);
		
		if (cli == null){
			response = new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}else{
			try {
				cli = cS.update(cliente);
				response = new ResponseEntity<Cliente>(cli, HttpStatus.ACCEPTED);
			} catch (Exception e) {
				
			}
			response = new ResponseEntity<Cliente>(HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
		
	}
}
