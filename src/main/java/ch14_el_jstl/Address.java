package ch14_el_jstl;

public class Address {
	private int aipCode;
	private String city;
	private String country;
	
	public Address() { }
	public Address(int aipCode, String city, String country) {
		this.aipCode = aipCode;
		this.city = city;
		this.country = country;
	}
	
	@Override
	public String toString() {
		return "Address [aipCode=" + aipCode + ", city=" + city + ", country=" + country + "]";
	}
	
	public int getAipCode() {
		return aipCode;
	}
	public void setAipCode(int aipCode) {
		this.aipCode = aipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
}
