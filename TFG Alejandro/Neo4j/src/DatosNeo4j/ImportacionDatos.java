package DatosNeo4j;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;

public class ImportacionDatos extends TratamientoDatos {
	
	
	/**
	 * Método con el que vamos extrayendo los datos del archivo JSON y según las
	 * normas y estructuras establecidas vamos importandolas en NEO4J.
	 */
	public void exporDateJSON(String directionJSON) throws ParseException{
		JSONParser parser = new JSONParser();
		//iniciamos transaccion en la base de datos.
		try(Transaction tx = getBaseDatos().beginTx();){			
			//Obtenemos el JSON del edificio en la variable jsonBuilding.
			Object obj = parser.parse(new FileReader(directionJSON));
			JSONObject jsonObject = (JSONObject) obj;
			//propiedades del nodo de tipo BUILDING
			Set keyJSON = jsonObject.keySet();
			//Si el JSON hace referencia a una estructura de edificio.
			if(!keyJSON.contains("deviceType")){
				String idBuilding= (String) jsonObject.get("_id");
				 //CONTROL:comrpobamos que el edificio a insertar 
				//no existe en la base de datos
				Result result = getBaseDatos().execute("MATCH (b:BUILDING {_id:\""+idBuilding+"\"}) RETURN b");
				System.out.println(result+"---------------------");
				//si el edificio no esta en la base de datos lo insertamos
				if(!result.hasNext()){
					estructraEdificio( jsonObject, keyJSON);
				}				
			//Si el JSON hace referencia a una estructura de dispositivo	
			}else{
				String idDevice= (String) jsonObject.get("_id");
				//CONTROL:comrpobamos que el dispositivo a insertar no existe en la base de datos
				Result result = getBaseDatos().execute("MATCH (d:DEVICE {_id:\""+idDevice+"\"}) RETURN d");
				System.out.println(result+"---------------------");
				//si el dispositivo no esta en la base de datos lo insertamos con la estructura dispositivo
				if(!result.hasNext()){
					estructuraDispositivo(jsonObject,keyJSON);
				}				
			}
			//Ejecutamos transacción
			tx.success();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * método que gestiona el tratamiento de los datos si la información a tratar es 
	 * de un edificio (BUILDING, FLOOR AND ROOM)
	 * @param jsonObject
	 * @param valoresJSON
	 */
	public void estructraEdificio(JSONObject jsonObject,Set keyJSON){	
		
		//creamos el nodo de tipo BUILDIMG (único que se creara para este archivo)
		Node building = getBaseDatos().createNode(getNodesType("building"));
		
		Iterator iteratorPropertyBuilding = keyJSON.iterator();
		while(iteratorPropertyBuilding.hasNext()) { 
		    String PropertyBuilding = (String) iteratorPropertyBuilding.next();
		    if(!(PropertyBuilding.equals("buildingData") || 
		    		PropertyBuilding.equals("buildingLocation"))){			    	
		    	System.out.println(PropertyBuilding);
		    	//Asingamos propiedades (nombre,valor) al nodo de tipo BUILDING
		    	building.setProperty(PropertyBuilding,jsonObject.get(PropertyBuilding));
		    	
		    }else if(PropertyBuilding.equals("buildingData")){
				    	//Obtenemos el Array de estructuras del edificio 
		    			//( nodos de tipo FLOOR o CONNECTOR)
		    			//arrays son []
				    	JSONArray structures = (JSONArray) jsonObject.get("buildingData");
				    	
				    	Iterator<JSONObject> iteratorstructures = structures.iterator();	    	 
						while (iteratorstructures.hasNext()) {		
							JSONObject jsonEstructure =iteratorstructures.next();
							System.out.println(jsonEstructure);
							
							//obtenemos los valores del jsonEstructure que son las 
							//propiedades de las estructuras					
							Set keySetStructure = jsonEstructure.keySet();
							
							//Si el JSON hace referencia a una estructura planta.
							if(keySetStructure.contains("floorID")){
								String idFloor= (String) jsonEstructure.get("floorID");
								//CONTROL:comrpobamos que la planta a insertar no existe en la base de datos
								Result result = getBaseDatos().execute("MATCH (f:FLOOR {floorID:\""+idFloor+"\"}) RETURN f");
								System.out.println(result+"---------------------");
								//si la planta no esta en la base de datos lo insertamos
								if(!result.hasNext()){
									estructuraPlanta(building,keySetStructure,jsonEstructure);
								}
							//Si el JSON hace referencia a una estructura connector.
							}else{
								String idConnector= (String) jsonEstructure.get("connectorID");
								//CONTROL:comrpobamos que el connector a insertar no existe en la base de datos
								Result result = getBaseDatos().execute("MATCH (f1:FLOOR )-[c:CONNECTOR {connectorID:\""+ idConnector+"\"}]->(f2:FLOOR) RETURN f1,f2");
								System.out.println(result+"---------------------");
								//si el conector no esta en la base de datos lo insertamos
								if(!result.hasNext()){
									estructuraConnector(building,keySetStructure,jsonEstructure);
								}
							}
						}//fin de estructuras
			    }//fin de la propiedad buildingData del nodo de tipo BUILDING
			}//fin del iterador iteratorPropertyBuilding 	
	}
	
	/**
	 * método que trata la informacion si esta hace referencia a una planta (FLOOR)
	 * @param building
	 * @param valoresStructure
	 * @param jsonEstructure
	 */
	public void estructuraPlanta(Node building,Set keySetStructure,JSONObject jsonEstructure){
		//creamos el nodo de tipo FLOOR
		Node  floor = getBaseDatos().createNode(getNodesType("floor"));
		
		//Creamos relación con el nodo de tipo BUILDING.
		floor.createRelationshipTo(building,getrelationType("belong"));	
		//Recorremos las propiedades del nodo de tipo FLOOR.
		Iterator iteratorPropertyEstructure = keySetStructure.iterator();
		while (iteratorPropertyEstructure.hasNext()) {
			String PropertyEstructure = (String) iteratorPropertyEstructure.next();			
			if(!PropertyEstructure.equals("rooms")){
				//Asignamos propiedad al nodo floor
				floor.setProperty(PropertyEstructure,jsonEstructure.get(PropertyEstructure));
				System.out.println(jsonEstructure.get("floorID").toString());
			}else{
				//Obtenemos el Array de rooms, nodos de tipo ROOM
		    	JSONArray rooms = (JSONArray) jsonEstructure.get("rooms");			
		    	Iterator iteratorRooms = rooms.iterator();
		    	while(iteratorRooms.hasNext()){
		    		JSONObject jsonRoom =(JSONObject) iteratorRooms.next();
		    		System.out.println(jsonRoom);
		    		
		    		//obtenemos los valores del jsonRoom que son las propiedades del nodo de tipo ROOM					
					Set keySetRoom = jsonRoom.keySet();	
					String idRoom= (String) jsonRoom.get("roomId");
					//CONTROL:comrpobamos que el connector a insertar no existe en la base de datos
					Result result = getBaseDatos().execute("MATCH (r:ROOM "
							+ "{roomId:\""+idRoom+"\"}) RETURN r");
					System.out.println(result+"---------------------");
					//si la habitacion no esta en la base de datos lo insertamos
					if(!result.hasNext()){
						estructuraRoom(jsonRoom, keySetRoom, floor);	
					}
		    	}//recorrido de las habitaciones de la planta				
			}//localizamos la propiedad rooms de la planta		
		}//recorrido de las propiedades de la planta
	}
	
	
	public void estructuraRoom(JSONObject jsonRoom, Set keySetRoom, Node  floor){
		//creamos los nodos de tipo ROOM
		Node  room = getBaseDatos().createNode(getNodesType("room"));					
		//Creamos relación con el nodo de tipo FLOOR al que pertenece.
		room.createRelationshipTo(floor,getrelationType("belong"));		
		//Recorremos las propiedades del nodo de tipo ROOM.
		Iterator iteratorPropertyRoom = keySetRoom.iterator();
		while(iteratorPropertyRoom.hasNext()){
			String propertyRoom = (String) iteratorPropertyRoom.next();						
			if(!(propertyRoom.equals("roomLocation") || propertyRoom.equals("deviceList"))){
				//Asignamos propiedad al nodo room
				room.setProperty(propertyRoom,jsonRoom.get(propertyRoom));									
				System.out.println(propertyRoom);
			}else{
				//CODIGO PARA GESTIONAR los arrays roomLocation,deviceList.
				if(propertyRoom.equals("deviceList") && jsonRoom.get("deviceList")!=null ){	
					JSONArray sensores = (JSONArray) jsonRoom.get("deviceList");	
			    	Iterator<JSONObject> iteratorsSensores = sensores.iterator();	    	 
					while (iteratorsSensores.hasNext()) {		
						JSONObject jsonSensor =iteratorsSensores.next();
						System.out.println(jsonSensor);
						//propiedades de las estructuras					
						Set keySetSensor = jsonSensor.keySet();
						String idSensor= (String) jsonSensor.get("id");
						//CONTROL:comrpobamos si el dispositivo a insertar no existe 
						Result resultSensor = getBaseDatos().execute("MATCH "
								+ "(s:DEVICE {id:\""+idSensor+"\"}) RETURN s");
						System.out.println(resultSensor+"---------------------");
						//si el dispositivo no esta en la base de datos lo insertamos 
						if(!resultSensor.hasNext()){
							itDeviceList(keySetSensor, jsonSensor, room);
						}	
					}
				}//si hay lista de algun dispositivo
			}//else 
		}//recorrido de las propiedades de una habitacion (ROOM)
	}
	
	
	
	
	
	/**
	 * método que se ejecuta cuando se debe crear un nodo de dispositivo 
	 * que todavia no se ha creado y esta introducido en el json que hace referencia 
	 * a un edificio
	 * 
	 * @param keySetSensor
	 * @param jsonSensor
	 * @param room
	 */
	public void itDeviceList(Set keySetSensor, JSONObject jsonSensor, Node  room ){
		//creamos los nodos de tipo DEVICE
		Node  sensor = getBaseDatos().createNode(getNodesType("device"));					
		//Creamos relación con el nodo de tipo ROOM al que pertenece.
		sensor.createRelationshipTo(room,getrelationType("belong"));

		//Recorremos las propiedades del nodo de tipo DEVICE.
		Iterator iteratorPropertySensor = keySetSensor.iterator();
		while(iteratorPropertySensor.hasNext()){
			String propertySensor = (String) iteratorPropertySensor.next();
			
			if(propertySensor.equals("Battery replacement")){
				JSONArray fechas = (JSONArray) jsonSensor.get("Battery replacement");
				String fecha="";
		    	Iterator<String> iteratorsFechas = fechas.iterator();	    	 
				while (iteratorsFechas.hasNext()) {		
					String i =iteratorsFechas.next();
					if(iteratorsFechas.hasNext())
						fecha=fecha+i+", ";
					else
						fecha=fecha+i;
				}
				sensor.setProperty(propertySensor,fecha);
			}else{
				//Asignamos propiedad al nodo device
				sensor.setProperty(propertySensor,jsonSensor.get(propertySensor));									
				System.out.println(propertySensor);
				
			}	
		}
	}
	
	/**
	 * metodo que trata la información si esta hace referencia a un conector
	 *  entre plantas (CONNECTOr)
	 * @param building
	 * @param valoresStructure
	 * @param jsonEstructure
	 */
	public void estructuraConnector(Node building,Set valoresStructure,JSONObject jsonEstructure){
		//creamos el nodo de tipo CONNECTOR
		Node  connector = getBaseDatos().createNode(getNodesType("connector"));
		
		//Recorremos las propiedades del nodo de tipo CONNECTOR.
		Iterator iteratorPropertyEstructure = valoresStructure.iterator();
		while (iteratorPropertyEstructure.hasNext()) {
		String PropertyEstructure = (String) iteratorPropertyEstructure.next();
			if(!(PropertyEstructure.equals("connectorLocation") || 
					PropertyEstructure.equals("floorsConnected"))){
				//Asignamos propiedad al nodo de tipo CONNECTOR
				connector.setProperty(PropertyEstructure,jsonEstructure.get(PropertyEstructure));
				System.out.println(PropertyEstructure);
				
			//gestion de los arrays connectorLocation y floorsConnected
			}else{
				JSONArray geopoint= (JSONArray) jsonEstructure.get("connectorLocation");
				
			}			
		}//recorremos las propiedades del connector
	}
	
	/**
	 * método que trata la información si esta hace referencia a un dispositivo (DEVICE)
	 * @param jsonObject
	 * @param valoresJSON
	 */
	public void estructuraDispositivo(JSONObject jsonObject,Set valoresJSON){
		//creamos el nodo de tipo BUILDIMG (único que se creara para este archivo)
		Node device = getBaseDatos().createNode(getNodesType("device"));	
		//Si no estamos ante un dispositivo de tipo sensor
		if(!jsonObject.get("deviceType").equals("sensor")){
			Iterator iteratorPropertyDevice = valoresJSON.iterator();
			while(iteratorPropertyDevice.hasNext()){ 
			    String PropertyDevice = (String) iteratorPropertyDevice.next();
			    if(!PropertyDevice.equals("location")){
			    	//Asingamos propiedades (nombre,valor) al nodo de tipo DEVICE
			    	device.setProperty(PropertyDevice,jsonObject.get(PropertyDevice));			    	
			    	//Buscamos el nodo de tipo ROOM para asignar una relación.		    	
			    	if(PropertyDevice.equals("roomID")){
			    		Result result =  getBaseDatos().execute("MATCH"
			    		+ "(r:ROOM {_id:\"" + jsonObject.get(PropertyDevice) + "\"}) RETURN r");
			    	 	//Obtenemos el nodo de tipo ROOM
						Map<String, Object> row = result.next(); 
						for ( String key : result.columns()){ 
							Node room =  (Node) row.get( key );
							System.out.println(room.getProperty("name"));							
							//Creamos relación con el nodo de tipo ROOM.
							device.createRelationshipTo(room,getrelationType("belong"));
						}					
			    	}			    	
			    }else{
			    	//codigo para gestionar la localizacion
			    }	    	
			}		
		}else{
			estructuraSensor(device,jsonObject,valoresJSON);
		}	
	}
	
	/**
	 * Metodo que trata la información si esta hacere referencia a un dispositivo de tipo
	 * sensor (SENSORLOG, DATACOLLECTED)
	 * @param device
	 * @param jsonObject
	 * @param valoresJSON
	 */
	public void estructuraSensor(Node device,JSONObject jsonObject,Set valoresJSON){
		Iterator iteratorPropertyDevice = valoresJSON.iterator();
		while(iteratorPropertyDevice.hasNext()){ 
		    String PropertyDevice = (String) iteratorPropertyDevice.next();					    
		    if(!PropertyDevice.equals("sensorLog")){
		    	device.setProperty(PropertyDevice,jsonObject.get(PropertyDevice));
		    }else{		    	
		    	JSONArray asensorlog = (JSONArray) jsonObject.get("sensorLog");		    	
		    	Iterator<JSONObject> iteratorsensorlog = asensorlog.iterator();	    	 
				while (iteratorsensorlog.hasNext()) {		
					JSONObject jsonSensorlog =iteratorsensorlog.next();
					System.out.println(iteratorsensorlog);										
					Set keySetSensorlog = jsonSensorlog.keySet();					
					Node  sensorlog = getBaseDatos().createNode(getNodesType("sensorlog"));					
					sensorlog.createRelationshipTo(device,getrelationType("belong"));							
					//Recorremos las propiedades del nodo de tipo SENSORLOG.
					Iterator iteratorPropertySensorlog = keySetSensorlog.iterator();
					while (iteratorPropertySensorlog.hasNext()) {
						String PropertySensorlog = (String) iteratorPropertySensorlog.next();					
						if(!(PropertySensorlog.equals("dataCollected") || PropertySensorlog.equals("location"))){							
							//Asingamos propiedades (nombre,valor) al nodo de tipo DEVICE
					    	sensorlog.setProperty(PropertySensorlog,jsonObject.get(PropertySensorlog));				    	
					    	//Obtenemos al nodo de tipo ROOM al que pertenece y creamos la relación
					    	if(PropertySensorlog.equals("roomID")){
					    		Result result =  getBaseDatos().execute
					    		("MATCH (r:ROOM {_id:\"" + jsonObject.get(PropertySensorlog) + "\"}) RETURN r");
								Map<String, Object> row = result.next(); 
								for ( String key : result.columns()){ 
									Node room =  (Node) row.get( key );
									System.out.println(room.getProperty("name"));								
									//Creamos relación con el nodo de tipo ROOM.
									sensorlog.createRelationshipTo(room,getrelationType("belong"));
								}    	
					    	}
						}else if(PropertySensorlog.equals("dataCollected")){							
							estructureDatacollected(sensorlog,jsonSensorlog);						
						}else if(PropertySensorlog.equals("location")){
							//codigo para gestionar location
						}
					}//rrecorrido de las propiedades de tipo Sensor
				}//recorrido de objetos de tipo sensor
		    }//else para identificar la propiedad sensorlog		
		}//recorremos las propiedades de un sensor.	
	}
	
	public void estructureDatacollected(Node  sensorlog,JSONObject jsonSensorlog){
		//Obtenemos el Array de dataCollected ( nodos de tipo DATACOLLECTED)
    	JSONArray aDataCollected = (JSONArray) jsonSensorlog.get("dataCollected");					    	
    	Iterator<JSONObject> iteratorDataCollected = aDataCollected.iterator();	    	 
		while (iteratorDataCollected.hasNext()) {		
			JSONObject jsonDatacollected =iteratorDataCollected.next();
			System.out.println(jsonDatacollected);							
			//obtenemos los valores del jsonDatacollected que son las propiedades
			//de los nodos de tipo DATACOLLECTED					
			Set keySetDatacollected = jsonDatacollected.keySet();	
			
			//creamos el nodo de tipo DATACOLLECTED
			Node  datacollected = getBaseDatos().createNode(getNodesType("datacollected"));
			
			//Creamos relación con el nodo de tipo SENSORLOG al que pertenece.
			datacollected.createRelationshipTo(sensorlog,getrelationType("belong"));
			
			//Recorremos las propiedades del nodo de tipo DATACOLLECTED.
			Iterator iteratorPropiedadesDataCollected =  keySetDatacollected.iterator();
			while (iteratorPropiedadesDataCollected.hasNext()) {
				
				String PropertyDatacollected = (String) iteratorPropiedadesDataCollected.next();					
				if(!PropertyDatacollected.equals("values")){							
					//Asingamos propiedades (nombre,valor) al nodo de tipo DEVICE
					datacollected.setProperty(PropertyDatacollected,jsonDatacollected
					.get(PropertyDatacollected));

				}else{
					JSONArray aValores = (JSONArray) jsonDatacollected.get("values");
					Iterator<String> iteratorValues = aValores.iterator();
					int i=0;
					while (iteratorValues.hasNext()) {
						datacollected.setProperty("value"+i,iteratorValues.next());
						i++;
					}
					
				}
			}
		}
	}
	
	

}
