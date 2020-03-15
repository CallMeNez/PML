package controllers;

import views.NewCalendarView;
import model.DayBox;
import java.sql.Date;
import java.util.List;
import java.util.Observable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;

public class CalendarController 
{
	private NewCalendarView cv;
	private ObservableList<Date> workingDate = FXCollections.observableArrayList();
	
	public CalendarController(NewCalendarView cv)
	{
		this.cv = cv;
		workingDate.add(0, cv.getWorkingDate());
		attachEventHandlers();
	}
	public void setWorkingDateListener(ListChangeListener e)
	{
		workingDate.addListener(e);
	}

	private void attachEventHandlers() {
		cv.setLastMonthHandler(new LastMonthHandler());
		cv.setNextMonthHandler(new NextMonthHandler());
		cv.setBoxHandlers(new BoxesHandler());
	}
	
	private class LastMonthHandler implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent event) 
		{
			cv.lastMonth();
			cv.clearCalendar();
			cv.buildBoxes();
			cv.setBoxHandlers(new BoxesHandler());
			cv.buildTitle();
			cv.buildCalendar(cv.getWorkingDate());
		}
		
	}
	private class NextMonthHandler implements EventHandler<ActionEvent>
	{
		public void handle(ActionEvent e)
		{
			cv.nextMonth();
			cv.clearCalendar();
			cv.buildBoxes();
			cv.setBoxHandlers(new BoxesHandler());
			cv.buildTitle();
			cv.buildCalendar(cv.getWorkingDate());
		}
	}
	private class BoxesHandler implements EventHandler<MouseEvent>
	{
		public void handle(MouseEvent e)
		{
			DayBox db = (DayBox) e.getSource();
			cv.setWorkingDate(db.getDate());
			cv.clearCalendar();
			cv.buildBoxes();
			cv.setBoxHandlers(new BoxesHandler());
			cv.buildTitle();
			cv.buildCalendar(cv.getWorkingDate());
			cv.getWorkingDate();
			workingDate.set(0, cv.getWorkingDate());
		}
	}
	public Date getWorkingDate() {
		return workingDate.get(0);
	}
}
