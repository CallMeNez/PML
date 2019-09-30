package controllers;

import views.RootPane;
import javafx.event.*;
import javafx.scene.input.MouseEvent;

public class RootController
{
	private RootPane rp;
	private CalendarController cc;
	private TimetableController ttc;
	
	public RootController(RootPane rp)
	{
		//calendar controller
		this.cc = new CalendarController(rp.getCalendarView());
		this.ttc = new TimetableController(rp.getTimetableView());
		this.rp = rp;
		
		attachEventHandlers();
	}

	private void attachEventHandlers()
	{
		rp.setHomeHandler(new HomeHandler());
		rp.setCalendarHandler(new CalendarHandler());
		rp.setTimetableHandler(new TimetableHandler());
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
	
}
