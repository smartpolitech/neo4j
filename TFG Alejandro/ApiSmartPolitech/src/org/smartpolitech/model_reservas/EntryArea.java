package org.smartpolitech.model_reservas;

public class EntryArea {
	String area_name;
	String room_name;
	int capacity;
	int start_time;
	int end_time;
	String name_reserva;
	String description_reserva;
	
	public String getArea_name() {return area_name;
	}
	public void setArea_name(String area_name) {this.area_name = area_name;
	}
	public String getRoom_name() {return room_name;
	}
	public void setRoom_name(String room_name) {this.room_name = room_name;
	}
	public int getCapacity() {return capacity;
	}
	public void setCapacity(int capacity) {this.capacity = capacity;
	}
	public int getStart_time() {return start_time;
	}
	public void setStart_time(int start_time) {this.start_time = start_time;
	}
	public int getEnd_time() {return end_time;
	}
	public void setEnd_time(int end_time) {this.end_time = end_time;
	}
	public String getName_reserva() {return name_reserva;
	}
	public void setName_reserva(String name_reserva) {this.name_reserva = name_reserva;
	}
	public String getDescription_reserva() {return description_reserva;
	}
	public void setDescription_reserva(String description_reserva) {
		this.description_reserva = description_reserva;
	}
}
