package model;

import java.sql.Date;
import java.time.LocalDate;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import views.NewCalendarView;

public class DayBox extends AnchorPane
{
	private Day day;
	private int no;
	private Date workingDate;
	
	public DayBox()
	{
		day = new Day();
		workingDate = java.sql.Date.valueOf(day.getDate());
	}
	public DayBox(LocalDate date, Node...children)
	{
		super(children);
		day = new Day(date);
		workingDate = java.sql.Date.valueOf(date);
	}
	
	//Event Handlers
	public void setFocusHandler(EventHandler<MouseEvent> e)
	{
		this.setOnMouseEntered(e);
	}
	public void setFocusLostHandler(EventHandler<MouseEvent> e)
	{
		this.setOnMouseExited(e);
	}
	public void setClickHandler(EventHandler<MouseEvent> e)
	{
		this.setOnMouseClicked(e);
	}
	
	//sets and gets
	//day
	public void setDay(Day day)
	{
		this.day = day;
		no = day.getDate().getDayOfMonth();
	}
	public Day getDay()
	{
		return day;
	}
	
	//date
	public void setDate(Date date)
	{
		workingDate = date;
	}
	
	public Date getDate()
	{
		return workingDate;
	}
	
	public int getNo()
	{
		return no;
	}
}
