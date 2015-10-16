package org.countrycurrency.ws.type;

public class Currency {
	
	private String code;
	private String name;
	private Double eurFactor;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getEurFactor() {
		return eurFactor;
	}

	public void setEurFactor(Double eurFactor) {
		this.eurFactor = eurFactor;
	}

	@Override
	public boolean equals(Object obj){
		return getCode().equals(((Currency) obj).getCode());
	}
	
	@Override
	public int hashCode(){
		return getCode().hashCode();
	}

	@Override
	public String toString() {
		return "Currency [code=" + code + ", name=" + name + ", eurFactor="
				+ eurFactor + "]";
	}
	
}
