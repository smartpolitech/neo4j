package org.smartpolitech.dao;

import java.util.ArrayList;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Result;
import org.influxdb.dto.QueryResult.Series;
import org.smartpolitech.model_sensores.DataCollected;
import org.smartpolitech.model_sensores.DataLog;
import org.smartpolitech.model_sensores.SensorLog;


public class SensoresDAOimpl implements SensoresDAO {

	public static void main(String[] args) {
		SensoresDAO s=new SensoresDAOimpl();
		
				
		//InfluxDB conn=InfluxDBFactory.connect("http://localhost:8086", "root", "root");
		InfluxDB conn=InfluxDBFactory.connect("http://10.253.247.18:8086", "guest", "smartpolitech");
		s.setConnection(conn);
		/*BatchPoints batchPoints = BatchPoints
				.database("aTimeSeries")
				.tag("async", "true")
				.retentionPolicy("autogen")
				.consistency(ConsistencyLevel.ALL)
				.build();
		Point point1 = Point.measurement("UEXCC_INF_P00_COM001")
					.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
					.addField("temp", 12.56)
					.addField("hum", 12)
					.addField("rt", 12.56)
					.build();
		
		batchPoints.point(point1);
		conn.write(batchPoints);
		
		System.out.println("conectado a influxdb");
		String dbName = "aTimeSeries";
		conn.createDatabase(dbName);
		System.out.println("conectado a la base de datos");
		
		UEXCC_INF_P00_CUA002_SEN002_AGU
		
		
		
		BatchPoints batchPoints = BatchPoints
						.database(dbName)
						.tag("async", "true")
						.retentionPolicy("autogen")
						.consistency(ConsistencyLevel.ALL)
						.build();
		Point point1 = Point.measurement("sensor1")
							.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
							.addField("temp", 12.56)
							.addField("hum", 12)
							.addField("rt", 12.56)
							.build();
		
		Point point2 = Point.measurement("sensor1")
				.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.addField("temp", 34.56)
				.addField("hum", 34)
				.addField("rt", 34.56)
				.build();
		
		Point point3 = Point.measurement("sensor1")
				.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
				.addField("temp", 23.56)
				.addField("hum", 23)
				.addField("rt", 23.34)
				.build();
		
		Point point4 = Point.measurement("sensor2")
							//	.time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
							.addField("temp", 34.56)
							.addField("hum", 45)
							.addField("rt", 23.56)
							.build();
		
		//batchPoints.point(point1);
		//batchPoints.point(point2);
		//batchPoints.point(point3);
		batchPoints.point(point4);
		
		//conn.write(batchPoints);*/
		
		//UEXCC_INF_P00_CUA002_SEN001_THV
		//UEXCC_INF_P00_LAB007_SEN001_THC
		//UEXCC_INF_P00_CUA002_SEN002_AGU
		
		
		//UEXCC_INF_P00_CUA019_SEN001_ELE
		//UEXCC_INF_P00_CUA019_SEN002_ELE
		//UEXCC_INF_P00_COM003_SEN001_AGU
		//UEXCC_INF_P00_COM048_SEN003_AGU
		//UEXCC_INF_P00_AUL030_SEN001_THR
		//UEXCC_INF_P00_COM048_SEN001_THR
		//UEXCC_INF_P00_CUA002_SEN001_THV
		//UEXCC_INF_P00_CUA002_SEN002_AGU
		//UEXCC_INF_P00_CUA019_SEN001_THV
		//UEXCC_INF_P00_DES011_SEN002_THV
		//UEXCC_INF_P00_DES035_SEN002_THC
		//UEXCC_INF_P00_LAB007_SEN001_THC
		//UEXCC_INF_P00_LAB033_SEN001_THV
		//UEXCC_INF_P00_LAB037_SEN001_THR
		//UEXCC_INF_P00_LAB041_SEN001_THR
		//UEXCC_INF_P01_AUL031_SEN001_THR
		//UEXCC_INF_P01_DES005_SEN001_THR
		//UEXCC_INF_P01_DES012_SEN001_THR
		//UEXCC_INF_P01_DES044_SEN001_THR
		//UEXCC_INF_P01_LAB023_SEN001_THR
		//UEXCC_INF_P01_LAB024_SEN001_THC
		//UEXCC_INF_P01_LAB029_SEN001_THR
		//UEXCC_INF_P01_LAB029_SEN001_THR
		s.getSensorV("UEXCC_INF_P01_LAB029_SEN001_THR", 1);
		//s.getMedTempSensor("UEXCC_INF_P00_CUA002_SEN002_AGU",1);
		//ArrayList<String> list=new ArrayList<String>();
		//list.add("UEXCC_INF_P01_LAB024_SEN001_THC");
		//list.add("UEXCC_INF_P01_LAB029_SEN001_THR");
		//list.add("UEXCC_INF_P00_CUA002_SEN002_AGU");
		//s.getMedTempSensores(list,1);
		//conn.deleteDatabase(dbName);
		//System.out.println("base de datos eliminada");
	}
	
