package com.ipartek.formacion.api.restfulservers.alumno.configuration;

import java.io.Serializable;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration  // Especifica que esta clase es para configurar
@EnableWebMvc  // trata la clase como un archivo de configuracion web
@ComponentScan(basePackages="com.ipartek.formacion.api.restfulservers.alumno") // que aplique al siguiente paquete
public class AlumnoRestControllerConfiguration implements Serializable {

	
	
}
