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

import com.ipartek.formacion.dbms.persistence.Profesor;
import com.ipartek.formacion.service.interfaces.ProfesorService;

@RestController
@RequestMapping(value="/api/profesores")
public class ProfesorRestController {

		@Autowired
		private ProfesorService pS;
		/*
		http://gestiondocente/api/profesors/i
		GET    --> GETBYID
		PUT    --> CREATE
		DELETE --> DELETE
		
		http://gestiondocente/api/profesors
		GET  --> GETALL
		POST --> CREATE
		*/
		
		@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
		public ResponseEntity<Profesor> getById(@PathVariable("codigo") int codigo){
			
			Profesor profesor = pS.getById(codigo);
			ResponseEntity<Profesor> response=null;
			if (profesor==null){ // No existe 404
				response = new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
			}
			else{ // Correcto 200
				response = new ResponseEntity<Profesor>(profesor, HttpStatus.OK);
			}
			return response;
		}
		
		@RequestMapping(method= RequestMethod.GET)
		public ResponseEntity<List<Profesor>> getAll(){
			List<Profesor> profesors= pS.getAll();
			ResponseEntity<List<Profesor>>response=null;
			
			if(profesors==null || profesors.isEmpty()){ // Sin contenido 204
				response = new ResponseEntity<List<Profesor>>(HttpStatus.NO_CONTENT);
			}
			else
			{
			 	response = new ResponseEntity<List<Profesor>>(profesors,HttpStatus.OK);
			}
			
			return response;
			
		}
		
		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<Void>create(@RequestBody Profesor profesor, 
										               UriComponentsBuilder ucBuilder){
			
			ResponseEntity<Void> response = null;
			boolean duplicado = pS.profesorDniDuplicado(profesor.getDni(),profesor.getCodigo());
			
			if (duplicado){
				response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			else{
				Profesor aux;
				try {
					aux = pS.create(profesor);
					// response = new ResponseEntity<Void>(HttpStatus.CREATED); se podria realizar asi
					//Modificamos los encabezados hhtp para hacer una redireccion al metodo 
					// getbyId del RestController 
					//para ver que se ha creado correctamente
					HttpHeaders headers = new HttpHeaders();
					headers.setLocation(ucBuilder.path("/api/profesors/{codigo}")
							.buildAndExpand(aux.getCodigo()).toUri());
					
					response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
				} catch (Exception e) {
					
					response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
				}
				
			}
				
			
			return response;
		}
		
		@RequestMapping(value="/{codigo}", method = RequestMethod.PUT)
		public ResponseEntity<Profesor> update(@PathVariable("codigo")int id, 
											 @RequestBody Profesor profesor){
			
			ResponseEntity<Profesor> response = null;
			Profesor profe = pS.getById(id);
			
			if (profe == null){
				response = new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
			}else{
				try {
					profe = pS.update(profesor);
					response = new ResponseEntity<Profesor>(profe, HttpStatus.ACCEPTED);
				} catch (Exception e) {
					
				}
				response = new ResponseEntity<Profesor>(HttpStatus.NOT_ACCEPTABLE);
			}
			return response;
		}
		
		@RequestMapping(value="/{codigo}", method = RequestMethod.DELETE)
		public ResponseEntity<Profesor> deleteById(@PathVariable("codigo") int id){
			
			ResponseEntity<Profesor> response = null;
			Profesor profe = pS.getById(id);
			
			if (profe == null){
				response = new ResponseEntity<Profesor>(HttpStatus.NOT_FOUND);
			}else{
				try {
					pS.delete(id);
					response = new ResponseEntity<Profesor>(HttpStatus.NO_CONTENT);
				} catch (Exception e) {
					response = new ResponseEntity<Profesor>(HttpStatus.NOT_ACCEPTABLE);
				}
				
			}
				
			return response;
		}
	
}
