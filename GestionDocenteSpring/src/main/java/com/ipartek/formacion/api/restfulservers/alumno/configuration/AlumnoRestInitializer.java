/**
 * 
 */
package com.ipartek.formacion.api.restfulservers.alumno.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.ipartek.formacion.api.restfulservers.CORSFilter;

/**
 * @author Sergio Aparicio
 *
 */
public class AlumnoRestInitializer // extends AbstractAnnotationConfigDispatcherServletInitializer 
{

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getRootConfigClasses()
	 */
	//@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[]{AlumnoRestControllerConfiguration.class};
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer#getServletConfigClasses()
	 */
	//@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.support.AbstractDispatcherServletInitializer#getServletMappings()
	 */
	//@Override
	protected String[] getServletMappings() {
	
		return new String[]{"/"};
	}

	//@Override
	protected Filter[] getServletFilters() {
	    Filter[] filter = new Filter[]{new CORSFilter()};
		return filter;
	}
	
	

}
