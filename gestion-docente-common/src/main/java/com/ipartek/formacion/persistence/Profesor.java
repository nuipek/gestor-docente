package com.ipartek.formacion.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "profesor")
public class Profesor implements Serializable,Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2544697235388686644L;
	
	@Id
	@GeneratedValue
	private long codigo;
	
	private String  nSS; 
	private String nombre; 
	private String apellidos; 
	private Date fNacimiento; 
	private String dni; 
	private String direccion; 
	private String poblacion;
	private Integer codigoPostal;
	private String telefono; 
	private String email; 
	private boolean activo;
	
	@Transient
	private List<Curso>cursos;
	
	/*
	// el mappedby es el nombre de la joincolumn de la relacion en imparticiones
	@OneToMany(fetch=FetchType.LAZY,mappedBy="profesor") 
	
	//@JoinColumn(name="profesor_codigo", referencedColumnName="codigo") equivalente al mappedBy
	private Set<Imparticion> imparticiones;
	*/
	public Profesor(){
		super();
	}
	
	
	
	public List<Curso> getCursos() {
		return cursos;
	}



	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}



	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getnSS() {
		return nSS;
	}
	public void setnSS(String nSS) {
		this.nSS = nSS;
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
	public void setfNacimiento(Date fNacimiento) {
		this.fNacimiento = fNacimiento;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public int compareTo(Object o) {
		return (int) (this.codigo - ((Profesor) o).getCodigo());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigo ^ (codigo >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profesor other = (Profesor) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profesor [codigo=" + codigo + ", nSS=" + nSS + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", fNacimiento=" + fNacimiento + ", dni=" + dni + ", direccion=" + direccion + ", poblacion="
				+ poblacion + ", codigoPostal=" + codigoPostal + ", telefono=" + telefono + ", email=" + email
				+ ", activo=" + activo + "]";
	}
	
	
	
	

}
