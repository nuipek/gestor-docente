package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Alumno;

public class AlumnoMapper implements RowMapper<Alumno> {

	@Override
	public Alumno mapRow(ResultSet rs, int rowNum) throws SQLException {
		Alumno alumno = new Alumno();
		alumno.setCodigo(rs.getInt("codigo"));
		alumno.setApellidos(rs.getString("apellidos"));
		alumno.setNombre(rs.getString("nombre"));
		alumno.setActivo(rs.getBoolean("activo"));
		alumno.setnHermanos(rs.getInt("nhermanos"));
		alumno.setDni(rs.getString("dni"));
		alumno.setfNacimiento(rs.getDate("fNacimiento"));
		alumno.setEmail(rs.getString("email"));
		alumno.setDireccion(rs.getString("direccion"));
		alumno.setPoblacion(rs.getString("poblacion"));
		alumno.setCodigoPostal(rs.getInt("codigoPostal"));
		alumno.setTelefono(String.valueOf(rs.getInt("telefono")));
		
		
		return alumno;
	}

}
