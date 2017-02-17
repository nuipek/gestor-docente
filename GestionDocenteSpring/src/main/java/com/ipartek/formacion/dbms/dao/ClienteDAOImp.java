package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
	private SimpleJdbcCall jdbcCall;
	private Logger logger = LoggerFactory.getLogger(ClienteDAOImp.class);
	
	@Value("${cliente.getByIdentificativo}")
	private String sqlgetByIdentificativo;
	
	
	@Override
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(this.dataSource);
		jdbcCall = new SimpleJdbcCall(dataSource);
	
	}

	@Override
	public Cliente create(Cliente cliente) {
		final String SQL = "clienteCreate";
		jdbcCall = new SimpleJdbcCall(dataSource);
		
		
		
		jdbcCall.withProcedureName(SQL);
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("pnombre", cliente.getNombre())
					.addValue("papellidos", cliente.getApellidos())
					.addValue("pdireccion", cliente.getDireccion())
					.addValue("pemail", cliente.getEmail())
					.addValue("ptelefono", cliente.getTelefono())
					.addValue("pidentificativo", cliente.getIdentificativo());
		
		logger.info(cliente.toString());
		
		try {
			Map<String,Object> out = jdbcCall.execute(in);
			
			cliente.setCodigo((Integer)out.get("pcodigo"));
			
		} catch (Exception e) {
			logger.equals("Error al crear el cliente " + e.getMessage());
			e.printStackTrace();
		}
					
		return cliente;
	}
	@Override
	public List<Cliente> getAll() {
		
		final String SQL="CALL clientegetAll();";
		jdbcCall = new SimpleJdbcCall(dataSource);
		
		List<Cliente> clientes = null;
		
		try {
			
			clientes = template.query(SQL, new ClienteMapper());
		} catch (EmptyResultDataAccessException e) {
			clientes = new ArrayList<Cliente>();
			logger.info("La lista esta vacia para los clientes");
			
		}catch(Exception e){
			clientes = new ArrayList<Cliente>();
			e.printStackTrace();
			logger.error("Error al recuperar la lista de clientes " + e.getMessage());
		}// fin del catch
		
		return clientes;
	}

	@Override
	public Cliente getById(int codigo) {
		Cliente cliente = null ;
		final String SQL = "CALL clientegetById(?);";
		
		
		try {
			cliente = template.queryForObject(SQL, new ClienteMapper(),new Object[]{codigo});
		} catch (EmptyResultDataAccessException e) {
			cliente = new Cliente();
			e.printStackTrace();
			logger.info("El cliente no existe con codigo: " + codigo);
		}catch (Exception e ){
			cliente = new Cliente();
			logger.error("Error al ejecutar el getbyid de cliente " + e.getMessage() );
			
		}
		logger.info(cliente.toString());
		return cliente;
	}

	@Override
	public Cliente update(Cliente cliente) {
		final String SQL = "clienteUpdate";
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		// Se asigna el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		
		// Crear un mapa con los parametros de procedimiento almacenado  de entrada IN
		SqlParameterSource in = new MapSqlParameterSource().addValue("pnombre", cliente.getNombre())
				.addValue("papellidos", cliente.getApellidos())
				.addValue("pdireccion", cliente.getDireccion())
				.addValue("pemail", cliente.getEmail())
				.addValue("ptelefono", cliente.getTelefono())
				.addValue("pidentificativo", cliente.getIdentificativo())
				.addValue("pcodigo", cliente.getCodigo());
				
		
		logger.info(cliente.toString());
		
		// Ejecuta la sentencia de update
		try {
			jdbcCall.execute(in);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la update de cliente " + e.getMessage());
			
		}
		
		return cliente;
	}

	@Override
	public void delete(int codigo) {
		String SQL = "clienteDelete";
		
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("pcodigo", codigo);
		jdbcCall.withProcedureName(SQL);
		
		try {
			jdbcCall.execute(in);
			logger.info(jdbcCall.getProcedureName());
		} catch (Exception e) {
			logger.error("Error en el delete de cliente " + e.getMessage());
			e.printStackTrace();
		}
		
		logger.info(String.valueOf(codigo));

	}

	@Override
	public boolean clienteIdentificativoDuplicado(String identificativo, int codigo) {
		final String SQL = sqlgetByIdentificativo;
		
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		// Se asigna el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		
		// Crear un mapa con los parametros de procedimiento almacenado  de entrada IN
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pidentificativo", identificativo)
				.addValue("pcodigo", codigo);
		
		logger.info(identificativo);
		
		// Ejecuta la sentencia
		Map<String,Object>out = jdbcCall.execute(in);
	    // En out se han recogido los parametros out de la consulta de BBDD.
	    int total = (Integer)out.get("presultado");
		logger.info("El valor de la recuperacion identificativo Duplicado es "+ total);
		if (total > 0) return true;
		else return false;

		
	}

}
