package org.smartpolitech.dao;


import java.util.ArrayList;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.smartpolitech.model_arquitectura_sp.Building;
import org.smartpolitech.model_arquitectura_sp.Sensor;


public interface ArquitecturaSPDAO {
	
	public abstract void setConnection(Session session);
	public abstract Sensor getSensor(String sensorId);
	public abstract  StatementResult getSensoresPlanta(String floorId);
	public ArrayList<Sensor> getSensoresRoom(String roomId);
	public Building getDataBuiding(String buidingId);

}
