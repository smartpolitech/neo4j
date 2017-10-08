package org.smartpolitech.model_sensores;

import java.util.LinkedHashMap;

public class DataCollected {
	
	String timestamp;
	LinkedHashMap<String, Object> values;
	
	public DataCollected(){
		values= new LinkedHashMap<String, Object>();
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Object object) {
		this.timestamp = (String) object;
	}

	public LinkedHashMap<String, Object> getValues() {
		return values;
	}

	public void setValues(LinkedHashMap<String, Object> values) {
		this.values = values;
	}
	

}
