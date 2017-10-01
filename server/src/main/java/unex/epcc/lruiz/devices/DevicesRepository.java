package unex.epcc.lruiz.devices;

import static java.util.Optional.empty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.neo4j.driver.v1.Record;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import unex.epcc.lruiz.domain.Device;
import unex.epcc.lruiz.domain.DeviceType;

@Repository
@RequiredArgsConstructor
public class DevicesRepository {

	private final Session session;
	
	private static final String FIND_DEVICE = "match (d:Device {id: {deviceId}}) "
			+ "return d.id as id, "
			+ "d.description as description, "
			+ "d.query as query, "
			+ "d.location as location, "
			+ "d.enabled as enabled, "
			+ "d.type as type;";
	
	private static final String FIND_DEVICES = "match (b:Building {id: {buildingId}})<-[]-(f:Floor)<-[]-(r:Room)<-[]-(d:Device) "
			+ "return d.id as id, "
			+ "d.description as description, "
			+ "d.query as query, "
			+ "d.location as location, "
			+ "d.enabled as enabled, "
			+ "d.type as type, "
			+ "f.id as floorId;";
	
	public Optional<Device> findDeviceById(String deviceId) {
		Map<String, Object> params = new HashMap<>();
		params.put("deviceId", deviceId);
		StatementResult result = session.run(FIND_DEVICE, params);
		return result.hasNext() ? Optional.of(buildDeviceFromRecord(result.single())) : empty();
	}
	
	public Set<Device> findDevicesByBuilding(String buildingId) {
		Map<String, Object> params = new HashMap<>();
		params.put("buildingId", buildingId);
		StatementResult result = session.run(FIND_DEVICES, params);
		Set<Device> devices = new HashSet<>();
		result.forEachRemaining(record -> {
			devices.add(buildDeviceFromRecord(record));
		});
		return devices;
	}
	
	private Device buildDeviceFromRecord(Record record) {
		return Device.builder()
				.id(record.get("id").asString())
				.floorId(record.get("floorId", ""))
				.description(record.get("description").asString())
				.query(record.get("query").asString())
				.location(record.get("location").asString())
				.enabled(record.get("enabled").asBoolean())
				.type(DeviceType.valueOf(record.get("type").asString()))
				.build();
	}
}
