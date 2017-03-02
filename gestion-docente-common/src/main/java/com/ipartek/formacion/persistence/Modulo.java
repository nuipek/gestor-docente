package com.ipartek.formacion.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/*
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
*/

@Entity
@Table(name = "modulo")
public class Modulo implements Serializable,Comparable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -2580771437456301182L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="codigo")
	private long codigo;
		
	@Column(name ="nombre") // se pone cuando la columna de la clase no coincide con el de la tabla
	private String nombre;
	private int nHoras;
	private String descripcion;
	private double precio;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "modulo")
	private Set<CursoDetalle> detalle;
	
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
	public int getnHoras() {
		return nHoras;
	}
	public void setnHoras(int nHoras) {
		this.nHoras = nHoras;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	

	public Set<CursoDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(Set<CursoDetalle> detalle) {
		this.detalle = detalle;
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
		Modulo other = (Modulo) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Modulo [codigo=" + codigo + ", nombre=" + nombre + ", nHoras=" + nHoras + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}
	@Override
	public int compareTo(Object o) {
		return (int) (this.codigo - ((Modulo) o).getCodigo());
	}
	 
}
