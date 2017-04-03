package com.ipartek.formacion.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.cliente.ClienteServiceRemote;
import com.ipartek.formacion.persistence.Cliente;
import com.ipartek.formacion.service.interfaces.ClienteServiceEJB;

@Service("clienteServiceEJBImp")
public class ClienteServiceEJBImp implements ClienteServiceEJB {

	
	@Resource(name = "clienteServiceRemote")
	private ClienteServiceRemote clienteServiceRemote;

	
	public ClienteServiceRemote getClienteServiceRemote() {
		return clienteServiceRemote;
	}

	@Override
	public void setClienteServiceRemote(ClienteServiceRemote clienteServiceRemote) {
		this.clienteServiceRemote = clienteServiceRemote;
	}

	@Override
	public List<Cliente> getAll() {

		return clienteServiceRemote.getAll();
	}

	@Override
	public Cliente getById(long codigo) {

		return clienteServiceRemote.getById(codigo);
	}
}
