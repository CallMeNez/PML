package controllers;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.prism.paint.Color;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.TTBox;
import views.NewTimetableView;

public class TimetableController {
	private NewTimetableView tv;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private HashMap colourCodes;
	private Date workingDate;
	private TTBox highlighted;
	
	public TimetableController(NewTimetableView tv, Connection conn, Date workingDate) {
		this.tv = tv;
		this.conn = conn;
		this.workingDate = workingDate;
		highlighted = new TTBox();
		attachEventHandlers();
		
		HashMap<String, String> colourCodes = new HashMap<String, String>();
		colourCodes.put("White",  "FFFFFF");
		colourCodes.put("Red", "FF0000");
		colourCodes.put("Orange", "FFA500");
		colourCodes.put("Yellow", "FFFF00");
		colourCodes.put("Green", "008000");
		colourCodes.put("Cyan", "00FFFF");
		colourCodes.put("Blue", "0000FF");
		colourCodes.put("Purple", "800080");
		colourCodes.put("Pink", "FFC0CB");
		colourCodes.put("Grey", "808080");
		update(workingDate);
	}

	private void attachEventHandlers() {
		tv.setSetHandler(new SetHandler());
		tv.setClearHandler(new ClearHandler());
		tv.setRemoveHandler(new RemoveHandler());
	}
	private boolean checkTime(Time start, Time end)
	{
		try 
		{
			rs.first();
			if(rs.next())
			{
				rs.first();
				while(rs.next())
				{
					if((start.after(rs.getTime("activityStart")) && start.before(rs.getTime("activityEnd"))))
					{
						return false;
					}
					if((end.after(rs.getTime("activityStart")) && end.before(rs.getTime("activityEnd"))))
					{
						return false;
					}
					if((start.equals(rs.getTime("activityStart")) || end.equals(rs.getTime("activityEnd"))))
					{
						return false;
					}
					if((start.before(rs.getTime("activityStart")) && end.after(rs.getTime("activityEnd"))))
					{
						return false;
					}
				}
			}
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return false;
		}
	}
	public NewTimetableView getTimetableView() {
		return tv;
	}
	public void update(Date workingDate) {
		ArrayList<TTBox> boxes = new ArrayList<TTBox>();
		this.workingDate = workingDate;
		try {
		stmt = conn.createStatement();
		stmt.execute("use pml;");
		rs = stmt.executeQuery(
				"SELECT activityStart, activityEnd, activityTitle, activityColour, activityDescription FROM timetable WHERE activityDate = '"
				+workingDate+"';");
		while(rs.next())
		{
			TTBox ttb = new TTBox(
					rs.getString("activityTitle"),
					rs.getTime("activityStart"), 
					rs.getTime("activityEnd"),
					rs.getString("activityColour"),
					rs.getString("activityDescription"));
			ttb.setOnMouseClicked(new HighlightHandler());
			boxes.add(ttb);
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		tv.update(boxes);
	}
	public void closeDB()
	{
		try {
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	
//	private boolean timeCheck(ResultSet rs, Time startTime, Time endTime)
//	{
//		try {
//		while(rs.next())
//		{
//			//start time or end time is equal to or between existing start time or end time FALSE, else true
//			if((startTime.after(rs.getTime("activityStart")) && startTime.before(rs.getTime("activityEnd")))
//				)
//		}
//			if(rs.getTime("activityStart").equals(startTime)
//			rs.getTime("activityEnd");
//		}
//		return false;
//	}
	private class SetHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			String activity = tv.getActivity();
			Time startTime = Time.valueOf(tv.getStartHour()+":"+tv.getStartMin()+":00");
			Time endTime = Time.valueOf(tv.getEndHour()+":"+tv.getEndMin()+":00");
			String colour = tv.getColour();
			String description = tv.getDescription();
			
			if(highlighted.equals(new TTBox()))
			{
				if(checkTime(startTime, endTime))
				{
				try {
					stmt.executeUpdate("INSERT INTO timetable (activityTitle, activityDescription, activityColour, activityStart, activityEnd, activityDate)"
							+ " VALUES ('"+activity+"', '"+description+"', '"+colour+"', '"+startTime+"', '"+endTime+"', '"+workingDate+"');");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				update(workingDate);
				};
			}
			else
			{
				try
				{
					stmt.executeUpdate("UPDATE timetable SET "
							+ "activityTitle = '"+activity+"', "
							+ "activityDescription = '"+description+"', "
							+ "activityColour = '"+colour+"', "
							+ "activityStart = '"+startTime+"', "
							+ "activityEnd = '"+endTime+"', "
							+ "activityDate = '"+workingDate+"' "
							+ "WHERE activityStart = '"+highlighted.getStart()+"';");
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
				update(workingDate);
			}
			
		}
	}
	private class ClearHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			tv.setActivity("");
			tv.setStartHour(0);
			tv.setStartMin(0);
			tv.setColour("White");
		}
	}
	private class RemoveHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			if(highlighted.equals(new TTBox()))
			{
			}
			else
			{
				try {
					stmt.executeUpdate("DELETE FROM timetable WHERE "
							+ "activityStart = '"+highlighted.getStart()+"';");
					update(workingDate);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	private class HighlightHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent e)
		{
			if(highlighted.equals(e.getSource()))
			{
				highlighted = new TTBox();
				Alert boxUnselected = new Alert(AlertType.INFORMATION);
				boxUnselected.setTitle("Timetable Slot Unselected");
				boxUnselected.setHeaderText(highlighted.getActivity());
				boxUnselected.setContentText("Tasks may now be added as usual.");
				boxUnselected.showAndWait();
			}
			else
			{
				highlighted = (TTBox) e.getSource();
				Alert boxSelected = new Alert(AlertType.INFORMATION);
				boxSelected.setTitle("Timetable Slot Selected");
				boxSelected.setHeaderText(highlighted.getActivity());
				boxSelected.setContentText("Selected task may now be edited.");
				boxSelected.showAndWait();
			}
		}
	}
}
