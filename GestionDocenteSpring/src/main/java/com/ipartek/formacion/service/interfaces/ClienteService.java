package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.dbms.dao.interfaces.ClienteDAO;
import com.ipartek.formacion.dbms.persistence.Cliente;

public interface ClienteService {

	public Cliente create(Cliente cliente);
	
	public List<Cliente> getAll();
	
	public Cliente getById(int codigo);
	
	public Cliente update(Cliente cliente);
	
	public void delete(int codigo);
	
	public void setClienteDao(ClienteDAO clienteDao);
}
