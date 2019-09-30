

import java.util.ArrayList;

import model.Day;

public class Timetable {
	private ArrayList<Day> weekA;
	private ArrayList<Day> weekB;
	private ArrayList<Day> weekC;
	private ArrayList<Day> weekD;

	public Timetable()
	{
		this.weekA = new ArrayList<Day>();
		this.weekB = new ArrayList<Day>();
		this.weekC = new ArrayList<Day>();
		this.weekD = new ArrayList<Day>();
	}
	
	public Timetable(ArrayList<Day> weekA)
	{
		this.weekA = weekA;
		this.weekB = new ArrayList<Day>();
		this.weekC = new ArrayList<Day>();
		this.weekD = new ArrayList<Day>();
	}
	public Timetable(ArrayList<Day> weekA, ArrayList<Day> weekB)
	{
		this.weekA = weekA;
		this.weekB = weekB;
		this.weekC = new ArrayList<Day>();
		this.weekD = new ArrayList<>();
	}
	public Timetable(ArrayList<Day> weekA,ArrayList<Day> weekB, ArrayList<Day> weekC)
	{
		this.weekA = weekA;
		this.weekB = weekB;
		this.weekC = weekC;
		this.weekD = new ArrayList<Day>();
	}
	public Timetable(ArrayList<Day> weekA, ArrayList<Day> weekB, ArrayList<Day> weekC, ArrayList<Day> weekD)
	{
		this.weekA = weekA;
		this.weekB = weekB;
		this.weekC = weekC;
		this.weekD = weekD;
	}
	
}
