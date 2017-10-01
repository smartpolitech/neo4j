package unex.epcc.lruiz.domain;

import java.util.Set;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Building {

	String id;
	
	Set<Room> rooms;
	
	Set<Device> devices;

}