	/**
	 * Atributo donde se asigna la conexion al servidor de InfluxDB
	 */
	private InfluxDB conn;
	/**
	 * Nombre d ela base de datos donde se obtendrán los datos
	 */
	private static String  nameDB="sensors";
	
	/**
	 * Obtenemos conexión con la base de datos INfluxDB
	 * Este metodo es llamado por todos los métodos del paquete resource 
	 * localizada en la universidad
	 */
	@Override
	public void setConnection(InfluxDB conn) {
		this.conn = conn;
		//conn.createDatabase(nameDB);
	}
	
	/**
	 * metodo que devuelve los puntos recogidos en las ultimas dos horas
	 */
	@Override
	public SensorLog getSensorV( String sensorid, int horas){
		SensorLog sensorLog= new SensorLog();
		DataLog dataLog=new DataLog();
		ArrayList<String> columns= new ArrayList<String>();
		List<List<Object>> points= new ArrayList<>();
		List<Object> point= new ArrayList<>();
		
		Query query = new Query("SELECT * FROM "+sensorid+ " where time > "
				+ "now() - "+horas+"h", nameDB);
		//Query query = new Query("SELECT * FROM "+sensorid, "aTimeSeries");
		QueryResult queryResult=conn.query(query);
		List<Result> results = queryResult.getResults();
		System.out.println(results.size()+"------------");
			for(int i=0;i<results.size();i++){
				List<Series> series = results.get(i).getSeries();
				for(int j=0;j<series.size();j++){
					Series serie=series.get(j);
					columns=(ArrayList<String>) serie.getColumns();
					points=serie.getValues();
					for(int p=0; p<points.size(); p++){
						point= points.get(p);
						System.out.println(points.get(p));
						DataCollected d= new DataCollected();
						//insertamos el timeStamp
						d.setTimestamp(point.get(0));
						System.out.println(d.getTimestamp());
						for(int v=1; v<point.size();v++){
							d.getValues().put(columns.get(v), point.get(v));
							System.out.println(columns.get(v) +" : "+ point.get(v));
						}//values
						dataLog.getDataCollected().add(d);
					}//points
				}//series
				sensorLog.getDataLog().add(dataLog);
			}
			
	
		return sensorLog;	
	}
//	
//	/**
//	 * metodo que devuelve las serie de los sensores de tipo agua
//	 * con un formato normalizado en el que se recogen todos los
//	 * valores de los sensores de agua.
//	 * @param s
//	 * @return
//	 */
//	@Override
//	public SerieResult getSerierResultAGU(Series s){
//		SerieResult serie= new SerieResult();
//		serie.setColumnsSensorAGU();
//		serie.setName(s.getName());
//		System.out.println(s.getName());
//		//lista de  puntos de una serie
//		List<List<Object>> points=s.getValues();
//		for(int p=0;p<points.size();p++){
//			ArrayList<Object> point=new  ArrayList<>();
//			System.out.println(points.get(p));
//			List<Object> values=points.get(p);
//			if(s.getColumns().contains("counter2")){
//				//recorremos todos los valores en cada punto creado
//				for(int valor=0;valor<serie.getColumns().size();valor++){
//					point.add(values.get(valor));
//				}
//			}else{
//				int indice=0;
//				for(int valor=0;valor<serie.getColumns().size();valor++){
//					if(valor==serie.getColumns().indexOf("counter2")){
//						point.add(null);
//					}else{
//						point.add(values.get(indice));
//						indice++;
//					}
//					
//				}
//			}
//			serie.getValues().add(point);
//		}
//		return serie;	
//	}
//	
//	/**
//	 * metodo que devuelve las series  de forma normalizada
//	 * con el conjunto de valores de todos los sensores de tipo ele
//	 * @param s
//	 * @return
//	 */
//	
//	public SerieResult getSerierResult(Series s){
//		SerieResult serie= new SerieResult();
//		serie.setName(s.getName());
//		if(s.getName().contains("TH")){
//			serie.setColumnsSensorTH();
//		}else{
//			serie.setColumnsSensorELE();
//		}
//		System.out.println(serie.getColumns());
//		System.out.println(s.getName());
//		//lista de  puntos de una serie
//		List<List<Object>> points=s.getValues();
//			for(int p=0;p<points.size();p++){
//				ArrayList<Object> point=new  ArrayList<Object>();
//				System.out.println(points.get(p));
//				List<Object> values=points.get(p);
//				//recorremos todos los valores en cada punto creado
//				int indice=0;
//				for(int valor=0;valor<serie.getColumns().size();valor++){
//					if(s.getColumns().contains(serie.getColumns().get(valor))){
//							//condicion por si hay elementos repetidos
//							if(indice<s.getColumns().size())
//								point.add(values.get(indice));	
//						
//						System.out.println(serie.getColumns());
//						indice++;
//					}else{
//						point.add(null);
//						System.out.println(serie.getColumns());
//					}
//					
//				}
//				serie.getValues().add(point);
//			}
//		return serie;
//	}
//	
	
