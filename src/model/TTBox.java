package model;

import java.sql.Time;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TTBox extends VBox{
	private String activity;
	private Time start;
	private Time end;
	private String colour;
	private String description;
	
	public TTBox()
	{
		this.activity = "";
		this.start = new Time(0);
		this.end = new Time(0);
		this.colour = "";
		this.description = "";
	}
	public TTBox(String activity, Time start, Time end)
	{
		this.activity = activity;
		this.start = start;
		this.end = end;
		this.colour = "FFFFFF";
		this.description = "No Description.";
		
		this.getChildren().add(new Text(activity));
		this.getChildren().add(new Text("Start: "+start.toString()));
		this.getChildren().add(new Text("End: "+end.toString()));
		this.getChildren().add(new Text("Description: "+description));
		this.setBackground(new Background(new BackgroundFill(
							Color.web(colour), 
							CornerRadii.EMPTY, 
							Insets.EMPTY)));
	}
	
	public TTBox(String activity, Time start, Time end, String colour)
	{
		this.activity = activity;
		this.start = start;
		this.end = end;
		this.colour = colour;
		this.description = "No Description.";
		
		this.getChildren().add(new Text(activity));
		this.getChildren().add(new Text("Start: "+start.toString()));
		this.getChildren().add(new Text("End: "+end.toString()));
		this.getChildren().add(new Text("Description: "+description));
		this.setBackground(new Background(new BackgroundFill(
							Color.web(colour), 
							CornerRadii.EMPTY, 
							Insets.EMPTY)));
	}
	
	public TTBox(String activity, Time start, Time end, String colour, String description)
	{
		this.activity = activity;
		this.start = start;
		this.end = end;
		this.colour = colour;
		this.description = description;
		
		this.getChildren().add(new Text(activity));
		this.getChildren().add(new Text("Start: "+start.toString()));
		this.getChildren().add(new Text("End: "+end.toString()));
		this.getChildren().add(new Text("Description: "+description));
		this.setBackground(new Background(new BackgroundFill(
							Color.web(colour), 
							CornerRadii.EMPTY, 
							Insets.EMPTY)));
	}
	public String getActivity()
	{
		return activity;
	}
	public Time getStart()
	{
		return start;
	}
	public Time getEnd()
	{
		return end;
	}
	public String getColour()
	{
		return colour;
	}
	public String description()
	{
		return description;
	}
	
	public void setActivity(String activity)
	{
		this.activity = activity;
	}
	public void setStart(Time start)
	{
		this.start = start;
	}
	public void setEnd(Time end)
	{
		this.end = end;
	}
	public void setColour(String colour)
	{
		this.colour = colour;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
}
