package model;


import java.time.LocalDate;
import java.util.ArrayList;

public class Day {
	private LocalDate date;
	private ArrayList<Block> schedule;

	public Day()
	{
		this.date = LocalDate.now();
		this.setSchedule(new ArrayList<Block>());
	}
	public Day(LocalDate date)
	{
	this.date = date;
	this.setSchedule(new ArrayList<Block>()); 
	}
	public Day(LocalDate date, ArrayList<Block> schedule)
	{
		this.date = date;
		this.schedule = schedule;
	}
	public void setDate(LocalDate date)
	{
		this.date = date;
	}
	
	public LocalDate getDate() 
	{
		return date;
	}
	public ArrayList<Block> getSchedule() {
		return schedule;
	}
	public void setSchedule(ArrayList<Block> schedule) {
		this.schedule = schedule;
	}
}
