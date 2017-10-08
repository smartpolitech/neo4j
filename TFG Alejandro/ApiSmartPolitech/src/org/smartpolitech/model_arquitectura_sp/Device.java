package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;
/**
 * Clase que guarda los datos genericos y estatico de un dispositivo
 * Todas las clases que implementan un dispositivo en concreto
 * heredan de esta.
 * @author alejandro
 *
 */
public class Device {
	String id;
	String deviceType;
	String name;
	String brand;
	String model;
	String type;
	String additionalInformation;
	String startDate;
	String endDate;
	boolean enabled;
	
	public boolean getEnabled() {return enabled;}
	public void setEnabled(boolean enabled) {this.enabled = enabled;}
	public String getId() {return id;}
	public void setId(String id) {this.id = id;}
	public String getDeviceType() {return deviceType;}
	public void setDeviceType(String deviceType) {this.deviceType = deviceType;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getBrand() {return brand;}
	public void setBrand(String brand) {this.brand = brand;}
	public String getModel() {return model;}
	public void setModel(String model) {this.model = model;}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public String getAdditionalInformation() {return additionalInformation;}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;}
	public String getStartDate() {return startDate;}
	public void setStartDate(String startDate) {this.startDate = startDate;}
	public String getEndDate() {return endDate;}
	public void setEndDate(String endDate) {this.endDate = endDate;}
	
}
