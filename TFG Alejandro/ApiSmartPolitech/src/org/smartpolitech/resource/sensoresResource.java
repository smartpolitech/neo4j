package org.smartpolitech.resource;

import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.influxdb.InfluxDB;

import org.neo4j.driver.v1.Session;
import org.smartpolitech.dao.ArquitecturaSPDAO;
import org.smartpolitech.dao.ArquitecturaSPDAOimp;
import org.smartpolitech.dao.SensoresDAO;
import org.smartpolitech.dao.SensoresDAOimpl;
import org.smartpolitech.model_arquitectura_sp.Sensor;
import org.smartpolitech.model_sensores.SensorData;
import org.smartpolitech.model_sensores.SensorLog;


@Path("/sensores")
public class sensoresResource {

	@Context
	ServletContext sc;

	@GET
	@Path("/{sensorid: [0-9a-zA-Z_]+}/{horas: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public SensorLog getSensorValores(@PathParam("sensorid") String sensorId, @PathParam("horas") int horas,
			@Context HttpServletRequest request) {

		InfluxDB conn = (InfluxDB) sc.getAttribute("ConnInfluxDB");
		SensoresDAO sensores = new SensoresDAOimpl();
		sensores.setConnection(conn);
		SensorLog sensorLog = sensores.getSensorV(sensorId, horas);
		return sensorLog;
	}

	@GET
	@Path("/alldate/{sensorid: [0-9a-zA-Z_]+}/{horas: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public Sensor getAllDateSensor(@PathParam("sensorid") String sensorId, @PathParam("horas") int horas,
			@Context HttpServletRequest request) {

		InfluxDB conn = (InfluxDB) sc.getAttribute("ConnInfluxDB");
		Session sessionNeo4j = (Session) sc.getAttribute("ConnNeo4j");

		SensoresDAO sensores = new SensoresDAOimpl();
		ArquitecturaSPDAO arquitectura = new ArquitecturaSPDAOimp();

		sensores.setConnection(conn);
		arquitectura.setConnection(sessionNeo4j);

		SensorData sensorData=new SensorData();
		SensorLog sensorLog = sensores.getSensorV(sensorId, horas);
		Sensor sensor = arquitectura.getSensor(sensorId);
		sensorData.getSensorLog().add(sensorLog);
		sensor.setSensorData(sensorData);
		return sensor;
	}

	@GET
	@Path("/media/{sensorid: [0-9a-zA-Z_]+}/{horas: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
	public double getMedTempSensor(@PathParam("sensorid") String sensorId, @PathParam("horas") int horas,
			@Context HttpServletRequest request) {

		InfluxDB conn = (InfluxDB) sc.getAttribute("ConnInfluxDB");
		SensoresDAO sensores = new SensoresDAOimpl();
		sensores.setConnection(conn);
		Double med = sensores.getMedTempSensor(sensorId, horas);
		return med;
	}

	@POST
	@Path("/media/{horas: [0-9]+}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public double getMedTempSensores(ArrayList<String> sensorsId, @PathParam("horas") int horas,
			@Context HttpServletRequest request) {

		InfluxDB conn = (InfluxDB) sc.getAttribute("ConnInfluxDB");
		SensoresDAO sensores = new SensoresDAOimpl();
		sensores.setConnection(conn);
		double med = sensores.getMedTempSensores(sensorsId, horas);
		return med;
	}

}
