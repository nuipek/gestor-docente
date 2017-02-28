package com.ipartek.formacion.dbms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

import com.ipartek.formacion.dbms.dao.interfaces.AlumnoDAO;
import com.ipartek.formacion.dbms.mappers.AlumnoExtractor;
import com.ipartek.formacion.dbms.mappers.AlumnoMapper;
import com.ipartek.formacion.dbms.persistence.Alumno;
import com.ipartek.formacion.dbms.persistence.Profesor;

@Repository("alumnoDaoImp")
public class AlumnoDAOImp implements AlumnoDAO {
	
    @SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate template;
	private SimpleJdbcCall jdbcCall;
	private Logger logger = LoggerFactory.getLogger(AlumnoDAOImp.class);
	//private Logger logger = (Logger) LoggerFactory.getLogger(AlumnoDAOImp.class);
	
	// Se utiliza para cargar las properties del root-context a las variables que aplican
	// siempre se tiene que poner como mienbros de clase
	@Value("${alumno.create}")
	private String sqlCreate;
	@Value("${alumno.getAll}")
	private String sqlgetAll;
	@Value("${alumno.getById}")
	private String sqlgetById;
	@Value("${alumno.update}")
	private String sqlUpdate;
	@Value("${alumno.delete}")
	private String sqlDelete;
	@Value("${alumno.getByDni}")
	private String sqlgetByDni;
	
	@Value("${alumno.getInforme}")
	private String sqlgetInforme;

	@Autowired // igual a @inject
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.template = new JdbcTemplate(this.dataSource);
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);

	}

	@Override
	public Alumno create(Alumno alumno) {
		
		final String SQL = sqlCreate;
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		// Se asigna el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		
		// Crear un mapa con los parametros de procedimiento almacenado  de entrada IN
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", alumno.getNombre()).addValue("pcodigoPostal",alumno.getCodigoPostal())
				.addValue("pdireccion", alumno.getDireccion()).addValue("pdni",alumno.getDni())
				.addValue("pemail", alumno.getEmail()).addValue("pfNacimiento", alumno.getfNacimiento())
				.addValue("pnHermanos", alumno.getnHermanos()).addValue("pnombre", alumno.getNombre())
				.addValue("ppoblacion", alumno.getPoblacion())
				.addValue("papellidos", alumno.getApellidos())
				.addValue("ptelefono", alumno.getTelefono());
		
		logger.info(alumno.toString());
		
		// Ejecuta la sentencia
		Map<String,Object> out = jdbcCall.execute(in);
	    // En out se han recogido los parametros out de la consulta de BBDD.
	   alumno.setCodigo((Integer)out.get("pcodigo"));
		
		return alumno;
	}

	@Override
	public List<Alumno> getAll() {
		final String SQL = sqlgetAll;
		List<Alumno> alumnos = null;
		try{
			
		  alumnos =  template.query(SQL, new AlumnoMapper());
		  
		}catch(EmptyResultDataAccessException e){
			logger.trace(e.getMessage());
			alumnos = new ArrayList<Alumno>();
			
		}
		catch (Exception e){
			logger.error("Error en la recuperacion de la lista de alumnos " + e.getMessage());
			alumnos = new ArrayList<Alumno>();
			
		}
		return alumnos;
	}

	@Override
	public Alumno getById(int codigo) {
		// Llamada al procedimiento con parametro de entrada
		final String SQL = sqlgetById;
		
		Alumno alumno = null;
		try {														 // array con los parametros de entrada
			alumno = template.queryForObject(SQL, new AlumnoMapper(),new Object[]{ codigo });
			logger.info(alumno.toString());
			
		} catch (EmptyResultDataAccessException e) {
			logger.info("No se ha encontrado Alumno para codigo: " + codigo + "" + e.getMessage());
			alumno = new Alumno();
		}
		catch (Exception e){
			logger.error("Error al realizar el getById del Alumno " + e.getMessage());
			alumno = new Alumno();
		}
		return alumno;
	}

	@Override
	public Alumno update(Alumno alumno) {
		final String SQL = sqlUpdate;
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		// Se asigna el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		
		// Crear un mapa con los parametros de procedimiento almacenado  de entrada IN
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pnombre", alumno.getNombre()).addValue("pcodigoPostal",alumno.getCodigoPostal())
				.addValue("papellidos", alumno.getApellidos())
				.addValue("pdireccion", alumno.getDireccion()).addValue("pdni",alumno.getDni())
				.addValue("pemail", alumno.getEmail()).addValue("pfNacimiento", alumno.getfNacimiento())
				.addValue("pnHermanos", alumno.getnHermanos()).addValue("pnombre", alumno.getNombre())
				.addValue("ppoblacion", alumno.getPoblacion())
				.addValue("ptelefono", alumno.getTelefono())
				.addValue("pcodigo", alumno.getCodigo());
		
		logger.info(alumno.toString());
		
		// Ejecuta la sentencia de update
		try {
			jdbcCall.execute(in);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error en la update de alumno " + e.getMessage());
			
		}
	
			
		return alumno;
	}

	@Override
	public void delete(int codigo) {
		String SQL = sqlDelete;
		
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		
		SqlParameterSource in = new MapSqlParameterSource().addValue("pcodigo", codigo);
		jdbcCall.withProcedureName(SQL);
		
		try {
			jdbcCall.execute(in);
			logger.info(jdbcCall.getProcedureName());
		} catch (Exception e) {
			logger.error("Error en el delete de alumno " + e.getMessage());
			e.printStackTrace();
		}
		
		logger.info(String.valueOf(codigo));

	}

	@Override
	public boolean alumnoDniDuplicado(String dni,int codigo) {
		
		final String SQL = sqlgetByDni;
		this.jdbcCall = new SimpleJdbcCall(this.dataSource);
		// Se asigna el nombre del procedimiento almacenado
		jdbcCall.withProcedureName(SQL);
		
		// Crear un mapa con los parametros de procedimiento almacenado  de entrada IN
		SqlParameterSource in = new MapSqlParameterSource()
				.addValue("pdni", dni)
				.addValue("pcodigo", codigo);
		
		logger.info(dni);
		
		// Ejecuta la sentencia
		Map<String,Object>out = jdbcCall.execute(in);
	    // En out se han recogido los parametros out de la consulta de BBDD.
	    int total = (Integer)out.get("presultado");
		logger.info("El valor de la recuperacion Dni Duplicado es "+ total);
		if (total > 0) return true;
		else return false;

	}

	@Override
	public Alumno getInforme(int codigo) {
		final String SQL = sqlgetInforme;
		Alumno alumno = null;
		
		try{
			
			List<Alumno> alumnos = template.query(SQL, new AlumnoExtractor(), new Object[]{codigo});
			alumno = alumnos.get(0);
			
		}catch (EmptyResultDataAccessException e){
			logger.info("No hay datos para el alumno seleccionado " + e.getMessage());
		} catch (Exception e){
			logger.error("Error al ejecutar el procedimiento alumnoInforme con codigo  "  + codigo + " " + e.getMessage() );
		}
		
		return alumno;
	
	}

}
