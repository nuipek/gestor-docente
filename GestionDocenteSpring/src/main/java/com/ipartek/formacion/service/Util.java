package com.ipartek.formacion.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Util {
	public static final String FECHA_PATRON = "dd/MM/yyyy";
	private Util() {
	}

	public static boolean validarDni(String dni) {
		return true;
	}
	
	public static final Date parseLatinDate(String date) throws ParseException
	{
		Date fecha;
		SimpleDateFormat dfmt = new SimpleDateFormat(FECHA_PATRON);
		fecha = dfmt.parse(date);
		return fecha;
	}
}
