package model;



import java.time.LocalDate;
import java.util.ArrayList;
public class Day {
	private LocalDate date;

	public Day()
	{
		this.date = LocalDate.now();
	}
	public Day(LocalDate date)
	{
	this.date = date;
	}
	{
		this.date = date;
	}
	public void setDate(LocalDate date)
	{
		this.date = date;
	}
	
	public LocalDate getDate() 
	{
		return date;
	}
}