	/**
	 * Método que devuelve la temperatura media de las últimas 6horas
	 */
	public double getMedTempSensor(String sensorid, int horas){
		int posicion = 0;
		List<List<Object>> points = null;
		List<Result> results = null;
		List<Double> temps=new ArrayList<Double>();//temperaturas
		
		Query query = new Query("select * from "+ sensorid+ " where time > now() - "+horas+"h", nameDB);
		QueryResult queryResult=conn.query(query);
		//Obtenemos los resultados
		results=queryResult.getResults();
		System.out.println(results);
		for(int i=0; i<results.size();i++){
			System.out.println(results.get(i));
			//lista de series que solo hay una
			List<Series> series=results.get(i).getSeries();
			for(int j=0; j<series.size();j++){
				Series s= series.get(j);
				if(s.getName().contains("TH")){
					System.out.println(s);
					//Obtenemos la posicion que tendra en los puntos la variabkle temp
					posicion=s.getColumns().indexOf("temp");
					System.out.println(posicion);
					//lista de  puntos de una serie
					points=s.getValues();
					System.out.println(points);
					for(int p=0;p<points.size();p++){
						System.out.println(points.get(p));
						//lista de valores de un punto
						List<Object> values=points.get(p);
						Double tempPoint=(Double) values.get(posicion);
						//valor de la temperatura en un punto
						temps.add(tempPoint);
					}
					
				}
				
			}
		}
		double med=0, cont=0;
		int i=0;
		
		for(i=0;i<temps.size();i++){
			
			cont=cont+temps.get(i);
			System.out.println(temps.get(i));
		}
		if(cont>0)
			med=cont/i;
		System.out.println(cont);
		System.out.println(i);
		System.out.println(med);
		return med;
	}
	
	/**
	 * Método que devuelve la temperatura media general de los
	 * sensores que se pasan por parámetros en las últimas dos horas
	 */
	public double getMedTempSensores(ArrayList<String> sensoresid, int horas){
		List<Double> temps=new ArrayList<Double>();//temperaturas
		for(int i=0;i<sensoresid.size();i++){
			if(sensoresid.get(i).contains("TH")){
				temps.add(getMedTempSensor(sensoresid.get(i),horas));
			}
			
		}
		double med=0, cont=0;
		int i=0;
		System.out.println(temps.size()+"-------------");
		for(i=0;i<temps.size();i++){
			cont=cont+temps.get(i);
			System.out.println(temps.get(i));
		}
		if(cont>0)
			med=cont/i;
		System.out.println(cont);
		System.out.println(i);
		System.out.println(med);
		return med;
	}
	
	
	
}
