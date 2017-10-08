package org.smartpolitech.model_sensores;

import java.util.ArrayList;

public class SensorLog {
	
	String firstUpdate;
	String lastUpdate;
	ArrayList<String> location;
	String roomId;
	ArrayList<DataLog> dataLog;
	
	public SensorLog(){
		dataLog=new ArrayList<>();
	}
	
	public ArrayList<DataLog> getDataLog() {
		return dataLog;
	}

	public void setDataLog(ArrayList<DataLog> dataLog) {
		this.dataLog = dataLog;
	}

	public String getFirstUpdate() {
		return firstUpdate;
	}
	public void setFirstUpdate(String firstUpdate) {
		this.firstUpdate = firstUpdate;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
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
}
