package org.smartpolitech.listener;


import java.sql.*;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;


/**
 * Application Lifecycle Listener implementation class ListenerContext
 *
 */
@WebListener
public class ListenerContext implements ServletContextListener {
		
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	ServletContext sc = event.getServletContext();
    	//-----------------INFLUXDB----------------------
    	//InfluxDB connectionInfluxDB=InfluxDBFactory.connect("http://localhost:8086", "root", "root");
    	InfluxDB connectionInfluxDB=InfluxDBFactory.connect("http://10.253.247.18:8086", "guest", "smartpolitech");
		sc.setAttribute("ConnInfluxDB", connectionInfluxDB);
		System.out.println("base de datos InfluxDB active");
			
		//----------------NEO4J-----------------------------
		Driver driver = GraphDatabase.driver ("bolt://localhost:7687",AuthTokens.basic( "alejandro", "insert789" ) );
		Session sessionNeo4j = driver.session();
		sc.setAttribute("ConnNeo4j", sessionNeo4j);
		System.out.println("base de datos Neo4j active");
		
		//---------------MYSQL-----------------------------
		Connection conexionMySql;
		try {
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				Class.forName ("com.mysql.jdbc.Driver");
				conexionMySql = DriverManager.getConnection("jdbc:mysql://iepcc.unex.es/r3s4l_13pcc_",
						"opr3s4lspt", "62iFtyrwAA2CwjR8");
				System.out.println("base de datos MySql active");
				sc.setAttribute("ConnMySql", conexionMySql);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    }
	

    /**
     * Default constructor. 
     */
    public ListenerContext() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	
	
}
