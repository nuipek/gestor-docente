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
import com.ipartek.formacion.service.interfaces.AlumnoService;

@RestController
@RequestMapping(value="/api/alumnos")
public class AlumnoRestController {

	@Autowired
	private AlumnoService aS;
	/*
	http://gestiondocente/api/alumnos/i
	GET    --> GETBYID
	PUT    --> CREATE
	DELETE --> DELETE
	
	http://gestiondocente/api/alumnos
	GET  --> GETALL
	POST --> CREATE
	*/
	
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ResponseEntity<Alumno> getById(@PathVariable("codigo") int codigo){
		
		Alumno alumno = aS.getById(codigo);
		ResponseEntity<Alumno> response=null;
		if (alumno==null){ // No existe 404
			response = new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}
		else{ // Correcto 200
			response = new ResponseEntity<Alumno>(alumno, HttpStatus.OK);
		}
		return response;
	}
	
	@RequestMapping(method= RequestMethod.GET)
	public ResponseEntity<List<Alumno>> getAll(){
		List<Alumno> alumnos= aS.getAll();
		ResponseEntity<List<Alumno>>response=null;
		
		if(alumnos==null || alumnos.isEmpty()){ // Sin contenido 204
			response = new ResponseEntity<List<Alumno>>(HttpStatus.NO_CONTENT);
		}
		else
		{
		 	response = new ResponseEntity<List<Alumno>>(alumnos,HttpStatus.OK);
		}
		
		return response;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void>create(@RequestBody Alumno alumno, 
									               UriComponentsBuilder ucBuilder){
		
		ResponseEntity<Void> response = null;
		boolean duplicado = aS.alumnoDniDuplicado(alumno.getDni(),alumno.getCodigo());
		
		if (duplicado){
			response = new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		else{
			Alumno aux;
			try {
				aux = aS.create(alumno);
				// response = new ResponseEntity<Void>(HttpStatus.CREATED); se podria realizar asi
				//Modificamos los encabezados hhtp para hacer una redireccion al metodo 
				// getbyId del RestController 
				//para ver que se ha creado correctamente
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(ucBuilder.path("/api/alumnos/{codigo}")
						.buildAndExpand(aux.getCodigo()).toUri());
				
				response = new ResponseEntity<Void>(headers,HttpStatus.CREATED);
			} catch (Exception e) {
				
				response = new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
			}
			
		}
			
		
		return response;
	}
	
	@RequestMapping(value="/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Alumno> update(@PathVariable("codigo")int id, 
										 @RequestBody Alumno alumno){
		
		ResponseEntity<Alumno> response = null;
		Alumno alum = aS.getById(id);
		
		if (alum == null){
			response = new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}else{
			try {
				alum = aS.update(alumno);
				response = new ResponseEntity<Alumno>(alum, HttpStatus.ACCEPTED);
			} catch (Exception e) {
				
			}
			response = new ResponseEntity<Alumno>(HttpStatus.NOT_ACCEPTABLE);
		}
		return response;
	}
	
	@RequestMapping(value="/{codigo}", method = RequestMethod.DELETE)
	public ResponseEntity<Alumno> deleteById(@PathVariable("codigo") int id){
		
		ResponseEntity<Alumno> response = null;
		Alumno alum = aS.getById(id);
		
		if (alum == null){
			response = new ResponseEntity<Alumno>(HttpStatus.NOT_FOUND);
		}else{
			try {
				aS.delete(id);
				response = new ResponseEntity<Alumno>(HttpStatus.NO_CONTENT);
			} catch (Exception e) {
				response = new ResponseEntity<Alumno>(HttpStatus.NOT_ACCEPTABLE);
			}
			
		}
			
		return response;
	}
	
}
