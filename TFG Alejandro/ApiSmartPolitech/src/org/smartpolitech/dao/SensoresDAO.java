package org.smartpolitech.dao;

import java.util.ArrayList;
import org.influxdb.InfluxDB;
import org.smartpolitech.model_sensores.SensorLog;


public interface SensoresDAO {
	
	public abstract void setConnection(InfluxDB conn);
	public abstract SensorLog getSensorV(String sensorid, int horasd);
	public abstract double getMedTempSensor(String sensorid, int horas);
	public abstract double getMedTempSensores(ArrayList<String> sensoresid, int horas);
	

}
