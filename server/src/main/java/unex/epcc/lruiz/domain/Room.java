package unex.epcc.lruiz.domain;

import java.util.Set;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
@Wither
public class Room {
	
	 String id;
	 
	 String floorId;
	 
	 String type;
	
	 String location;
	
	 String name;
	
	 String area;
	
	 Set<Device> devices;
}