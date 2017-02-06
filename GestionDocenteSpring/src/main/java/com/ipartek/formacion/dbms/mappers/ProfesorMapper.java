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
		/*profesor.setnSS(rs.getInt("nSS"));
		profesor.setDireccion(rs.getString("direccion"));
		profesor.setEmail(rs.getString("email"));
		profesor.setPoblacion(rs.getString("poblacion"));
		profesor.setDni(rs.getString("dni"));
		profesor.setCodigoPostal(rs.getInt("codigoPostal"));
		profesor.setTelefono(rs.getInt("telefono"));*/
		return profesor;
	
    }
	
}
