package com.ipartek.formacion.service.interfaces;

import java.util.List;

import com.ipartek.formacion.persistence.Modulo;

public interface ModuloService {
	
	public Modulo create(Modulo modulo);
	
	public List<Modulo> getAll();
	
	public Modulo getById(int codigo);
	
	public Modulo update(Modulo modulo);
	
	public void delete(int codigo);
	
	

}
