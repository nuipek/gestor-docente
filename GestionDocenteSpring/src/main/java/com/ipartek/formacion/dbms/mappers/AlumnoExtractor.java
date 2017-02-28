package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.persistence.Curso;

public class AlumnoExtractor implements ResultSetExtractor<List<Alumno>>{

	@Override
	public List<Alumno> extractData(ResultSet rs) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		List<Alumno> alumnos = new ArrayList<Alumno>();
		List<Curso> cursos;
		
		while (rs.next())
		{
			Alumno alumno = new Alumno();
			Curso curso = new Curso();
			
			alumno.setCodigo(rs.getInt("codigo"));
			alumno.setNombre(rs.getString("nombre"));
			alumno.setApellidos(rs.getString("apellidos"));
			alumno.setEmail(rs.getString("email"));
			
			curso.setCodigo(rs.getLong("codigocurso"));
			curso.setNombre(rs.getString("nombrecurso"));
			curso.setIdentificador(rs.getString("identificador"));
			curso.setfInicio(rs.getDate("finicio"));
			curso.setfFin(rs.getDate("ffin"));
			curso.setnHoras(rs.getInt("nhoras"));
			curso.setPrecio(rs.getDouble("precio"));
					
			// Si es un nuevo alumno
			if (!alumnos.contains(alumno))
			{
			  cursos = alumno.getCursos();
			  cursos.add(curso);
			  alumno.setCursos(cursos);
			  alumnos.add(alumno);
			}
			else
			{ 
				// si el alumno ya existe comprobamos si existe este curso
				alumno = alumnos.get(alumnos.indexOf(alumno));
				cursos = alumno.getCursos();
				
				// Si el curso no existe lo añadimos a la lista
				if (!cursos.contains(curso))
				{
					cursos.add(curso);
				}
				
			}
			
			
		}
		
		return alumnos;
	}

}
