package unex.epcc.lruiz.domain;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Device {

	 String id;
	
	 String floorId;
	 
	 String description;
	
	 String query;
	
	 String location;
	
	 List<String> batteryReplacement;
	
	 Boolean enabled;
	
	 DeviceType type;
	 
	 
}
