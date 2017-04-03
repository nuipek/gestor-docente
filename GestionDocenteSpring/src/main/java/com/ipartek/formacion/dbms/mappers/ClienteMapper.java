package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ipartek.formacion.dbms.persistence.Cliente;

public class ClienteMapper implements RowMapper<Cliente> {

	@Override
	public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setCodigo(rs.getInt("codigo"));
		cliente.setNombre(rs.getString("nombre"));
		//cliente.setApellidos(rs.getString("apellidos"));
		cliente.setDireccion(rs.getString("direccion"));
		cliente.setTelefono(String.valueOf(rs.getInt("telefono")));
		cliente.setEmail(rs.getString("email"));
		//cliente.setIdentificativo(rs.getString("identificativo"));
		cliente.setActivo(rs.getBoolean("activo"));
		
		return cliente;
	}

}
