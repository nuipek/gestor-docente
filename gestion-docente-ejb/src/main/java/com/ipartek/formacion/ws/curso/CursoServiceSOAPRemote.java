package com.ipartek.formacion.ws.curso;

import java.util.List;

import javax.ejb.Remote;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import com.ipartek.formacion.persistence.Curso;

@WebService
@SOAPBinding(style=Style.DOCUMENT,use = Use.LITERAL)
@Remote
public interface CursoServiceSOAPRemote {

	@WebMethod(operationName="obtenerTodos")
	public List<Curso> getAll();
	
	
}
