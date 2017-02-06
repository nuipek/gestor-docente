package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ipartek.formacion.service.Util;

public class Profesor  implements Comparable<Profesor>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -986241053422788368L;
	public static final int CODIGO_NULO = -1;
	public static final long YEAR_MILISEGUNDOS = 31556900000L;
	public static final long DAY_MILISEGUNDOS = 86400001L;
	
	
	private int codigo;
	private int nSS;
	private String dni;
	private String nombre;
	private String apellidos;
	private Date fNacimiento;
	private String email;
	private String direccion;
	private String poblacion;
	private int codigoPostal;
	private int telefono; 
	private String sessionId;

	public Profesor() {
		super();
		this.codigo = CODIGO_NULO;
		this.nSS = 0;
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
		
		this.poblacion="";
		this.codigoPostal=0;
		this.telefono=0; 
		
		this.codigo = CODIGO_NULO;

	}

	public int getnSS() {
		return nSS;
	}

	public void setnSS(int nSS) {
		this.nSS = nSS;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	@Override
	public String toString() {
		return this.getCodigo() + " " + this.getApellidos() + ", " + this.getNombre() + " " + this.getDni()+ " " + this.getEmail();
	}

	@Override
	public int compareTo(Profesor o) {
		
		return this.getApellidos().compareTo(o.getApellidos());
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
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(new Date());
		int anyo18ago = gc.get(GregorianCalendar.YEAR) - 18;// 1999
		gc.set(GregorianCalendar.YEAR, anyo18ago);

		if (gc.getTime().before(fNacimiento)) {
			//throw new PersonaException(PersonaException.COD_EDAD_ERROR, PersonaException.MSG_EDAD_ERROR);
		}

		this.fNacimiento = fNacimiento;
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

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
