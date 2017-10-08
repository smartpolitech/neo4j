package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;
/**
 * Clase que alamacena los datos de un dispositivo wap 
 * @author alejandro
 *
 */
public class Wap extends Device {
	String serialNumber;
	String weight;
	String voltage;
	int USBPortsNumber;
	int ethernetPortsNumber;
	String roomId;
	ArrayList<String> location;
	public String getRoomId() {return roomId;}
	public void setRoomId(String roomId) {this.roomId = roomId;}
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
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public int getUSBPortsNumber() {
		return USBPortsNumber;
	}
	public void setUSBPortsNumber(int uSBPortsNumber) {
		USBPortsNumber = uSBPortsNumber;
	}
	public int getEthernetPortsNumber() {
		return ethernetPortsNumber;
	}
	public void setEthernetPortsNumber(int ethernetPortsNumber) {
		this.ethernetPortsNumber = ethernetPortsNumber;
	}
}
