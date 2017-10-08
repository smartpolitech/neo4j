package org.smartpolitech.dao;

import java.sql.*;
import java.util.ArrayList;

import org.influxdb.InfluxDB;
import org.smartpolitech.model_reservas.Entry;
import org.smartpolitech.model_reservas.EntryArea;
import org.smartpolitech.model_reservas.EntryRoom;
import org.smartpolitech.model_reservas.Room;

public interface ReservaEspacioDAO {
	
	public abstract void setConnection(Connection conn); 
	public abstract ArrayList<Entry> entryRankOfTime(int startTime, int endTime,int id);
	public abstract ArrayList<Room> roomsByArea_Id(int id);
	public abstract ArrayList<EntryArea> EntrysByArea_Id_RankOfTime(int startTime, int endTime, int id);
	public abstract ArrayList<EntryRoom> EntrysByRoom_Id_RankOfTime(int startTime, int endTime, int id);

}
