package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.dbms.dao.interfaces.ClienteDAO;
import com.ipartek.formacion.dbms.mappers.ClienteMapper;
import com.ipartek.formacion.dbms.mappers.ProfesorMapper;
import com.ipartek.formacion.dbms.persistence.Cliente;
import com.ipartek.formacion.dbms.persistence.Profesor;

@Repository("clienteDaoImp")
public class ClienteDAOImp implements ClienteDAO {
	@Inject
	private DataSource dataSource;
	private JdbcTemplate template;
	private Logger logger = LoggerFactory.getLogger(ClienteDAOImp.class);
	
	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(this.dataSource);
	
	}

	@Override
	public Cliente create(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> getAll() {
		
		final String SQL="SELECT codigo as codigo, nombre as nombre, apellidos as apellidos, direccion as direccion, " +
		"email as email, telefono as telefono, identificativo as identificativo FROM cliente";
		List<Cliente> clientes = null;
		
		try {
			clientes = template.query(SQL, new ClienteMapper());
		} catch (EmptyResultDataAccessException e) {
			clientes = new ArrayList<Cliente>();
			logger.info("La lista esta vacia");
			
		}catch(Exception e){
			clientes = new ArrayList<Cliente>();
			e.printStackTrace();
			logger.error("Error al recuperar el SQL de cliente " + e.getMessage());
		}// fin del catch
		
		return clientes;
	}

	@Override
	public Cliente getById(int codigo) {
		Cliente cliente ;
		
		final String SQL = "SELECT codigo as codigo, nombre as nombre, apellidos as apellidos, direccion as direccion, " +
				"email as email, telefono as telefono, identificativo as identificativo FROM cliente " +  
				"WHERE codigo = " + codigo;
		
		
		cliente = template.queryForObject(SQL, new ClienteMapper());
		
		return cliente;
	}

	@Override
	public Cliente update(Cliente cliente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int codigo) {
		// TODO Auto-generated method stub

	}

}
