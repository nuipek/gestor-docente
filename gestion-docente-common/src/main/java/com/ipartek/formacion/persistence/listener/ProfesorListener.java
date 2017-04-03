package com.ipartek.formacion.persistence.listener;

import javax.persistence.PostLoad;

import com.ipartek.formacion.persistence.Profesor;

public class ProfesorListener {
	
		@PostLoad
		public void profesorPostLoad(Profesor profesor) {
			profesor.setNombreCompleto(profesor.getNombre() + " " + profesor.getApellidos());
		}
	
}
