package unex.epcc.lruiz.devices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import unex.epcc.lruiz.domain.DeviceData;

@RestController
@RequiredArgsConstructor
public class DevicesController {

	private final DevicesService devicesService;

	@GetMapping("/smart-politech/api/devices/{deviceId}/data")
	public DeviceData getDeviceData(@PathVariable String deviceId) {
		return devicesService.getDeviceData(deviceId).orElseThrow(DeviceNotFoundException::new);
	}
}
