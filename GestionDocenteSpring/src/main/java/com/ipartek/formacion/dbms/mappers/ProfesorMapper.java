package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Profesor;

public class ProfesorMapper implements RowMapper<Profesor> {

	@Override
	public Profesor mapRow(ResultSet rs, int rowNum) throws SQLException {
		Profesor profesor = new Profesor();
		profesor.setCodigo(rs.getInt("codigo"));
		profesor.setNombre(rs.getString("nombre"));
		profesor.setApellidos(rs.getString("apellidos"));
		profesor.setDni(rs.getString("dni"));
		//profesor.setnSS(rs.getBigDecimal("nSS"));
		profesor.setEmail(rs.getString("email"));
		profesor.setTelefono(String.valueOf(rs.getInt("telefono")));
		profesor.setDireccion(rs.getString("direccion"));
		profesor.setCodigoPostal(rs.getInt("codigoPostal"));
		profesor.setPoblacion(rs.getString("poblacion"));
	return profesor;
	
    }
	
}
