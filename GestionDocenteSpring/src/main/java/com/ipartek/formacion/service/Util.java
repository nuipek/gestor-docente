package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ipartek.formacion.controller.validator.AlumnoValidator;



public class Util {
	public static final String FECHA_PATRON = "dd/MM/yyyy";
	
	 private static final Logger logger = LoggerFactory.getLogger(Util.class);
	 
	 
	private Util() {
	}

	public static boolean validarDni(String dni) {
		boolean valido = false;
		final String REGEX = "[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKET]";
		final Pattern pattern = Pattern.compile(REGEX);
		final Matcher matcher = pattern.matcher(dni);
		
		if (matcher.matches() && comprobarLetraDni(dni)) {
		
			valido = true;
		}
		
		return valido;
	}
	
	private static boolean comprobarLetraDni(String dni) {
		boolean valido = false;
		String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
	
		try {
			int dniNum = Integer.parseInt(dni.substring(0, dni.length() - 1));
			char letra = letras.charAt(dniNum % 23);
			
			if (letra == dni.charAt(dni.length() - 1)) {
				valido = true;
				
			}
		} catch (Exception e) {
			valido = false;
		}

		return valido;

	}
	
	
	public static final Date parseLatinDate(String date) throws ParseException
	{
		Date fecha;
		SimpleDateFormat dfmt = new SimpleDateFormat(FECHA_PATRON);
		fecha = dfmt.parse(date);
		return fecha;
	}
}
