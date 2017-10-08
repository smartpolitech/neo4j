package org.smartpolitech.model_sensores;

import java.util.ArrayList;

public class DataLog {
	
	ArrayList<DataCollected> dataCollected;
	
	public DataLog(){
		dataCollected=new ArrayList<DataCollected>();
	}

	public ArrayList<DataCollected> getDataCollected() {
		return dataCollected;
	}

	public void setDataCollected(ArrayList<DataCollected> dataCollected) {
		this.dataCollected = dataCollected;
	}

}
