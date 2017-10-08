package org.smartpolitech.model_reservas;

import java.sql.Timestamp;


public class Entry {
	 int id; 
	 int start_time;
	 int end_time;
	 int entry_type;
	 int repeat_id;
	 int room_id;
	 Timestamp timestamp;
	 String create_by;
	 String name;
	 String type;
	 String description;
	 int status;
	 int reminded;
	 int info_time;
	 String info_user;
	 String info_text;
	 String ical_uid;
	 int ical_sequence;
	 String ical_recur_id;
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getStart_time() {return start_time;}
	public void setStart_time(int start_time) {this.start_time = start_time;}
	public int getEnd_time() {return end_time;}
	public void setEnd_time(int end_time) {this.end_time = end_time;}
	public int getEntry_type() {return entry_type;}
	public void setEntry_type(int entry_type) {this.entry_type = entry_type;}
	public int getRepeat_id() {return repeat_id;}
	public void setRepeat_id(int repeat_id) {this.repeat_id = repeat_id;}
	public int getRoom_id() {return room_id;}
	public void setRoom_id(int room_id) {this.room_id = room_id;}
	public Timestamp getTimestamp() {return timestamp;}
	public void setTimestamp(Timestamp timestamp) {this.timestamp = timestamp;}
	public String getCreate_by() {return create_by;}
	public void setCreate_by(String create_by) {this.create_by = create_by;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getStatus() {return status;}
	public void setStatus(int status) {this.status = status;}
	public int getReminded() {return reminded;}
	public void setReminded(int reminded) {this.reminded = reminded;}
	public int getInfo_time() {return info_time;}
	public void setInfo_time(int info_time) {this.info_time = info_time;}
	public String getInfo_user() {return info_user;}
	public void setInfo_user(String info_user) {this.info_user = info_user;}
	public String getInfo_text() {return info_text;}
	public void setInfo_text(String info_text) {this.info_text = info_text;}
	public String getIcal_uid() {return ical_uid;}
	public void setIcal_uid(String ical_uid) {this.ical_uid = ical_uid;}
	public int getIcal_sequence() {return ical_sequence;}
	public void setIcal_sequence(int ical_sequence) {this.ical_sequence = ical_sequence;}
	public String getIcal_recur_id() {return ical_recur_id;}
	public void setIcal_recur_id(String ical_recur_id) {this.ical_recur_id = ical_recur_id;}	 
}
	
