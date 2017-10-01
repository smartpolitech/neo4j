package unex.epcc.lruiz.rooms.spatial;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unex.epcc.lruiz.devices.DevicesRepository;
import unex.epcc.lruiz.domain.Building;

@Service
@RequiredArgsConstructor
class SpatialService {

	private final SpatialRepository repository;

	private final RoomsRepository roomsRepository;
	
	private final DevicesRepository devicesRepository;

	List<Building> findBuildings(double latitude, double longitude, double distanceInKm) {
		return repository.findNodes("buildings", latitude, longitude, distanceInKm).stream()
				.map(this::buildBuildings)
				.collect(toList());
	}

	private Building buildBuildings(String buildingId) {
		return Building.builder()
				.id(buildingId)
				.rooms(roomsRepository.findRoomsByBuilding(buildingId))
				.devices(devicesRepository.findDevicesByBuilding(buildingId))
				.build();
	}
}
