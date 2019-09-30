package model;

import java.time.LocalTime;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Block extends StackPane{
	private String activity;
	private String details;
	private LocalTime start;
	private LocalTime end;
	private Color colour;
	
	public Block()
	{
		this.activity = "";
		this.start = LocalTime.now();
		this.end = LocalTime.now();
		this.details = "";
		
		this.setMinSize(50, (end.toSecondOfDay()-start.toSecondOfDay())/10);
	}
	public Block(LocalTime start, LocalTime end, String activity, String colour) 
	{
		this.activity = activity;
		this.start = start;
		this.end = end;
		this.colour = Color.valueOf(colour);
		details = "";
	}
	public Block(LocalTime start, LocalTime end, String activity, String colour, String details)
	{
		this.activity = activity;
		this.start = start;
		this.end = end;
		this.colour = Color.valueOf(colour);
		this.details = details;
	}
	//sets & gets
	public void setActivity(String activity)
	{
		this.activity = activity;
	}
	public void setStart(LocalTime start)
	{
		this.start = start;
	}
	public void setEnd(LocalTime end)
	{
		this.end = end;
	}
	public void setColour(String colour)
	{
		this.colour = Color.valueOf(colour);
	}
	public void setDetails(String details)
	{
		this.details = details;
	}
	
	public String getActivity()
	{
		return activity;
	}
	public LocalTime getStart()
	{
		return start;
	}
	public LocalTime getEnd()
	{
		return end;
	}
	public Color getColour()
	{
		return colour;
	}
	public String getDetails()
	{
		return details;
	}
}
