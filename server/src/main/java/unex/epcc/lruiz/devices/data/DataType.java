package unex.epcc.lruiz.devices.data;

import lombok.Getter;

@Getter
public enum DataType {
	time("Time"),
	temp("Temperatura ºC"),
	hum("Humedad %"),
	counter("Consumo Agua"),
	counter1("Consumo Agua"),
	Power1("Consumo eléctrico");
	
	private final String label;
	
	private DataType(String label) {
		this.label = label;
	}
}
