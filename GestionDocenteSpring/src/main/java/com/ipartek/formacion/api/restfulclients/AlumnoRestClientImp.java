package com.ipartek.formacion.api.restfulclients;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.ipartek.formacion.dbms.persistence.Alumno;


@Transactional
@Service
public class AlumnoRestClientImp implements AlumnoRestClient{

	@Override
	public List<Alumno> getAll() {
		List<Alumno>alumnos = null;
		
		RestTemplate template = new RestTemplate();
		//El parse de JSON/XML a Objeto Java lo hace la biblioteca JACKSON
		alumnos = template.getForObject(AlumnoRestClient.URL+".json", List.class);
		return alumnos;
	}

	@Override
	public Alumno getById(int codigo) {
		Alumno alumno = null;
		RestTemplate template = new RestTemplate();
		alumno = template.getForObject(AlumnoRestClient.URL+"/"+codigo, Alumno.class);
		return alumno;
	}

	@Override
	public Alumno Update(Alumno alumno) {
		RestTemplate template = new RestTemplate();
		template.put(AlumnoRestClient.URL + "/" + alumno.getCodigo(), alumno);
		return alumno;
	}

	@Override
	public Alumno create(Alumno alumno) {
		RestTemplate template = new RestTemplate();
		
		//template.postForObject(AlumnoRestClient.URL, alumno, Alumno.class); IGUAL A
		URI uri = template.postForLocation(AlumnoRestClient.URL, alumno);
		Alumno alum = template.getForObject(uri, Alumno.class);
		
		return alum;
	}

	@Override
	public void delete(int codigo) {
		RestTemplate template = new RestTemplate();
		
		template.delete(AlumnoRestClient.URL + "/" + codigo);
		
		
	}

	

}
