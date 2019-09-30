package model;

import java.time.LocalDate;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DayBox extends AnchorPane
{
	private Day day;
	private int no;
	
	public DayBox(LocalDate date, Node...children)
	{
		super(children);
		day = new Day(date);
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
	public void setDate(LocalDate date)
	{
		day.setDate(date);
		no = date.getDayOfMonth();
	}
	
	public LocalDate getDate()
	{
		return day.getDate();
	}
	public int getNo()
	{
		return no;
	}
	
}
