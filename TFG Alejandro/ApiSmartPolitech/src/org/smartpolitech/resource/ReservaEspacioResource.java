package org.smartpolitech.resource;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


import org.smartpolitech.dao.ReservaEspacioDAO;
import org.smartpolitech.dao.ReservaEspacioDAOImpl;
import org.smartpolitech.model_reservas.Entry;
import org.smartpolitech.model_reservas.EntryArea;
import org.smartpolitech.model_reservas.EntryRoom;
import org.smartpolitech.model_reservas.Room;


@Path("/reserva")
public class ReservaEspacioResource {
	
	 @Context
	  ServletContext sc;
	  
	  @GET
	  @Path("/entryRankOfTime/{startTime: [0-9]+}/{endTime: [0-9]+}/{id: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<Entry> getConsulta(@PathParam("startTime") int startTime,@PathParam("endTime") 
	  int endTime,@PathParam("id") int Id,@Context HttpServletRequest request){
		  Connection connMysql =  (Connection) sc.getAttribute("ConnMySql");
		  ReservaEspacioDAO rs=new ReservaEspacioDAOImpl();
		  rs.setConnection(connMysql);
		  ArrayList<Entry> entrys= rs.entryRankOfTime(startTime, endTime, Id);
		return entrys;
	  }
	  
	  
	  @GET
	  @Path("/roomsByArea_Id/{id: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<Room> getConsulta2(@PathParam("id") int Id,@Context HttpServletRequest request){
		  Connection connMysql =  (Connection) sc.getAttribute("ConnMySql");
		  ReservaEspacioDAO rs=new ReservaEspacioDAOImpl();
		  rs.setConnection(connMysql);
		  ArrayList<Room> rooms= rs.roomsByArea_Id(Id);
		return rooms;
	  }
	  
	  
	  @GET
	  @Path("/EntrysByArea_Id_RankOfTime/{startTime: [0-9]+}/{endTime: [0-9]+}/{id: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<EntryArea> getConsulta3(@PathParam("startTime") int startTime,@PathParam("endTime") 
	  int endTime,@PathParam("id") int Id,@Context HttpServletRequest request){
		  Connection connMysql =  (Connection) sc.getAttribute("ConnMySql");
		  ReservaEspacioDAO rs=new ReservaEspacioDAOImpl();
		  rs.setConnection(connMysql);
		  ArrayList<EntryArea> entryAreas= rs.EntrysByArea_Id_RankOfTime(startTime, endTime, Id);
		return entryAreas;
	  }
	  
	  
	  @GET
	  @Path("/EntrysByRoom_Id_RankOfTime/{startTime: [0-9]+}/{endTime: [0-9]+}/{id: [0-9]+}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public ArrayList<EntryRoom> getConsulta4(@PathParam("startTime") int startTime,@PathParam("endTime") 
	  int endTime,@PathParam("id") int Id,@Context HttpServletRequest request){
		  Connection connMysql =  (Connection) sc.getAttribute("ConnMySql");
		  ReservaEspacioDAO rs=new ReservaEspacioDAOImpl();
		  rs.setConnection(connMysql);
		  ArrayList<EntryRoom> entryRooms= rs.EntrysByRoom_Id_RankOfTime(startTime, endTime, Id);
		return entryRooms;
	  }
	  
	  

}
