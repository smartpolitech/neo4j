package org.smartpolitech.model_reservas;

public class EntryRoom {
	
	String room_name;
	int capacity;
	int start_time;
	int end_time;
	String name_entry;
	
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getStart_time() {
		return start_time;
	}
	public void setStart_time(int start_time) {
		this.start_time = start_time;
	}
	public int getEnd_time() {
		return end_time;
	}
	public void setEnd_time(int end_time) {
		this.end_time = end_time;
	}
	public String getName_entry() {
		return name_entry;
	}
	public void setName_entry(String name_entry) {
		this.name_entry = name_entry;
	}
}
