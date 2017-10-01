package unex.epcc.lruiz.devices.data;

import static java.util.Optional.empty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DeviceLogClient {

	private ObjectMapper mapper = new ObjectMapper();
	
	public Optional<Results> retrieveDeviceData(String deviceId) {
		try {
			InputStream is = getClass().getResourceAsStream("/devicesFakeData/" + deviceId+".json");
			
			return Optional.of(mapper.readValue(is, Results.class));
		} catch (RuntimeException | IOException e) {
			log.error("Error reading device data", e);
			return empty();
		}
	}
}
