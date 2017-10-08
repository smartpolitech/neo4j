package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;
/**
 * CLase que alamacena los datos de una planta
 * @author alejandro
 *
 */
public class Floor {
	String floorId;
	ArrayList<Room> placesLog;
	
	public String getFloorId() {
		return floorId;
	}
	public void setFloorId(String floorId) {
		this.floorId = floorId;
	}
	public ArrayList<Room> getPlacesLog() {
		return placesLog;
	}
	public void setPlacesLog(ArrayList<Room> placesLog) {
		this.placesLog = placesLog;
	}
	
}
