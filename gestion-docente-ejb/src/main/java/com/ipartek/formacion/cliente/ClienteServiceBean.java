package com.ipartek.formacion.cliente;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;



import com.ipartek.formacion.persistence.Cliente;


@Stateless(name="clienteServiceBean")
public class ClienteServiceBean implements ClienteServiceRemote {

	
	
	@PersistenceContext(unitName = "gestiondocente")
	private EntityManager entityManager;
	
	@Override
	public Cliente getById(long codigo) {
		Cliente cliente = entityManager.find(Cliente.class, codigo);
		return cliente;
		
	}

	@Override
	public List<Cliente> getAll() {
		TypedQuery<Cliente> clientes = entityManager.createNamedQuery("clientes.getall", Cliente.class);
		List<Cliente> clienteLista = (List<Cliente>)clientes.getResultList();
		System.out.println("Tamano Lista" + String.valueOf(clienteLista.size()));
		return clienteLista;
		
	}

}
