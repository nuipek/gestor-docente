package com.ipartek.formacion.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imparticion")
public class Imparticion implements Serializable, Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474065698791044773L;
	
	@Id
	@GeneratedValue
	private long codigo;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="curso_detalle_codigo",referencedColumnName="codigo")
	private CursoDetalle cursoDetalle;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="profesor_codigo",referencedColumnName="codigo")
	private Profesor profesor;
	
	//Enlazamos con Asistente y Alumno
	@ManyToMany(fetch=FetchType.EAGER, mappedBy="imparticiones")
	private Set<Alumno> alumnos;
	
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public CursoDetalle getCursoDetalle() {
		return cursoDetalle;
	}

	public void setCursoDetalle(CursoDetalle cursoDetalle) {
		this.cursoDetalle = cursoDetalle;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	
	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	@Override
	public int compareTo(Object o) {
		return (int) (this.codigo - ((Imparticion) o).getCodigo());
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
		Imparticion other = (Imparticion) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Imparticion [codigo=" + codigo + ", cursoDetalle=" + cursoDetalle + ", profesor=" + profesor + "]";
	}
	
	

}
