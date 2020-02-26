package com.patharitech.timesheet.dao;

import com.patharitech.timesheet.co.TimesheetCO;

public interface TimesheetDAO {
	public boolean addTimesheet(TimesheetCO objTimesheetCO);
	public boolean deleteUser(TimesheetCO objTimesheetCO);
	public boolean updateTS(TimesheetCO objTimesheetCO);
	public TimesheetCO searchTimesheet(TimesheetCO timesheetCO);

}
