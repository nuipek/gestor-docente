package com.ipartek.formacion.ws.curso;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.ipartek.formacion.curso.CursoServiceRemote;
import com.ipartek.formacion.persistence.Curso;

/**
 * Session Bean implementation class CursoServiceBeanSOAP
 */
@WebService(endpointInterface="com.ipartek.formacion.ws.curso.CursoServiceSOAPRemote",serviceName="cursosws")
@Stateless(name = "cursoServiceSOAP")
public class CursoServiceBeanSOAP implements CursoServiceSOAPRemote {

    /**
     * Default constructor. 
     */
	//Para pasar un EJB a Spring con JNDI
	
	// CDI inyeccion de un objeto EJB para disponer del objeto Equivalente a Autowired para EJB
	@EJB
	CursoServiceRemote cS;
	
    public CursoServiceBeanSOAP() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<Curso> getAll() {
		
		return cS.getAll();
	}

}
