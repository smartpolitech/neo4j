package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;
/**
 * Clase que almacena los datos estáticos de un radiador
 * @author alejandro
 *
 */
public class Radiator extends Device {
	String serialNumber;
	String size;
	String weight;
	String power;
	String roomId;
	ArrayList<String> location;
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public ArrayList<String> getLocation() {
		return location;
	}
	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
}
