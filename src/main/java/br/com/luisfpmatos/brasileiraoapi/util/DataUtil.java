package br.com.luisfpmatos.brasileiraoapi.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

	public static String formataDateEmString(Date data, String mask) {
		DateFormat format = new SimpleDateFormat(mask);
		return format.format(data);
	}

}
