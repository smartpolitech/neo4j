package unex.epcc.lruiz.devices.data;

import java.util.List;

import lombok.Value;

@Value
public class Serie {

	String name;
	List<String> columns;
	List<List<String>> values;
}
