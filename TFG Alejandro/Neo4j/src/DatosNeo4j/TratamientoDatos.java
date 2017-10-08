package DatosNeo4j;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import java.util.Map;

import org.neo4j.cypher.internal.javacompat.ExecutionEngine;
import org.neo4j.cypher.internal.javacompat.ExecutionResult;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.GraphDatabaseQueryService;
import org.neo4j.logging.LogProvider;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * 
 * 
 * @author Alejandro Corchado Bernal
 * En esta clase se gestiona todo lo relacionado con la importación
 * de los datos desde un archivo JSON a la base de datos orientada a grafos NEO4J,
 * en función de unas series de normas de transformación establecidas con anterioridad. 
 * 
 * Para ello usamos las librerias proporcionadas por java tano de JSON como de NEO4J
 *
 */
public class TratamientoDatos {
	
	/**Servicio de la base de datos */
	private GraphDatabaseService db;
	
	
	/**
	 * Tipos de nodos que representan edificio, planta y lugares.
	 * Solo habra un nodo de tipo BUILDING para cada vez que ejecutemos el programa.
	 */
	private enum nodesType implements Label {
		BUILDING,FLOOR,ROOM, DEVICE, SENSORLOG, DATACOLLECTED;
	}
	
	/**
	 * Tipos de relaciones
	 * La relación CONNECTOR solo podra conectar nodos de los tipos FLOOR
	 */
	private enum relationTypes implements RelationshipType{
		CONNECTOR,BELONG;
	}
	
	
	/**
	 * Devuelve la conexión a la base de datos
	 * @return
	 */
	public GraphDatabaseService getBaseDatos(){
		return db;
	}
	
	/**
	 * Método que devuelve el valor de la relación pasada por parámetro
	 * @param r
	 * @return
	 */
	public relationTypes getrelationType(String r){
		if(r.equalsIgnoreCase("belong"))
			return relationTypes.BELONG;
		else if(r.equalsIgnoreCase("connector"))
			return relationTypes.CONNECTOR;
		else
			return null;
	}
	
	/**
	 * Método que devuelve el valor del nodo pasa por parámetro.
	 * @param n
	 * @return
	 */
	public nodesType getNodesType(String n){
		if(n.equalsIgnoreCase("building"))
			return nodesType.BUILDING;
		else if(n.equalsIgnoreCase("floor"))
			return nodesType.FLOOR;
		else if(n.equalsIgnoreCase("room"))
			return nodesType.ROOM;
		else if(n.equalsIgnoreCase("device"))
			return nodesType.DEVICE;
		else if(n.equalsIgnoreCase("sensorlog"))
			return nodesType.SENSORLOG;
		else if(n.equalsIgnoreCase("datacollected"))
			return nodesType.DATACOLLECTED;
		else
			return null;
	}
	
	/**
	 * Método con el que conectamos con la base de datos.
	 */
	public void conexionBaseDate(String direction){
		GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
		db= dbFactory.newEmbeddedDatabase(new File(direction));	
	}
	
	
/**
 * Ejecutamos la importación de datos
 */
public static void main(String[] args) {

	String rutaBdNeo4j;
	rutaBdNeo4j=JOptionPane.showInputDialog("Introduzca la direccion donde deseas localizar la base de datos");
	
		String rutaJSON;		
		rutaJSON=JOptionPane.showInputDialog("Introduzca la direccion donde se localiza el JSON a procesar");	
		try {
			ImportacionDatos imp=new ImportacionDatos();
			imp.conexionBaseDate(rutaBdNeo4j);
			imp.exporDateJSON(rutaJSON);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   System.out.println("Done successfully");
		
	
}
}//fin clase
