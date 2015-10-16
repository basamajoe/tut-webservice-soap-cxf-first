package org.countrycurrency.ws;

import java.util.List;
import java.util.Set;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import org.countrycurrency.ws.exception.CountryCurrencyException;
import org.countrycurrency.ws.type.Country;
import org.countrycurrency.ws.type.Currency;
import org.countrycurrency.ws.type.Validation;

@WebService(name = "ICountryCurrency", wsdlLocation="wsdl/countrycurrencysei.wsdl", targetNamespace = "http://ws.countrycurrency.org/")
public interface ICountryCurrency {

	@WebMethod(operationName = "add")
	@WebResult(name = "wasadded")
	public boolean add(
			@WebParam(name = "validation", header = true) @XmlElement(required = true)  Validation validation,
			@WebParam(name = "country") Country country,
			@WebParam(name = "currency") Currency currency)
			throws CountryCurrencyException;

	@WebMethod(operationName = "update")
	@Oneway
	public void update(
			@WebParam(name = "validation", header = true) Validation validation,
			@WebParam(name = "currency") Currency currency,
			@WebParam(name = "country") Country country)
			throws CountryCurrencyException;

	@WebMethod(operationName = "remove")
	@WebResult(name = "wasremoved")
	public boolean remove(
			@WebParam(name = "validation", header = true) Validation validation,
			@WebParam(name = "country") Country country)
			throws CountryCurrencyException;

	@WebMethod(operationName = "getCurrencyByCountry")
	@WebResult(name = "currency")
	public Currency getCurrencyByCountry(
			@WebParam(name = "validation", header = true) Validation validation,
			@WebParam(name = "country") Country country)
			throws CountryCurrencyException;

	@WebMethod(operationName = "getAllCurrencies")
	@WebResult(name = "currencies")
	public Set<Currency> getAllCurrencies(
			@WebParam(name = "validation", header = true) Validation validation);

	@WebMethod(operationName = "getAllCountries")
	@WebResult(name = "countries")
	public List<Country> getAllCountries(
			@WebParam(name = "validation", header = true) Validation validation);
}
