package com.ipartek.formacion.persistence;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3576905169452924689L;
	
	@Id
	@GeneratedValue
	private long codigo;
	private String nombre;
	
	
	
	
	
}
