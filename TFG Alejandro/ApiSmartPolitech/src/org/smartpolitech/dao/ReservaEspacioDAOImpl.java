package org.smartpolitech.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.influxdb.InfluxDB;
import org.smartpolitech.model_reservas.Entry;
import org.smartpolitech.model_reservas.EntryArea;
import org.smartpolitech.model_reservas.EntryRoom;
import org.smartpolitech.model_reservas.Room;

public class ReservaEspacioDAOImpl implements ReservaEspacioDAO {
	
private Connection conn;
	
	
public static void main(String[] args) {
	ReservaEspacioDAO reserva=new ReservaEspacioDAOImpl();
	try {
		Connection conexionMySql = DriverManager.getConnection("jdbc:mysql://iepcc.unex.es/r3s4l_13pcc_",
				"opr3s4lspt", "62iFtyrwAA2CwjR8");
		reserva.setConnection(conexionMySql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//reserva.entryRankOfTime(1360571400, 1361532600, 10);
	//reserva.roomsByArea_Id(6);
	//reserva.EntrysByArea_Id_RankOfTime(1360571400, 1498048200, 6);
	//reserva.EntrysByRoom_Id_RankOfTime(1360571400, 1498048200, 10);
}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
	
	
	public ArrayList<Entry> entryRankOfTime(int startTime, int endTime,int id){
		Statement  s;
		//Array de la clase definida para el mapeo de la información de las reservas
		ArrayList<Entry> entrys=new ArrayList<>();
		try {
			s = conn.createStatement();
			ResultSet rs = s.executeQuery ("SELECT name,description, "
					+ "start_time, end_time FROM mrbs_entry WHERE start_time  "
					+ ">= "+startTime+" AND end_time <= "+ endTime +" AND room_id "
							+ "= "+id+" ORDER BY from_unixtime("+startTime+")");
			while (rs.next()) {
				
				Entry entry=new Entry();
				entry.setName(rs.getString(1));
				entry.setDescription(rs.getString(2));
				entry.setStart_time(rs.getInt(3));
				entry.setEnd_time(rs.getInt(4));
				entrys.add(entry);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<entrys.size();i++){
			Entry entry=entrys.get(i);
			System.out.println(entry.getName());
			System.out.println(entry.getDescription());
			System.out.println(entry.getStart_time());
			System.out.println(entry.getEnd_time());
			System.out.println("-------------------------");

		}
		return entrys;
	}
	
	public ArrayList<Room> roomsByArea_Id(int id){
		Statement  s;
		//Array de la clase definida para el mapeo de la información de las reservas
		ArrayList<Room> rooms=new ArrayList<>();
		try {
			s = conn.createStatement();
			ResultSet rs = s.executeQuery ("SELECT room_name, "
					+ "sort_key, description, capacity FROM mrbs_room WHERE "
					+ "area_id= "+id);
			while (rs.next()) {
				
				Room room=new Room();
				room.setRoom_name(rs.getString(1));
				room.setSort_key(rs.getString(2));
				room.setDescription(rs.getString(3));
				room.setCapacity(rs.getInt(4));
				rooms.add(room);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<rooms.size();i++){
			Room room=rooms.get(i);
			System.out.println(room.getRoom_name());
			System.out.println(room.getSort_key());
			System.out.println(room.getDescription());
			System.out.println(room.getCapacity());
			System.out.println("-------------------------");

		}
		return rooms;
	}
	
	
	public ArrayList<EntryArea> EntrysByArea_Id_RankOfTime(int startTime, int endTime, int id){
		Statement  s;
		//Array de la clase definida para el mapeo de la información de las reservas
		ArrayList<EntryArea> entryAreas=new ArrayList<>();
		try {
			s = conn.createStatement();
			ResultSet rs = s.executeQuery ("SELECT a.area_name, r.room_name, "
					+ "r.capacity, e.start_time, e.end_time, e.name, e.description FROM "
					+ "mrbs_area a "
					+ "INNER JOIN mrbs_room r ON(a.id=r.area_id) INNER JOIN mrbs_entry e "
					+ "ON(r.id=e.room_id) WHERE a.id= "+id+" AND e.start_time >= "+startTime+" "
							+ "AND e.end_time<="+endTime);
			while (rs.next()) {
				
				EntryArea entryArea=new EntryArea();
				entryArea.setArea_name(rs.getString(1));
				entryArea.setRoom_name(rs.getString(2));
				entryArea.setCapacity(rs.getInt(3));
				entryArea.setStart_time(rs.getInt(4));
				entryArea.setEnd_time(rs.getInt(5));
				entryArea.setName_reserva(rs.getString(6));
				entryArea.setDescription_reserva(rs.getString(7));
				entryAreas.add(entryArea);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<entryAreas.size();i++){
			EntryArea e= entryAreas.get(i);
			System.out.println(e.getArea_name());
			System.out.println(e.getRoom_name());
			System.out.println(e.getCapacity());
			System.out.println(e.getStart_time());
			System.out.println(e.getEnd_time());
			System.out.println(e.getName_reserva());
			System.out.println(e.getDescription_reserva());
			System.out.println("-------------------------");

		}
		return entryAreas;
	}
	
	
	public ArrayList<EntryRoom> EntrysByRoom_Id_RankOfTime(int startTime, int endTime, int id){
		Statement  s;
		//Array de la clase definida para el mapeo de la información de las reservas
		ArrayList<EntryRoom> entryRooms=new ArrayList<>();
		try {
			s = conn.createStatement();
			ResultSet rs = s.executeQuery ("select r.room_name, r.capacity, e.start_time, "
					+ "e.end_time, e.name FROM mrbs_room r INNER JOIN mrbs_entry e "
					+ "ON(r.id=e.room_id) "
					+ "WHERE r.id= "+id+" AND e.start_time >= "+startTime+" AND "
							+ "e.end_time<= "+ endTime);
			while (rs.next()) {
				EntryRoom entryRoom= new EntryRoom();
				entryRoom.setRoom_name(rs.getString(1));
				entryRoom.setCapacity(rs.getInt(2));
				entryRoom.setStart_time(rs.getInt(3));
				entryRoom.setEnd_time(rs.getInt(4));
				entryRoom.setName_entry(rs.getString(5));
				entryRooms.add(entryRoom);
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<entryRooms.size();i++){
			
			EntryRoom e=entryRooms.get(i);
			System.out.println(e.getRoom_name());
			System.out.println(e.getCapacity());
			System.out.println(e.getStart_time());
			System.out.println(e.getEnd_time());
			System.out.println(e.getName_entry());
			System.out.println("-------------------------");

		}
		return entryRooms;
	}
	
	
	
	
	
	
	
	
	
}
