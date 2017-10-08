package org.smartpolitech.model_sensores;

import java.util.ArrayList;

public class SensorData {
	
	ArrayList<SensorLog> sensorLog;
	
	public SensorData(){
		sensorLog=new ArrayList<SensorLog>();
	}

	public ArrayList<SensorLog> getSensorLog() {
		return sensorLog;
	}

	public void setSensorLog(ArrayList<SensorLog> sensorLog) {
		this.sensorLog = sensorLog;
	}

}
