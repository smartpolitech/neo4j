package unex.epcc.lruiz.domain;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GraphData {

	String label;
	
	List<String> data;
}
