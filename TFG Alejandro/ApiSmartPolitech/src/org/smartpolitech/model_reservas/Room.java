package org.smartpolitech.model_reservas;

public class Room {	
	 int  id;
	 int disabled;
	 int area_id;
	 String room_name;
	 String sort_key;
	 String description;
	 int capacity;
	 String room_admin_email;
	 String ustom_html;
	public int getId() {return id;
	}
	public void setId(int id) {this.id = id;
	}
	public int getDisabled() {return disabled;
	}
	public void setDisabled(int disabled) {this.disabled = disabled;
	}
	public int getArea_id() {return area_id;
	}
	public void setArea_id(int area_id) {this.area_id = area_id;
	}
	public String getRoom_name() {return room_name;
	}
	public void setRoom_name(String room_name) {this.room_name = room_name;
	}
	public String getSort_key() {return sort_key;
	}
	public void setSort_key(String sort_key) {this.sort_key = sort_key;
	}
	public String getDescription() {return description;
	}
	public void setDescription(String description) {this.description = description;
	}
	public int getCapacity() {return capacity;
	}
	public void setCapacity(int capacity) {this.capacity = capacity;
	}
	public String getRoom_admin_email() {return room_admin_email;
	}
	public void setRoom_admin_email(String room_admin_email) {this.room_admin_email = room_admin_email;
	}
	public String getUstom_html() {return ustom_html;
	}
	public void setUstom_html(String ustom_html) {this.ustom_html = ustom_html;
	}
}
