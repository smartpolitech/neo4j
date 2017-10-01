package unex.epcc.lruiz.rooms.spatial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class SpatialRepository {

	private final Session session;
	
	private static final String QUERY = "CALL spatial.withinDistance({layer}, {latitude: {lat}, longitude:{lon} }, {distanceInKm}) " + 
			"YIELD node AS n RETURN n.id as id;";
	
	public List<String> findNodes(String layer, double latitude, double longitude, double distanceInKm) {
		Map<String, Object> params = new HashMap<>();
		params.put("layer", layer);
		params.put("lat", latitude);
		params.put("lon", longitude);
		params.put("distanceInKm", distanceInKm);
		
		StatementResult result = session.run(QUERY, params);
		List<String> nodes = new ArrayList<>();
		result.forEachRemaining(record -> nodes.add(record.get("id").asString()));
		return nodes;
	}
}
