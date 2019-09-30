package views;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Block;

public class TimetableView extends BorderPane{
	
	private Connection conn;
	private Statement stmt;

	
	private int weeksUsedInt;
	private int currentWeek;
	private ComboBox<Integer> weeksUsedBox;
	//week box
	private String[] weeks;
	private ArrayList<String> labels;
	private Button lastWeek;
	private Button nextWeek;
	private Text week;
	
	//create box
	private ComboBox<String> day;
	private Button set;
	private Button clear;
	private TextField activity;
	private ComboBox<Integer> startHour;
	private ComboBox<Integer> startMin;
	private ComboBox<Integer> endHour;
	private ComboBox<Integer> endMin;
	private ComboBox<String> colour;
	private TextField details;
	
	//main
	private GridPane ttGrid;
	
	public TimetableView(Connection conn)
	{
		//needs replacing
		weeksUsedInt = 1;
		currentWeek = 1;
		
		//JDBC
		this.conn = conn;
		try 
		{
			stmt = this.conn.createStatement();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		//build side
		
		weeks = new String[4];
		weeks[0] = "Week A";
		weeks[1] = "Week B";
		weeks[2] = "Week C";
		weeks[3] = "Week D";
		
		
		week = new Text(weeks[0]);
		lastWeek = new Button("Last Week");
		nextWeek = new Button("Next Week");
		HBox weekBox = new HBox(lastWeek, week, nextWeek);
		
		ObservableList<String> days = FXCollections.observableArrayList();
		days.add("Mon");
		days.add("Tue");
		days.add("Wed");
		days.add("Thu");
		days.add("Fri");
		days.add("Sat");
		days.add("Sun");

		day = new ComboBox<String>();
		day.setItems(days);
		
		ObservableList<String> colours = FXCollections.observableArrayList();
		colours.add("Red");
		colours.add("Orange");
		colours.add("Yellow");
		colours.add("Green");
		colours.add("Cyan");
		colours.add("Blue");
		colours.add("Purple");
		colours.add("Pink");
		colours.add("Grey");
		
		colour = new ComboBox<String>();
		colour.setItems(colours);
		set = new Button("Set");
		clear = new Button("Clear");
		activity = new TextField();
		activity.setPromptText("Activity");
		
		ObservableList<Integer> hours  = FXCollections.observableArrayList();
		for (int i = 0; i<24; i++)
		{
			hours.add(i);
		}
		
		startHour = new ComboBox<Integer>();
		startHour.setItems(hours);
		endHour = new ComboBox<Integer>();
		endHour.setItems(hours);
		
		ObservableList<Integer> mins = FXCollections.observableArrayList();
		for (int i = 0; i<60; i = i+5)
		{
			mins.add(i);
		}
		
		startMin = new ComboBox<Integer>();
		startMin.setItems(mins);
		endMin = new ComboBox<Integer>();
		endMin.setItems(mins);
		
		HBox startTimeBox = new HBox(new Label("Start Time:"), startHour, startMin);
		HBox endTimeBox = new HBox(new Label("End Time:"), endHour, endMin);
		
		details = new TextField();
		weeksUsedBox = new ComboBox<Integer>();
		ObservableList<Integer> weeksUsed = FXCollections.observableArrayList();
		weeksUsed.addAll(1, 2, 3, 4);
		weeksUsedBox.setItems(weeksUsed);
		weeksUsedBox.getSelectionModel().select(weeksUsedInt-1);
		
		VBox side = new VBox(weekBox, activity, day, startTimeBox, endTimeBox, colour, details, set, clear, weeksUsedBox);
		this.setLeft(side);
		
		//build grid
		ttGrid = new GridPane();
		this.setCenter(ttGrid);
		Label[] labels = 
			{
				new Label("Mon"), 
				new Label("Tue"), 
				new Label("Wed"), 
				new Label("Thu"), 
				new Label("Fri"), 
				new Label("Sat"), 
				new Label("Sun")
			};
		int i = 0;
		this.labels = new ArrayList<String>();
		for (Label l : labels)
		{
			this.labels.add(l.getText());
			ttGrid.add(l, i, 0);
			i++;
		}
		buildTimetable(weeks[currentWeek-1].replace(' ', '_'));
	}
	public void buildTimetable(String week) {
		try 
		{
			if(stmt.execute("SHOW DATABASES WHERE `database` LIKE '"+week+"';") != false)
			{
				stmt.executeQuery("USE "+week+";");
			}
			else
			{
				stmt.executeUpdate("CREATE DATABASE "+week+";");
				stmt.executeQuery("USE "+week+";");
			}
		} 
		catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		for(int x = 0; x<7; x++)
		{
			int ttRowCount = 1;
			try {
				if(stmt.execute("SELECT * FROM "+labels.get(x)+";") != false)
				{
					ResultSet rs = stmt.executeQuery("SELECT * FROM "+labels.get(x)+";");
					if (rs.last())
					{
						rs.beforeFirst();
					}
					while (rs.next()) 
					{
						if(rs.getString(5) != null)
						{
							Block block = new Block
							(
								rs.getTime(1).toLocalTime(),
								rs.getTime(2).toLocalTime(),
								rs.getString(3),
								rs.getString(4),
								rs.getString(5)
							);
							ttGrid.add(block, x, ttRowCount);
						}
						else
						{
							Block block = new Block
									(
										rs.getTime(1).toLocalTime(),
										rs.getTime(2).toLocalTime(),
										rs.getString(3),
										rs.getString(4)
									);
							ttGrid.add(block, x, ttRowCount);
						}
						
					}
				}
				else
				{
					stmt.executeUpdate
					(
							"CREATE TABLE "+labels.get(x)+" ( "
							+ "startTime TIME NOT NULL, "
							+ "endTime TIME NOT NULL, "
							+ "activity VARCHAR(40) NOT NULL, "
							+ "colour VARCHAR(7) NOT NULL, "
							+ "details VARCHAR(280), "
							+ "CONSTRAINT "+labels.get(x)
							+"_PK PRIMARY KEY(startTime, endTime) );"
					);
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
				try 
				{
					stmt.executeUpdate
					(
							"CREATE TABLE "+labels.get(x)+" ( "
							+ "startTime TIME NOT NULL, "
							+ "endTime TIME NOT NULL, "
							+ "activity VARCHAR(40) NOT NULL, "
							+ "colour VARCHAR(7) NOT NULL, "
							+ "details VARCHAR(280), "
							+ "CONSTRAINT "+labels.get(x)
							+"_PK PRIMARY KEY(startTime, endTime) );"
					);
				}
				catch (SQLException e1) 
				{
					e1.printStackTrace();
				}
			}
		}
	}
	public void buildWeekBox()
	{
		for(int i = 0; i<4; i++)
		{
			if(weeks[3].equals(week.getText()))
			{
				week.setText(weeks[0]);
			}
			else if (weeks[i].equals(week.getText()))
			{
				week.setText(weeks[i+1]);
				i = 5;
			}
		}
		
	}
	public void closeJDBC()
	{
		try 
		{
			stmt.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			conn.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	//EHs
	public void setLastWeekHandler(EventHandler<ActionEvent> e)
	{
		lastWeek.setOnAction(e);
	}
	public void setNextWeekHandler(EventHandler<ActionEvent> e)
	{
		nextWeek.setOnAction(e);
	}
	public void setSetHandler(EventHandler<ActionEvent> e)
	{
		set.setOnAction(e);
	}
	public void setClearHandler(EventHandler<ActionEvent> e)
	{
		clear.setOnAction(e);
	}
	public void setWeeksUsedHandler(EventHandler<ActionEvent> e)
	{
		weeksUsedBox.setOnAction(e);
	}
	//get infos
	public Connection getConn()
	{
		return conn;
	}
	public Statement getStmt()
	{
		return stmt;
	}
	public int getWeeksUsedInt()
	{
		return weeksUsedInt;
	}
	public int getCurrentWeek()
	{
		return currentWeek;
	}
	public String getDay()
	{
		return day.getValue();
	}
	public String getActivity()
	{
		return activity.getText();
	}
	public LocalTime getStartTime()
	{
		return LocalTime.of(startHour.getValue(), startMin.getValue());
	}
	public LocalTime getEndTime()
	{
		return LocalTime.of(endHour.getValue(), endMin.getValue());
	}
	public String getDetails()
	{
		return details.getText();
	}
	public void getWeekDatabase(String week)
	{
		try
		{
			Statement stmt = conn.createStatement();
			stmt.executeQuery("USE "+week+ ";");
			stmt.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public ResultSet getDayData(LocalDate date)
	{
		try 
		{
			Statement stmt = conn.createStatement();
			ResultSet temp = stmt.executeQuery("SELECT * FROM "+date.toString()+";");
			stmt.close();
			return temp;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	//sets
	public void setWeeksUsedInt(int weeksUsedInt)
	{
		this.weeksUsedInt = weeksUsedInt;
	}
	public void setCurrentWeek(int currentWeek) 
	{
		this.currentWeek = currentWeek;
	}
	
}