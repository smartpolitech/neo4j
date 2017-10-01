package unex.epcc.lruiz.rooms.spatial;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import unex.epcc.lruiz.domain.Building;

@RestController
@RequiredArgsConstructor
public class SpatialController {

	private final SpatialService service;
	
	@GetMapping("/smart-politech/api/spatial/buildings")
	public List<Building> getBuildingElements(
			@RequestParam("lat") double latitude, 
			@RequestParam("lon") double longitude,
			@RequestParam("distanceInKm") double distanceInKm) {
		
		return service.findBuildings(latitude, longitude, distanceInKm);
	}
}
