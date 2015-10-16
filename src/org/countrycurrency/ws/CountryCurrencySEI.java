package org.countrycurrency.ws;

import java.util.List;
import java.util.Set;

import javax.jws.WebService;

import org.countrycurrency.ws.exception.CountryCurrencyException;
import org.countrycurrency.ws.type.Country;
import org.countrycurrency.ws.type.Currency;
import org.countrycurrency.ws.type.Validation;
import org.countrycurrency.ws.util.MyFactory;

@WebService(endpointInterface = "org.countrycurrency.ws.ICountryCurrency")
public class CountryCurrencySEI implements ICountryCurrency {

	private MyFactory<Country, Currency> dynamicDb = new MyFactory<Country, Currency>();

	public boolean add(Validation validation, Country country, Currency currency)
			throws CountryCurrencyException {

		if (dynamicDb.contains(country) == false) {
			// Si NO aparece en la base de datos - a�adimos
			dynamicDb.add(country, currency);
			System.out.println("-> Operaton ADD: OK country (" + country + ") currency ("
					+ currency + ") added");
			return true;
		} else {
			// Si YA esta� en la base de datos - add = false
			System.out.println("-> Operaton ADD: KO already added");
			return false;
		}
	}

	public void update(Validation validation, Currency currency, Country country)
			throws CountryCurrencyException {
		if (dynamicDb.contains(country)) {
			// Country exists -> update
			try {
				dynamicDb.update(country, currency);
				System.out.println("-> Operation UPD: OK country (" + country + ") currency ("
						+ currency + ") added");
			} catch (Exception e) {
				System.out.println("-> Operation UPD: KO");
				throw new CountryCurrencyException("Fallo la inserci�n en la base de datos");
			}
		}
	}

	public boolean remove(Country country, Validation validation) throws CountryCurrencyException {
		if (dynamicDb.contains(country)) {
			// Country exists -> remove
			try {
				dynamicDb.remove(country);
				System.out.println("-> Operation DEL: OK country (" + country + ")");
			} catch (Exception e) {
				System.out.println("-> Operation DEL: KO");
				throw new CountryCurrencyException("Fallo la inserción en la base de datos");
			}
			return true;
		} else {
			// Country doesn't exists - remove=false
			return false;
		}
	}

	public Currency getCurrencyByCountry(Country country, Validation validation)
			throws CountryCurrencyException {
		Currency currency;
		System.out.println("-> OperatIon GETCURRENCYBYID: country (" + country + ")");

		if (country == null) {
			throw new CountryCurrencyException();
		}

		try {
			currency = dynamicDb.getValueByKey(country);
			System.out.println("-> OperatIon GETCURRENCYBYID: OK currency (" + currency + ")");
		} catch (Exception e) {
			System.out.println("-> OperatIon GETCURRENCYBYID: KO");
			throw new CountryCurrencyException();
		}

		return currency;
	}

	public Set<Currency> getAllCurrencies(Validation validation) {
		System.out.println("-> Operation GETALLCURRENCIES");
		return dynamicDb.getAllValues();
	}

	public List<Country> getAllCountries(Validation validation) {
		System.out.println("-> Operation GETALLCOUNTRIES");
		return dynamicDb.getAllKeys();
	}

	public boolean remove(Validation validation, Country country) throws CountryCurrencyException {
		if (dynamicDb.contains(country)) {
			// Country exists -> remove
			try {
				dynamicDb.remove(country);
				System.out.println("-> Operation DEL: OK country (" + country + ")");
			} catch (Exception e) {
				System.out.println("-> Operation DEL: KO");
				throw new CountryCurrencyException("Fallo la inserción en la base de datos");
			}
			return true;
		} else {
			// Country doesn't exists - remove=false
			return false;
		}
	}

	public Currency getCurrencyByCountry(Validation validation, Country country)
			throws CountryCurrencyException {
		Currency currency;
		System.out.println("-> OperatIon GETCURRENCYBYID: country (" + country + ")");

		if (country == null) {
			throw new CountryCurrencyException();
		}

		try {
			currency = dynamicDb.getValueByKey(country);
			System.out.println("-> OperatIon GETCURRENCYBYID: OK currency (" + currency + ")");
		} catch (Exception e) {
			System.out.println("-> OperatIon GETCURRENCYBYID: KO");
			throw new CountryCurrencyException();
		}

		return currency;
	}
}
