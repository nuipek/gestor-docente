package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.ipartek.formacion.service.Util;

/**
 * 
 * @author Urko Villanueva
 *
 */
public class Alumno implements Comparable<Alumno>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698866485450376235L;
	public static final int CODIGO_NULO = -1;
	public static final long YEAR_MILISEGUNDOS = 31556900000L;
	public static final long DAY_MILISEGUNDOS = 86400001L;
	
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fNacimiento;
	private String email;
	private String direccion;
	

	private String telefono;
	private String poblacion;
	private int codigoPostal;
	
	private int codigo;
	private boolean activo;
	private int nHermanos;
	
	private List<com.ipartek.formacion.persistence.Curso> cursos;

	public Alumno() {
		super();// constructor de la clase padre
		long tiempo=0L;
		this.nombre="";
		this.apellidos="";
		this.dni="";
		this.email = "";
		this.direccion = "";
		this.fNacimiento = new Date();
		
		tiempo = (long)new Date().getTime();
		tiempo -=   (18 * YEAR_MILISEGUNDOS + DAY_MILISEGUNDOS);
		Date fecha = new Date(tiempo);
		this.fNacimiento = fecha;
		this.codigo = CODIGO_NULO;
		this.activo = true;
		this.nHermanos = 0;
		this.codigoPostal=48;
		this.poblacion="";
		this.telefono="94";
		
	
		
		//this.cursos = new ArrayList<com.ipartek.formacion.persistence.Curso>();
		
		

	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return this.getCodigo() + " " + this.getApellidos() + ", " + this.getNombre() + " " + this.getDni();
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getnHermanos() {
		return nHermanos;
	}

	public void setnHermanos(int nHermanos) {
		this.nHermanos = nHermanos;
	}
	
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	/**
	 * Se usa en el caso de ordenamiento de List o Array
	 * 
	 * @param o
	 * @return
	 */
	@Override
	public int compareTo(Alumno o) {
		return this.getApellidos().compareToIgnoreCase(o.getApellidos());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alumno other = (Alumno) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getDni() {
		return dni;
	}

	public void setDni(String dni){
		final String regex = "\\d{8}[A-Za-z]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dni);
		if (!matcher.find() && Util.validarDni(dni)) {
			//throw new PersonaException(PersonaException.COD_DNI_ERROR, PersonaException.MSG_DNI_ERROR);
		}
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre.length() < 3) {
			//throw new PersonaException(PersonaException.COD_LONGITUD_NOMBRE, PersonaException.MSG_LONGITUD_NOMBRE);
		}
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(Date fNacimiento)  {
		/*GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int anyo18ago = gc.get(GregorianCalendar.YEAR) - 18;// 1999
		gc.set(GregorianCalendar.YEAR, anyo18ago);

		if (gc.getTime().before(fNacimiento)) {
			//throw new PersonaException(PersonaException.COD_EDAD_ERROR, PersonaException.MSG_EDAD_ERROR);
		}
*/
		this.fNacimiento = fNacimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		return result;
	}

	public List<com.ipartek.formacion.persistence.Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<com.ipartek.formacion.persistence.Curso> cursos) {
		this.cursos = cursos;
	}
	

}
