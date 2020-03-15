package controllers;

import views.RootPane;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.*;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;

public class RootController
{
	private RootPane rp;
	private CalendarController cc;
	private TimetableController ttc;
	private TasksController tac;
	private Date workingDate;
	private Connection conn;
	
	public RootController(RootPane rp, Connection conn)
	{
		//calendar controller
		this.rp = rp;
		this.cc = new CalendarController(rp.getCalendarView());
		workingDate = cc.getWorkingDate();
		this.ttc = new TimetableController(rp.getTimetableView(), conn, workingDate);
		this.tac = new TasksController(rp.getTasksView(), conn, workingDate);
		
		attachEventHandlers();
	}

	private void attachEventHandlers()
	{
		rp.setHomeHandler(new HomeHandler());
		rp.setCalendarHandler(new CalendarHandler());
		rp.setTimetableHandler(new TimetableHandler());
		rp.setTasksHandler(new TasksHandler());
		cc.setWorkingDateListener(new workingDateListener());
	}
	public void closeDB()
	{
		if(conn != null)
		{
			try {
				conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		ttc.closeDB();
//		tac.closeDB();
	}
	public RootPane getRootPane() {
		return rp;
	}
	//Event Handlers
	private class HomeHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent e)
		{
			rp.setMainHome();
		}
	}
	private class CalendarHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent e)
		{
			rp.setMainCalendar();
		}
	}
	
	private class TimetableHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent e)
		{
			rp.setMainTimetable();
		}
	}
	private class TasksHandler implements EventHandler<MouseEvent>
	{
		@Override
		public void handle(MouseEvent e)
		{
			rp.setMainTasks();
		}
	}
	private class workingDateListener implements ListChangeListener
	{
		@Override
		public void onChanged(Change c) 
		{
			workingDate = cc.getWorkingDate();
			update();
		}

		private void update() 
		{
			ttc.update(workingDate);
			tac.update(workingDate);
		}
		
	}
}
