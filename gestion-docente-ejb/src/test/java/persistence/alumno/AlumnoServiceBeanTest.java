package persistence.alumno;

import static org.junit.Assert.assertNotNull;

import java.io.File;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ipartek.formacion.alumno.AlumnoServiceBean;
import com.ipartek.formacion.alumno.AlumnoServiceRemote;
import com.ipartek.formacion.persistence.Alumno;
import com.ipartek.formacion.persistence.Cliente;
import com.ipartek.formacion.persistence.Curso;
import com.ipartek.formacion.persistence.Profesor;


@RunWith(Arquillian.class)
public class AlumnoServiceBeanTest {

	@EJB
	AlumnoServiceRemote as;
	
	@Deployment
	public static Archive<?> createDeployment(){
		//Cargamos el contexto de la aplicacion
		
		
	     // importa los archivos de pom.xml de la aplicacion para poder cargar las librerias necesarias para poder 
		// crear el contexto
        File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
                .withTransitivity().asFile();
	    // JavaArchive ja = null; para archivos JAR
		WebArchive wa = ShrinkWrap.create(WebArchive.class, "test.war")
				        .addClass(Alumno.class)
				        .addClass(Curso.class)
				        .addPackage(AlumnoServiceBean.class.getPackage())
				        /* Esta se quitan porque las hemos añadido con el addPackage que carga todas las clases 
				         * correspondientesa al paquete que hemos seleccionado
				        .addClass(AlumnoServiceBean.class)
				        .addClass(AlumnoServiceRemote.class)
				        */
				        .addClass(Profesor.class)
				        .addClass(Cliente.class)
				        .addAsLibraries(files)
				        .addAsWebInfResource(EmptyAsset.INSTANCE,"beans.xml");
				        
	
        return wa;
		
	}
	
	@Test
	public void testIsDeployed(){
		assertNotNull(as);
	}
	
	
}

