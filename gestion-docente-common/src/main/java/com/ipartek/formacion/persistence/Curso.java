package com.ipartek.formacion.persistence;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
		name = "curso.getAlumnos", 
		resultClasses = Alumno.class,
		procedureName = "alumnogetByCurso", 
		parameters = { 
						@StoredProcedureParameter(mode = ParameterMode.IN, type = Long.class) 
					 }
	)
})
 
@Table(name = "curso") // Marcamos el Nombre de la tabla
@Entity(name="curso") // Marcamos el nombre de la clase
@NamedQueries({
	@NamedQuery(name = "curso.getall", query = "SELECT c FROM curso c" )
})

public class Curso implements Serializable, Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4289085520734386926L;
	public static final int CODIGO_NULO = -1;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name = "cliente_codigo")
	private Cliente cliente;
	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.MERGE)
	@JoinColumn(name = "profesor_codigo")
	private Profesor profesor;
	
	//@Transient
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinTable(name="imparticion", joinColumns = {@JoinColumn(name="curso_codigo")},
	inverseJoinColumns = {@JoinColumn(name = "alumno_codigo")})
	private List<Alumno>alumnos;
	
	/*
	@OneToMany(fetch=FetchType.LAZY, mappedBy="curso")
	@Fetch(FetchMode.JOIN)
	private Set<CursoDetalle> modulos;
	*/
	
	
	public List<Alumno> getAlumnos() {
		return alumnos;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}
	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
	/*
	public Set<CursoDetalle> getModulos() {
		return modulos;
	}

	public void setModulos(Set<CursoDetalle> modulos) {
		this.modulos = modulos;
	}
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
	
	@Override
	public int compareTo(Object o) {
		return (int) (this.codigo - ((Curso) o).getCodigo());
	}
	
	
	
	
}
