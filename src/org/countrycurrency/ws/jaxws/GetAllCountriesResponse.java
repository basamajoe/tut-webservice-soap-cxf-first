
package org.countrycurrency.ws.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * This class was generated by Apache CXF 2.7.13
 * Sat Nov 29 18:34:42 CET 2014
 * Generated source version: 2.7.13
 */

@XmlRootElement(name = "getAllCountriesResponse", namespace = "http://ws.countrycurrency.org/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllCountriesResponse", namespace = "http://ws.countrycurrency.org/")

public class GetAllCountriesResponse {

    @XmlElement(name = "countries")
    private java.util.List<org.countrycurrency.ws.type.Country> countries;

    public java.util.List<org.countrycurrency.ws.type.Country> getCountries() {
        return this.countries;
    }

    public void setCountries(java.util.List<org.countrycurrency.ws.type.Country> newCountries)  {
        this.countries = newCountries;
    }

}
