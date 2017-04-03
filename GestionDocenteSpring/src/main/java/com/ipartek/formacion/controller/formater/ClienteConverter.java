package com.ipartek.formacion.controller.formater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.ipartek.formacion.cliente.ClienteServiceRemote;
import com.ipartek.formacion.persistence.Cliente;

public class ClienteConverter implements Converter<String, Cliente> {

	@Autowired
	ClienteServiceRemote clienteEJB;
	
	@Override
	public Cliente convert(String codigo) {
		
		return clienteEJB.getById(Long.parseLong(codigo)) ;
	}

}
