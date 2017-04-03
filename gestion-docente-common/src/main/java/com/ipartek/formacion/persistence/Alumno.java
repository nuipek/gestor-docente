package com.ipartek.formacion.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity(name="alumno")
@Table(name="alumno")
@NamedQueries({
	@NamedQuery(name = "alumnos.getall", query = "SELECT a FROM alumno a" )
})
public class Alumno implements Serializable, Comparable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2365071600338529508L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private long codigo;
	private String nombre;
	private String  apellidos;
	private Date fNacimiento;
	private String dni;
	private String poblacion;
	private String direccion;
	private Integer codigoPostal;
	private String email;
	private String telefono;
	private int nHermanos;
	private boolean activo;
	
	@Transient
	private List<Curso> cursos; 
	
	/*
	//@Fetch(FetchMode.JOIN)// Si fuese imprescindible una list se tendria que incluir esta anotacion con Join o Subselect
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "asistente", 
			   joinColumns=@JoinColumn(name="alumno_codigo",referencedColumnName="codigo"),
			   inverseJoinColumns=@JoinColumn(name="imparticion_codigo",referencedColumnName="codigo")
			  )
	private Set<Imparticion> imparticiones;
	
	*/
	
	public Alumno(){
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
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public int getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(int codigoPostal) {
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
	public int getnHermanos() {
		return nHermanos;
	}
	public void setnHermanos(int nHermanos) {
		this.nHermanos = nHermanos;
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
		Alumno other = (Alumno) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Alumno [codigo=" + codigo + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fNacimiento="
				+ fNacimiento + ", dni=" + dni + ", poblacion=" + poblacion + ", direccion=" + direccion
				+ ", codigoPostal=" + codigoPostal + ", email=" + email + ", telefono=" + telefono + ", nHermanos="
				+ nHermanos + ", activo=" + activo + "]";
	}

	@Override
	public int compareTo(Object o) {
		
		return (int) (this.codigo - ((Alumno) o).getCodigo());
	}
	
	

	
	
}
