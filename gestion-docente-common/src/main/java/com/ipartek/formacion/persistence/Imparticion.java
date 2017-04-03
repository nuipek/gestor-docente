package com.ipartek.formacion.persistence;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
@Entity
@Table(name = "imparticion")
*/
public class Imparticion implements Serializable, Comparable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2474065698791044773L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long codigo;

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
}
