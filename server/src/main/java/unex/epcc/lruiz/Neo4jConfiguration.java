package unex.epcc.lruiz;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Neo4jConfiguration {

	@Value("${database.url}")
	private String databaseUrl;
	
	@Value("${database.user}")
	private String databaseUser;
	
	@Value("${database.password}")
	private String databasePassword;
	
	@Bean
	public Session session() {
		return GraphDatabase.driver(databaseUrl, AuthTokens.basic(databaseUser, databasePassword)).session();
	}
}
