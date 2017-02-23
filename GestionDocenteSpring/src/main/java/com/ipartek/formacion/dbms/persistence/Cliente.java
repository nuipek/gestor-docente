package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;


public class Cliente implements Serializable,Comparable<Cliente> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	private String email;
	private String identificativo;
	private boolean activo;
	
	private Map<Long,com.ipartek.formacion.persistence.Curso> cursos;
	
	


	public static final int CODIGO_NULO = -1;
	

	public Cliente() {
		super();
	
	
		this.nombre="";
		this.apellidos="";

		this.email = "";
		this.direccion = "";
	
		this.codigo = CODIGO_NULO;
	
		this.telefono="94";
		
		this.cursos = null;
		
		
	}


	
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getIdentificativo() {
		return identificativo;
	}


	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}
	
	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((identificativo == null) ? 0 : identificativo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		boolean iguales = false;
		if (obj instanceof Alumno) {
			Alumno alum = (Alumno) obj;
			if (this.codigo == alum.getCodigo()) {
				iguales = true;
			}
		}
		return iguales;
	}


	@Override
	public int compareTo(Cliente o) {
		// TODO Auto-generated method stub
		return 0;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public Map<Long, com.ipartek.formacion.persistence.Curso> getCursos() {
		return cursos;
	}


	public void setCursos(Map<Long, com.ipartek.formacion.persistence.Curso> cursos) {
		this.cursos = cursos;
	}

	
	
	
}
