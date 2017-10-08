package org.smartpolitech.dao;


import org.neo4j.driver.v1.*;
import org.smartpolitech.model_arquitectura_sp.Building;
import org.smartpolitech.model_arquitectura_sp.Device;
import org.smartpolitech.model_arquitectura_sp.Floor;
import org.smartpolitech.model_arquitectura_sp.Room;
import org.smartpolitech.model_arquitectura_sp.Sensor;

import static org.neo4j.driver.v1.Values.parameters;

import java.util.ArrayList;


public class ArquitecturaSPDAOimp implements ArquitecturaSPDAO {

	public static void main(String[] args) {
	
			Driver driver = GraphDatabase.driver ("bolt://localhost:7687",AuthTokens.basic( "alejandro", "insert789" ) );
			
			Session session = driver.session();
			ArquitecturaSPDAO a = new ArquitecturaSPDAOimp();
			a.setConnection(session);
			//a.getSensoresPlanta("UEXCCINF");
			a.getDataBuiding("UEXCCINF");
			a.getSensor("UEXCC_INF_P00_LAB007_SEN001_THC");
			//a.getSensoresRoom("UEXCC_INF_P00_CUA002");	
	} 
		
		
	
	/**
	 * Atributo donde se asigna la conexion al servidor de Neo4j
	 */
	 
	private Session session;
	
	/**
	 * Obtenemos conexión con la base de datos Neo4j
	 * 
	 */
	@Override
	public void setConnection(Session session) {
		this.session = session;
		//conn.createDatabase(nameDB);
	}
	
	
	/**
	 * metodo de prueba
	 */
	@Override
	public  StatementResult getSensoresPlanta(String floorId) {
		 StatementResult rs = session.run(
				  "MATCH (b:BUILDING) WHERE b._id={id}  RETURN b._id AS id",parameters("id", floorId));
		 
		 while(rs.hasNext()){
			 Record record = rs.next();
			    System.out.println( record.get("id").asString());
		 }
		
		return null;
	}
	
	/**
	 * metodo que devuelve la información estática  de un sensor
	 */
	public Sensor getSensor(String sensorId){
		 StatementResult rs = session.run(
				  "MATCH (d:DEVICE) WHERE d.id={id}  "
				  + "RETURN d.id AS id, d.Description AS descripcion,d.enabled AS enabled,"
				  + " d.type AS type",parameters("id", sensorId));
		 Sensor sensor=new Sensor();
		 while(rs.hasNext()){
			 Record record = rs.next();
			 	sensor.setId(record.get("id").asString());
			 	sensor.setType(record.get("type").asString());
			 	sensor.setEnabled(record.get("enabled").asBoolean());
			 	sensor.setDescription(record.get("descripcion").asString());
			    System.out.println(sensor);
		 }
		 
		 return sensor;
	}
	
	/**
	 * método que devuelve los sensores localizados en una habitción
	 */
	public ArrayList<Sensor> getSensoresRoom(String roomId){
		
		ArrayList<Sensor> sensores = new ArrayList<>();
		 StatementResult rs = session.run(
				  "MATCH (d:DEVICE) -[:BELONG]->(r:ROOM) WHERE r.roomId={id}  "
				  + "RETURN d.id AS id, d.Description AS descripcion,"
				  + " d.type AS type",parameters("id", roomId));
		 while(rs.hasNext()){
			 Record record = rs.next();
			 	Sensor sensor=new Sensor();
			 	sensor.setId(record.get("id").asString());
			 	sensor.setType(record.get("type").asString());
			 	sensor.setDescription(record.get("descripcion").asString());
			 	sensores.add(sensor);
			    System.out.println(sensor);
		 }
		 return sensores;
	}
	
	/**
	 * método que devuelve la información  localizada en un edificio
	 */
	
		
//		StatementResult rs = session.run(
//				  "MATCH  (d:DEVICE)-[:BELONG]-> (r:ROOM) -[:BELONG]-> (f:FLOOR) -[:BELONG]->(b:BUILDING {_id:\""+buidingId+"\"}) RETURN"
//				  		+ " f.floorID, b._id AS buildingId, r.roomId, d.id");
	public Building getDataBuiding(String buidingId){
		Building building;
		Floor f;
		Room room;
		ArrayList<Floor> floors = new ArrayList<>();
		ArrayList<Room> rooms = new ArrayList<>();
		ArrayList<Device> devices = new ArrayList<>();
		 StatementResult rsBuilding = session.run("MATCH(b:BUILDING {_id:\""+buidingId+"\"}) RETURN"
		 		+ " b._id ,b.name,b.municipality,b.address, b.postalCode,b.province,"
		 		+ "b.country,b.area,b.cadastralReference");
		 building=new Building();
		 Record record = rsBuilding.next();
		 building.set_id(record.get("b._id").asString());
		 building.setName(record.get("b.name").asString());
		 building.setMunicipality(record.get("b.municipality").asString());
		 building.setAddress(record.get("b.address").asString());
		 building.setPostalCode(record.get("b.postalCode").asString());
		 building.setProvince(record.get("b.province").asString());
		 building.setCountry(record.get("b.country").asString());
		 building.setArea(record.get("b.area").asString());
		 building.setCadastralReference(record.get("b.cadastralReference").asString());
		 StatementResult rs = session.run(
				  "MATCH  (f:FLOOR)-[:BELONG]->(b:BUILDING {_id:\""+buidingId+"\"})  RETURN"
				  		+ " f.floorID");
		 while(rs.hasNext()){
			 Record recordFloor = rs.next();
			 f=new Floor();
			 f.setFloorId( recordFloor.get("f.floorID").asString());
			 StatementResult rs2 = session.run(
					  "MATCH  (r:ROOM)-[:BELONG]->(f:FLOOR {floorID:\""+f.getFloorId()+"\"}) RETURN "
					  		+ " r.roomId,r.roomType,r.name,r.area");
			 rooms = new ArrayList<>();
			 while(rs2.hasNext()){
				 Record record2=rs2.next();
				 room=new Room();
				 room.setRoomId(record2.get("r.roomId").asString());
				 room.setRoomnType(record2.get("r.roomTYpe").asString());
				 room.setName(record2.get("r.name").asString());
				 room.setArea(record2.get("r.area").asString());
				 StatementResult rs3 = session.run(
						  "MATCH  (d:DEVICE)-[:BELONG]->(r:ROOM {roomId:\""+room.getRoomId()+"\"})  RETURN "
						  		+ " d.id,d.Description,d.enabled,d.type");
				 devices = new ArrayList<>();
				 while(rs3.hasNext()){
					 Record record3=rs3.next();
					 Device device= new Device();
					 device.setId(record3.get("d.id").asString());
					 device.setAdditionalInformation(record3.get("d.Description").asString());
					 device.setEnabled(record3.get("d.enabled").asBoolean());
					 device.setType(record3.get("d.type").asString());
					 devices.add(device);
				 }
				 room.setDeviceList(devices);
				 rooms.add(room);
			 }
			 f.setPlacesLog(rooms); 
			 floors.add(f);
		 }
		 building.setBuildingData(floors);
		 return building;
	}
	
	
}
