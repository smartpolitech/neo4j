package org.smartpolitech.model_arquitectura_sp;
/**
 * Clase en la que mapeamos los datos referente a una habitación
 * 
 * @author alejandro
 *
 */

import java.util.ArrayList;

public class Room {
	String roomId;
	String roomnType;//"classroom","office","laboratory","room","toilet","communityArea""roomID": {
	String name;
	String area;
	ArrayList<String> roomLocation;
	ArrayList<Device> deviceList;
	String startDate;
	String endDate;
	
	public String getRoomId() {return roomId;
	}
	public void setRoomId(String roomId) {this.roomId = roomId;
	}
	public String getRoomnType() {return roomnType;
	}
	public void setRoomnType(String roomnType) {this.roomnType = roomnType;
	}
	public String getName() {return name;
	}
	public void setName(String name) {this.name = name;
	}
	public String getArea() {return area;
	}
	public void setArea(String area) {this.area = area;
	}
	public ArrayList<String> getRoomLocation() {return roomLocation;
	}
	public void setRoomLocation(ArrayList<String> roomLocation) {this.roomLocation = roomLocation;
	}
	public ArrayList<Device> getDeviceList() {return deviceList;
	}
	public void setDeviceList(ArrayList<Device> deviceList) {this.deviceList = deviceList;
	}
	public String getStartDate() {return startDate;
	}
	public void setStartDate(String startDate) {this.startDate = startDate;
	}
	public String getEndDate() {return endDate;
	}
	public void setEndDate(String endDate) {this.endDate = endDate;
	}
}
