package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;
/**
 * lase que almacena los datos estáticos de un extintor
 * @author alejandro
 *
 */
public class Extinguisher extends Device {
	
	
	String serialNumber;
	String weight;
	String volumen;
	String roomId;
	ArrayList<String> location;;
	
	public ArrayList<String> getLocation() {
		return location;
	}
	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getVolumen() {
		return volumen;
	}
	public void setVolumen(String volumen) {
		this.volumen = volumen;
	}

}
	
	
	
	
		
