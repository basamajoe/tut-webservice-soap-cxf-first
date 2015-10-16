package org.countrycurrency.ws.exception;

import javax.xml.ws.WebFault;

@WebFault
public class CountryCurrencyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CountryCurrencyException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CountryCurrencyException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CountryCurrencyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CountryCurrencyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CountryCurrencyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
