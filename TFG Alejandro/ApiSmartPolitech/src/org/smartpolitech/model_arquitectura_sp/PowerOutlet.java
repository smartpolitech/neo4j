package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;

/**
 * Clase que alamacena lso datos estáticos de una toma de corriente
 * @author alejandro
 *
 */
public class PowerOutlet extends Device {
	
	String voltage;
	String amperage;
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
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getAmperage() {
		return amperage;
	}
	public void setAmperage(String amperage) {
		this.amperage = amperage;
	}			
}


