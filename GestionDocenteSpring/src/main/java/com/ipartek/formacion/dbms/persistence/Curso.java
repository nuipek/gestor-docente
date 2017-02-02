package com.ipartek.formacion.dbms.persistence;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Curso implements Comparable<Curso>, Serializable{

/**
	 * 
	 */
private static final long serialVersionUID = 1L;
public static final int CODIGO_NULO = -1;
public static final int NUM_MIN_CARACTERES = 8;
public static final String PATRON_FECHA="dd/MM/yyyy";

private int codigo;
private String nombre;
private int duracion;
private Date fInicio;
private Date fFin;
private List<Alumno> alumnos;
private Profesor profesor;

public Curso() {
	super();
	this.codigo = CODIGO_NULO;
	this.nombre="";
	this.duracion=0;
	this.fInicio = new Date();
	this.fFin  = new Date();
	this.alumnos = new ArrayList<Alumno>();
	this.profesor = new Profesor();
}

public int getCodigo() {
	return codigo;
}

public void setCodigo(int codigo) {
	this.codigo = codigo;
}

public String getNombre() {
	return nombre;
}

public void setNombre(String nombre) {
	if (nombre.length()< NUM_MIN_CARACTERES){
		//throw new CursoException(COD_ERROR_LONGITUD,MSG_ERROR_LONGITUD);
	}
	
	this.nombre = nombre;
}

public int getDuracion() {
	return duracion;
}

public void setDuracion(int duracion) {
	
	if (duracion > 0)
	this.duracion = duracion;
	
	
}

public Date getfInicio() {
	return fInicio;
}

public void setfInicio(Date fInicio)  {
	String fechaPattern = PATRON_FECHA;
    SimpleDateFormat dfmt = new SimpleDateFormat(fechaPattern);
    String fechaInicio = dfmt.format(fInicio);
    String fechaHoy    = dfmt.format(new Date());
   try {
	   Date fHoy = dfmt.parse(fechaHoy);
	   fInicio=dfmt.parse(fechaInicio);
   } catch (ParseException e) {
	   e.printStackTrace();
	 
	
   }
	this.fInicio = fInicio;
}

public Date getfFin() {
	return fFin;
}

public void setfFin(Date fFin) {
	if (fInicio == null) 
		fInicio = new Date();
	
	/*if (fFin.getTime() < fInicio.getTime())
		throw new CursoException(COD_ERROR_FECHA_FIN,MSG_ERROR_FECHA_FIN);
		*/
	this.fFin = fFin;
}

public List<Alumno> getAlumnos() {
	return alumnos;
}

public void setAlumnos(List<Alumno> alumnos) {
	this.alumnos = alumnos;
}

public Profesor getProfesor() {
	return profesor;
}

public void setProfesor(Profesor profesor) {
	this.profesor = profesor;
}

@Override
public String toString() {
	String fechaInicio="";
	String fechaFin="";
	fechaInicio = (new SimpleDateFormat(PATRON_FECHA)).format(this.getfInicio());
	fechaFin = (new SimpleDateFormat(PATRON_FECHA)).format(this.getfFin());
	return this.getCodigo() + " " +  this.getNombre() + " " + this.getDuracion()+ " " + fechaInicio + " " + fechaFin;
	
}

@Override
public int compareTo(Curso o) {
	//return this.codigo - o.getCodigo();
	return this.nombre.compareToIgnoreCase(o.getNombre());
	// return  o.getCodigo() - this.codigo; DE MAYOR A MENOR
}

@Override
public boolean equals(Object obj) {
boolean resultado = false;
if (obj != null && obj instanceof  Curso)
{
	if (this == obj)
		resultado = true;
	else
	{
	  if (this.codigo == ((Curso)obj).getCodigo())
		  resultado = true;
	}
	
}
	return resultado;
}











}
