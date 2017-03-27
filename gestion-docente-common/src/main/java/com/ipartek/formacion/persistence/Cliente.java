package com.ipartek.formacion.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable, Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3576905169452924689L;
	
	@Id
	@GeneratedValue
	private long codigo;
	private String nombre;
	@Transient
	private String apellidos; 
	private String direccion; 
	private String poblacion; 
	private Integer codigoPostal;
	private String email;
	private String telefono;
	private String identificador;
	@Transient
	private String identificativo;
	private boolean activo;
	
	@Transient
	private List<Curso> cursos;
	
	
	
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




	public Integer getCodigoPostal() {
		return codigoPostal;
	}




	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}




	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getTelefono() {
		return telefono;
	}




	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}




	public String getIdentificativo() {
		return identificativo;
	}




	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}




	public boolean isActivo() {
		return activo;
	}




	public void setActivo(boolean activo) {
		this.activo = activo;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (activo ? 1231 : 1237);
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
		Cliente other = (Cliente) obj;
		if (activo != other.activo)
			return false;
		return true;
	}




	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion="
				+ direccion + ", poblacion=" + poblacion + ", codigoPostal=" + codigoPostal + ", email=" + email
				+ ", telefono=" + telefono + ", identificativo=" + identificativo + ", activo=" + activo + "]";
	}




	@Override
	public int compareTo(Object o) {
		return (int) (this.codigo - ((Cliente) o).getCodigo());
	}




	public String getIdentificador() {
		return identificador;
	}




	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	
	
	
	
}
