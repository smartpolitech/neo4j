package unex.epcc.lruiz.rooms.spatial;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import unex.epcc.lruiz.domain.Room;

@Repository
@RequiredArgsConstructor
class RoomsRepository {

	private final Session session;
	
	private static final String FIND_ROOMS = "match(b:Building {id: {buildingId}})<-[]-(f:Floor)<-[]-(r:Room) "
			+ "return r.id as id, "
			+ "r.type as type, "
			+ "r.name as name, "
			+ "r.area as area, "
			+ "r.location as location, "
			+ "f.id as floorId";
	
	public Set<Room> findRoomsByBuilding(String buildingId) {
		Map<String, Object> params = new HashMap<>();
		params.put("buildingId", buildingId);
		StatementResult result = session.run(FIND_ROOMS, params);
		Set<Room> rooms = new HashSet<>();
		result.forEachRemaining(record -> {
			rooms.add(buildRoomFromRecord(record));
		});
		return rooms;
	}
	
	private Room buildRoomFromRecord(Record record) {
		return Room.builder()
				.id(record.get("id").asString())
				.type(record.get("type").asString())
				.name(record.get("name").asString())
				.area(record.get("area").asString())
				.location(record.get("location").asString())
				.floorId(record.get("floorId").asString())
				.build();
	}
}
