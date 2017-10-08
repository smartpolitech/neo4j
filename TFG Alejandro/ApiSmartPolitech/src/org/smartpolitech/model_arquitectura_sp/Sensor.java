package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;

import org.smartpolitech.model_sensores.SensorData;
import org.smartpolitech.model_sensores.SensorLog;

/**
 * Clase que almacena los datos estáticos de un sensor. Los datos que 
 * genera el dispositivo se alamacenan en INlfuxDB y se gestiona con
 * la interfaz SensoresDAO.
 * @author alejandro
 *
 */
public class Sensor extends Device {
	
	String serialNumber;
    String description;
    SensorData sensorData;
    
    public Sensor(){
    	sensorData=new SensorData();
    }
	public SensorData getSensorData() {
		return sensorData;
	}

	public void setSensorData(SensorData sensorData) {
		this.sensorData = sensorData;
	}

	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	} 
}
