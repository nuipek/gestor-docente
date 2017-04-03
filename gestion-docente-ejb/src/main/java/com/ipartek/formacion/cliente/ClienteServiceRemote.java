package com.ipartek.formacion.cliente;

import java.util.List;

import javax.ejb.Remote;

import com.ipartek.formacion.persistence.Cliente;

@Remote
public interface ClienteServiceRemote {

	public Cliente getById(long codigo);
	
	public List<Cliente> getAll();
	
}
