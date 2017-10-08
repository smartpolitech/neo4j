package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;
/**
 * Clase que almacena la información de un connector
 * @author alejandro
 *
 */
public class Connector {
	String connectorType;
	String name;
	String area;
	ArrayList<String> connectorLocation;
	//Array que guarda plantas(Floor) y conectores(Connector) del edificio
	ArrayList<Object> floorsConnected;
	String startDate;
	String endDate;
	ArrayList<String> location;
	String roomId;
	
	public ArrayList<String> getLocation() {return location;}
	public void setLocation(ArrayList<String> location) {this.location = location;}
	public String getRoomId() {return roomId;}
	public void setRoomId(String roomId) {this.roomId = roomId;}
	public String getConnectorType() {return connectorType;}
	public void setConnectorType(String connectorType) {this.connectorType = connectorType;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getArea() {return area;}
	
	public void setArea(String area) {this.area = area;}
	
	public ArrayList<String> getConnectorLocation() {return connectorLocation;}
	
	public void setConnectorLocation(ArrayList<String> connectorLocation) {
		this.connectorLocation = connectorLocation;}
	
	public ArrayList<Object> getFloorsConnected() {return floorsConnected;}
	
	public void setFloorsConnected(ArrayList<Object> floorsConnected) {
		this.floorsConnected = floorsConnected;}
	
	public String getStartDate() {return startDate;}
	
	public void setStartDate(String startDate) {this.startDate = startDate;}
	
	public String getEndDate() {return endDate;}
	
	public void setEndDate(String endDate) {this.endDate = endDate;}
}
