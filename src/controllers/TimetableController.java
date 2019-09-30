package controllers;

import views.TimetableView;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TimetableController {
	private TimetableView tv;

	public TimetableController(TimetableView tv) {
		this.tv = tv;
		attachEventHandlers();
		
	}

	private void attachEventHandlers() {
		tv.setLastWeekHandler(new LastWeekHandler());
		tv.setNextWeekHandler(new NextWeekHandler());
		tv.setSetHandler(new SetHandler());
		tv.setClearHandler(new ClearHandler());
	}

	private class LastWeekHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			if(tv.getCurrentWeek() == 1)
			{
				tv.setCurrentWeek(tv.getWeeksUsedInt());
			}
			else
			{
				tv.setCurrentWeek(tv.getCurrentWeek()-1);
			}
		}
	}
	private class NextWeekHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			if(tv.getCurrentWeek() == tv.getWeeksUsedInt())
			{
				tv.setCurrentWeek(1);
			}
			else
			{
				tv.setCurrentWeek(tv.getCurrentWeek()+1);
			}
		}
	}
	private class SetHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			try {
				tv.getStmt().executeUpdate
				(
						"INSERT INTO "
				);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private class ClearHandler implements EventHandler<ActionEvent>
	{
		@Override
		public void handle(ActionEvent event)
		{
			
		}
	}
	
}
