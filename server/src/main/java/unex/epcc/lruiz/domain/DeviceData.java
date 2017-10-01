package unex.epcc.lruiz.domain;

import java.util.List;

import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

@Value
@Builder
public class DeviceData {

	@Wither
	String description;
	
	List<GraphData> data;
	
	List<String> labels;
}
