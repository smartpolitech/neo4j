package unex.epcc.lruiz.devices;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import unex.epcc.lruiz.devices.data.DeviceLogClient;
import unex.epcc.lruiz.devices.data.DeviceLogParser;
import unex.epcc.lruiz.domain.DeviceData;

@Service
@RequiredArgsConstructor
public class DevicesService {

	private final DeviceLogClient deviceLogClient;
	
	private final DeviceLogParser deviceLogParser;
	
	Optional<DeviceData> getDeviceData(String deviceId) {
		return deviceLogClient.retrieveDeviceData(deviceId).map(deviceLogParser::parse);
	}
}
