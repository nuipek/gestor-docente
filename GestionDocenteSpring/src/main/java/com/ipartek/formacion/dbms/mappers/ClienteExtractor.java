package com.ipartek.formacion.dbms.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.persistence.Curso;

public class ClienteExtractor implements ResultSetExtractor<Map<Long,Cliente>>{

	@Override
	public Map<Long,Cliente> extractData(ResultSet rs) throws SQLException, DataAccessException {
		Map<Long,Cliente>clientes = new HashMap<Long,Cliente>();
		
		while(rs.next())
		{
			// recogemos el codigo del cliente del resultset
			long codigo = rs.getLong("codigo");
			//Recogemos el cliente del mapa para comprobar si ya existe
			Cliente cliente = clientes.get(codigo);
			
			
			if(cliente == null){
				cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo"));
				cliente.setNombre(rs.getString("nombre"));
				cliente.setApellidos(rs.getString("apellidos"));
				cliente.setDireccion(rs.getString("direccion"));
				cliente.setTelefono(String.valueOf(rs.getInt("telefono")));
				cliente.setEmail(rs.getString("email"));
				cliente.setIdentificativo(rs.getString("identificativo"));
				cliente.setActivo(rs.getBoolean("activo"));
				
				// Si no existe el cliente creamos el primer mapa
				cliente.setCursos(new HashMap<Long,Curso>());
			}
			
			// Aqui es donde cargamos el mapa de cursos
			Map<Long,Curso> cursos = cliente.getCursos();
			
			Curso curso = new Curso();
			curso.setCodigo(rs.getLong("codigocurso"));
			curso.setNombre(rs.getString("nombrecurso"));
			curso.setfInicio(rs.getDate("finicio"));
			curso.setfInicio(rs.getDate("ffin"));
			curso.setnHoras(rs.getInt("nhoras"));
			curso.setIdentificador(rs.getString("identificadorcurso"));
			curso.setPrecio(rs.getDouble("precio"));
	
			cursos.put(curso.getCodigo(),curso);
			
			cliente.setCursos(cursos);
			clientes.put(codigo, cliente);
		}
		
		
		return clientes;
	}

}
