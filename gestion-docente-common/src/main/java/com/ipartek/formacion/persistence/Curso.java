package com.ipartek.formacion.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "curso") // Marcamos el Nombre de la tabla
@Entity(name="curso") // Marcamos el nombre de la clase
@NamedQueries({
	@NamedQuery(name = "curso.getall", query = "SELECT c FROM curso c" )
})
	

public class Curso implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4289085520734386926L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo")
	private long codigo;

	@Column(name="nombre")
	private String nombre;
	
	private String identificador;
	private Date fInicio;
	private Date fFin;
	private int nHoras;
	private String temario;
	private boolean activo;
	private double precio;
	
	 // ManyToOne de cliente
	/*
	@ManyToOne(fetch = FetchType.LAZY) // carga Lazy es por defecto
	Cliente cliente;
	*/
	
	
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
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
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public Date getfInicio() {
		return fInicio;
	}
	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}
	public Date getfFin() {
		return fFin;
	}
	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}
	public int getnHoras() {
		return nHoras;
	}
	public void setnHoras(int nHoras) {
		this.nHoras = nHoras;
	}
	public String getTemario() {
		return temario;
	}
	public void setTemario(String temario) {
		this.temario = temario;
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
		Curso other = (Curso) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Curso [codigo=" + codigo + ", nombre=" + nombre + ", identificador=" + identificador + ", fInicio="
				+ fInicio + ", fFin=" + fFin + ", nHoras=" + nHoras + ", temario=" + temario + ", activo=" + activo
				+ "]";
	}
	
	
	
	
}
