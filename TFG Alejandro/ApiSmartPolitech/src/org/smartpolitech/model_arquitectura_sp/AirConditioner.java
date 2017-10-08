package org.smartpolitech.model_arquitectura_sp;

import java.util.ArrayList;
/**
 * Clase que alamacena los datos estáticos de un aire acondicionado
 * @author alejandro
 *
 */
public class AirConditioner extends Device {
	String serialNumber;
	String weight;
	int power;
	int size;
	
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	
}
