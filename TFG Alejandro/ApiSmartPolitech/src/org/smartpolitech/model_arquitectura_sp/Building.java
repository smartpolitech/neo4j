package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;
/**
 * Clase que almacena los datos de un edificio
 * @author alejandro
 *
 */
public class Building {
	String _id;
	String name;
	String municipality;
	String address;
	String postalCode;
	String province;
	String country;
	String area;
	String cadastralReference;
	ArrayList<String> buildingLocation;
	ArrayList<Floor> buildingData;
	
	public String get_id() {return _id;}
	public void set_id(String _id) {this._id = _id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getMunicipality() {return municipality;}
	public void setMunicipality(String municipality) {this.municipality = municipality;}
	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}
	public String getPostalCode() {return postalCode;}
	public void setPostalCode(String postalCode) {this.postalCode = postalCode;}
	public String getProvince() {return province;}
	public void setProvince(String province) {this.province = province;}
	public String getCountry() {return country;}
	public void setCountry(String country) {this.country = country;}
	public String getArea() {return area;}
	public void setArea(String area) {this.area = area;}
	public String getCadastralReference() {return cadastralReference;}
	public void setCadastralReference(String cadastralReference) {this.cadastralReference = cadastralReference;}
	public ArrayList<String> getBuildingLocation() {return buildingLocation;}
	public void setBuildingLocation(ArrayList<String> buildingLocation) {this.buildingLocation = buildingLocation;}
	public ArrayList<Floor> getBuildingData() {return buildingData;}
	public void setBuildingData(ArrayList<Floor> buildingData) {this.buildingData = buildingData;}
}
