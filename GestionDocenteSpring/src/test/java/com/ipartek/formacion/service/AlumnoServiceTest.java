package com.ipartek.formacion.service;



import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.service.interfaces.AlumnoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:application-context-test.xml")
public class AlumnoServiceTest {

	@Autowired
	AlumnoService aS;
	
	int codigos[];
	
	Alumno alumno;
	List<Alumno> alumnos2;
	
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    	// Antes de lanzar el metodo 
    	alumno = new Alumno();
    	alumno.setNombre("Jon Koldobika");
    	alumno.setApellidos("Ajuriagogeaskoa belaustegigoitia");
    	alumno.setDni("30685168z");
    	alumno.setCodigoPostal(48006);
    	
    	codigos  = new int[8];
    	codigos[0] = 0;
    	codigos[1] = 1;
    	codigos[2] = 2;
    }

    @After
    public void tearDown() throws Exception {
    	// despues del metodo
    	alumno = null;
    	
    }
    
	@Test
    public void TestClase(){
		//assertEquals("class com.ipartek.formacion.service.AlumnoServiceImp",this.aS.getClass());
	}
	
	@Test(timeout=1000)
	public void getAllTest(){
		List<Alumno> alumnos = aS.getAll();
	int size=3;
	assertEquals("numero de alumnos ",size,alumnos.size());
		
	}
	
	@Test(timeout=1000)
	public void getByIdTest(){
	
	
	
	for (int i=0;i<codigos.length;i++){
		Alumno alum = aS.getById(codigos[i]);
		assertNotNull("El Alumno tiene que existir. El alumno con codigo " + codigos[i] + "esta en base de datos",alum);
		assertEquals("El codigo del alumno no coincide, el codgio enviado es " + codigos[i] + " y el recibidos es " 
				+ alum.getCodigo(), codigos[i] , alum.getCodigo());
	  }
	
	}

	

	
	
	//@Ignore
	//@Test(expected = DuplicateKeyException.class)
	@Test
	public void createTest() {
		Alumno alum = aS.create(alumno);
		//assertTrue("El alumno es nulo", alum == null); equivalente a
		assertNotNull("El alumno es nulo", alum);
		assertTrue("el codigo debe ser mayor que cero", alum.getCodigo() > 0); // Esperamos que sea TRUE si no mostramos el mensaje
		assertFalse("el codigo no debe ser menor que cero", alum.getCodigo() < 0); 
		//Todos los atributos
		assertEquals("el nombre no es identico", alum.getNombre(), alumno.getNombre());
		// Comparamos que sea el mismo objeto
		assertEquals("Los datos no son identicos", alum, aS.getById(alum.getCodigo()));
		aS.delete(alum.getCodigo());
		
		
	}
	
	@Test
	public void deleteTest(){
		Alumno alum = aS.create(alumno);
		aS.delete(alum.getCodigo());
		assertNotNull("El Alumno no se ha borrado", aS.getById(alum.getCodigo()));
	}
	
	
	

	
}
