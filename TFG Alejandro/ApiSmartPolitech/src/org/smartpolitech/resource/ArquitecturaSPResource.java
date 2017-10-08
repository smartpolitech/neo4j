package org.smartpolitech.resource;

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

import org.influxdb.InfluxDB;
import org.influxdb.dto.QueryResult;
import org.neo4j.driver.v1.Session;
import org.smartpolitech.dao.ArquitecturaSPDAO;
import org.smartpolitech.dao.ArquitecturaSPDAOimp;
import org.smartpolitech.dao.SensoresDAO;
import org.smartpolitech.dao.SensoresDAOimpl;
import org.smartpolitech.model_arquitectura_sp.Building;
import org.smartpolitech.model_arquitectura_sp.Sensor;

@Path("/arquitectura")
public class ArquitecturaSPResource {

		  @Context
		  ServletContext sc;  
		  
		  @GET
		  @Path("/{roomid: [0-9a-zA-Z_]+}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public ArrayList<Sensor>  getSensoresRoom(@PathParam("roomid") 
		  String roomId,@Context HttpServletRequest request){
			  
			  Session sessionNeo4j =  (Session) sc.getAttribute("ConnNeo4j");
			  ArquitecturaSPDAO arquitectura=new ArquitecturaSPDAOimp();
			  arquitectura.setConnection(sessionNeo4j);
			  ArrayList<Sensor> sensores= arquitectura.getSensoresRoom(roomId);
		
			
			return sensores;
		  }
		  
		  @GET
		  @Path("/building/{buildingId: [0-9a-zA-Z_]+}")
		  @Produces(MediaType.APPLICATION_JSON)
		  public Building  getDataBuilding(@PathParam("buildingId")
		  String buildingId,@Context HttpServletRequest request){
			  
			  Session sessionNeo4j =  (Session) sc.getAttribute("ConnNeo4j");
			  ArquitecturaSPDAO arquitectura=new ArquitecturaSPDAOimp();
			  arquitectura.setConnection(sessionNeo4j);
			  Building b= arquitectura.getDataBuiding(buildingId);	
			return b;
		  }

}
