package controllers;

import views.NewCalendarView;
import javafx.event.*;

public class CalendarController 
{
	private NewCalendarView cv;
	
	public CalendarController(NewCalendarView cv)
	{
		this.cv = cv;
		attachEventHandlers();
	}

	private void attachEventHandlers() {
		cv.setLastMonthHandler(new LastMonthHandler());
		cv.setNextMonthHandler(new NextMonthHandler());
	}
	
	private class LastMonthHandler implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent event) 
		{
			cv.lastMonth();
			cv.clearCalendar();
			cv.buildTitle();
			cv.buildCalendar();
		}
		
	}
	private class NextMonthHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			cv.nextMonth();
			cv.clearCalendar();
			cv.buildTitle();
			cv.buildCalendar();
		}
	}
}
