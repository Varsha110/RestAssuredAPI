package POJO;

import java.util.List;

public class addPlace {
	private int accuracy;
	private String name;
	private String phone_number;
	private String address;
	private String website;
	private String language;
	private locationChildOfAddPlace location;
	private List<String> types;
	
	
	public int getAccuracy() {
		return accuracy;
		
	}
	public void setAccuracy(int accuraacy) {
		this.accuracy = accuraacy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public locationChildOfAddPlace getLocation() {
		return location;
	}
	public void setLocation(locationChildOfAddPlace location) {
		this.location = location;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}


}
 