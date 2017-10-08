package org.smartpolitech.model_reservas;

public class Area {	
	 int id;
	 int disabled;
	 String area_name;
	 String timezone;
	 String area_admin_email;
	 int resolution;
	 int default_duration;
	 int default_duration_all_day;
	 int morningstarts;
	 int morningstarts_minutes;
	 int eveningends;
	 int eveningends_minutes;
	 int private_enabled;
	 int private_default;
	 int private_mandatory;
	 String private_override;
	 int min_book_ahead_enabled;
	 int min_book_ahead_secs;
	 int max_book_ahead_enabled;
	 int max_book_ahead_secs;
	 int max_per_day_enabled;
	 int max_per_day;
	 int max_per_week_enabled;
	 int max_per_week;
	 int max_per_month_enabled;
	 int max_per_month;
	 int max_per_year_enabled;
	 int max_per_year;
	 int max_per_future_enabled;
	 int max_per_future;
	 String custom_html;
	 int approval_enabled;
	 int reminders_enabled;
	 int enable_periods;
	 int confirmation_enabled;
	 int confirmed_default;
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public int getDisabled() {return disabled;}
	public void setDisabled(int disabled) {this.disabled = disabled;}
	public String getArea_name() {return area_name;}
	public void setArea_name(String area_name) {this.area_name = area_name;}
	public String getTimezone() {return timezone;}
	public void setTimezone(String timezone) {this.timezone = timezone;}
	public String getArea_admin_email() {return area_admin_email;}
	public void setArea_admin_email(String area_admin_email) {this.area_admin_email = area_admin_email;}
	public int getResolution() {return resolution;}
	public void setResolution(int resolution) {this.resolution = resolution;}
	public int getDefault_duration() {return default_duration;}
	public void setDefault_duration(int default_duration) {this.default_duration = default_duration;}
	public int getDefault_duration_all_day() {return default_duration_all_day;}
	public void setDefault_duration_all_day(int default_duration_all_day) {this.default_duration_all_day = default_duration_all_day;}
	public int getMorningstarts() {return morningstarts;}
	public void setMorningstarts(int morningstarts) {this.morningstarts = morningstarts;}
	public int getMorningstarts_minutes() {return morningstarts_minutes;}
	public void setMorningstarts_minutes(int morningstarts_minutes) {this.morningstarts_minutes = morningstarts_minutes;}
	public int getEveningends() {return eveningends;}
	public void setEveningends(int eveningends) {this.eveningends = eveningends;}
	public int getEveningends_minutes() {return eveningends_minutes;}
	public void setEveningends_minutes(int eveningends_minutes) {this.eveningends_minutes = eveningends_minutes;}
	public int getPrivate_enabled() {return private_enabled;}
	public void setPrivate_enabled(int private_enabled) {this.private_enabled = private_enabled;}
	public int getPrivate_default() {return private_default;}
	public void setPrivate_default(int private_default) {this.private_default = private_default;}
	public int getPrivate_mandatory() {return private_mandatory;}
	public void setPrivate_mandatory(int private_mandatory) {this.private_mandatory = private_mandatory;}
	public String getPrivate_override() {return private_override;}
	public void setPrivate_override(String private_override) {this.private_override = private_override;}
	public int getMin_book_ahead_enabled() {return min_book_ahead_enabled;}
	public void setMin_book_ahead_enabled(int min_book_ahead_enabled) {this.min_book_ahead_enabled = min_book_ahead_enabled;}
	public int getMin_book_ahead_secs() {	return min_book_ahead_secs;}
	public void setMin_book_ahead_secs(int min_book_ahead_secs) {this.min_book_ahead_secs = min_book_ahead_secs;}
	public int getMax_book_ahead_enabled() {return max_book_ahead_enabled;}
	public void setMax_book_ahead_enabled(int max_book_ahead_enabled) {this.max_book_ahead_enabled = max_book_ahead_enabled;}
	public int getMax_book_ahead_secs() {return max_book_ahead_secs;}
	public void setMax_book_ahead_secs(int max_book_ahead_secs) {this.max_book_ahead_secs = max_book_ahead_secs;}
	public int getMax_per_day_enabled() {return max_per_day_enabled;}
	public void setMax_per_day_enabled(int max_per_day_enabled) {this.max_per_day_enabled = max_per_day_enabled;}
	public int getMax_per_day() {return max_per_day;}
	public void setMax_per_day(int max_per_day) {this.max_per_day = max_per_day;}
	public int getMax_per_week_enabled() {return max_per_week_enabled;}
	public void setMax_per_week_enabled(int max_per_week_enabled) {this.max_per_week_enabled = max_per_week_enabled;}
	
	public int getMax_per_week() {return max_per_week;}
	public void setMax_per_week(int max_per_week) {this.max_per_week = max_per_week;}
	public int getMax_per_month_enabled() {return max_per_month_enabled;}
	public void setMax_per_month_enabled(int max_per_month_enabled) {this.max_per_month_enabled = max_per_month_enabled;}
	public int getMax_per_month() {return max_per_month;}
	public void setMax_per_month(int max_per_month) {this.max_per_month = max_per_month;}
	public int getMax_per_year_enabled() {return max_per_year_enabled;}
	public void setMax_per_year_enabled(int max_per_year_enabled) {this.max_per_year_enabled = max_per_year_enabled;}
	public int getMax_per_year() {return max_per_year;}
	public void setMax_per_year(int max_per_year) {this.max_per_year = max_per_year;}
	public int getMax_per_future_enabled() {return max_per_future_enabled;}
	public void setMax_per_future_enabled(int max_per_future_enabled) {this.max_per_future_enabled = max_per_future_enabled;}
	public int getMax_per_future() {return max_per_future;}
	public void setMax_per_future(int max_per_future) {this.max_per_future = max_per_future;}
	public String getCustom_html() {return custom_html;}
	public void setCustom_html(String custom_html) {this.custom_html = custom_html;}
	public int getApproval_enabled() {return approval_enabled;}
	public void setApproval_enabled(int approval_enabled) {this.approval_enabled = approval_enabled;}
	public int getReminders_enabled() {return reminders_enabled;}
	public void setReminders_enabled(int reminders_enabled) {this.reminders_enabled = reminders_enabled;}
	public int getEnable_periods() {return enable_periods;}
	public void setEnable_periods(int enable_periods) {this.enable_periods = enable_periods;}
	public int getConfirmation_enabled() {return confirmation_enabled;}
	public void setConfirmation_enabled(int confirmation_enabled) {this.confirmation_enabled = confirmation_enabled;}
	public int getConfirmed_default() {return confirmed_default;}
	public void setConfirmed_default(int confirmed_default) {this.confirmed_default = confirmed_default;}	
}
