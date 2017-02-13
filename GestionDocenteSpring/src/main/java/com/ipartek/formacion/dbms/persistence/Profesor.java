package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.ipartek.formacion.dbms.persistence.validator.Phone;
import com.ipartek.formacion.service.Util;

public class Profesor  implements Comparable<Profesor>, Serializable {
	/**
	 * 
	 */
	
	//@NotNull
	//@NotBlank
	
	private static final long serialVersionUID = -986241053422788368L;
	public static final int CODIGO_NULO = -1;
	public static final long YEAR_MILISEGUNDOS = 31556900000L;
	public static final long DAY_MILISEGUNDOS = 86400001L;
		
	private Integer codigo;
	private int nSS;
	
	@Pattern (regexp = "[0-9]{8}[a-z-A-Z]", message = "Pattern.dni")
	private String dni;
	@Size(min=3, max=50, message="Size.nombre")
	private String nombre;
	@Size(min=3, max=150, message="Size.apellidos")
	private String apellidos;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Past(message="Past.fNacimiento")
	private Date fNacimiento;
	
	@NotNull(message = "NotEmpty.email")
	@NotBlank(message = "NotBlank.email")
	@Email(message = "Email.email")
	private String email;
	private String direccion;
	private String poblacion;
	private int codigoPostal; 
	@Phone(message="Phone.telefono")
	private String telefono; 
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
		this.telefono=""; 
		
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
		/*final String regex = "\\d{8}[A-Za-z]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(dni);
		if (!matcher.find() && Util.validarDni(dni)) {
			//throw new PersonaException(PersonaException.COD_DNI_ERROR, PersonaException.MSG_DNI_ERROR);
		}*/
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {

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

		this.fNacimiento = fNacimiento;;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
