//
//
//import java.sql.*;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Text;
//import model.Block;
//
//public class TimetableView extends BorderPane{
//	
//	//create box
//	private Button set;
//	private Button clear;
//	private TextField activity;
//	private ComboBox<Integer> startHour;
//	private ComboBox<Integer> startMin;
//	private ComboBox<Integer> endHour;
//	private ComboBox<Integer> endMin;
//	private ComboBox<String> colour;
//	
//	private ComboBox<Integer> repeatInterval;
//	private ComboBox<String> repeatTime;
//	private ComboBox<Integer> repeatEndDateDay;
//	private ComboBox<Integer> repeatEndDateMonth;
//	private ComboBox<Integer> repeatEndDateYear;
//
//	private TextField details;
//	
//	//main
//	private GridPane ttGrid;
//	
//	public TimetableView()
//	{
//		ObservableList<String> days = FXCollections.observableArrayList();
//		
//		ObservableList<String> colours = FXCollections.observableArrayList();
//		colours.add("Red");
//		colours.add("Orange");
//		colours.add("Yellow");
//		colours.add("Green");
//		colours.add("Cyan");
//		colours.add("Blue");
//		colours.add("Purple");
//		colours.add("Pink");
//		colours.add("Grey");
//		
//		HashMap<String, String> colourCodes = new HashMap<String, String>();
//		colourCodes.put("Red", "FF0000");
//		colourCodes.put("Orange", "FFA500");
//		colourCodes.put("Yellow", "FFFF00");
//		colourCodes.put("Green", "008000");
//		colourCodes.put("Cyan", "00FFFF");
//		colourCodes.put("Blue", "0000FF");
//		colourCodes.put("Purple", "800080");
//		colourCodes.put("Pink", "FFC0CB");
//		colourCodes.put("Grey", "808080");
//		
//		colour = new ComboBox<String>();
//		colour.setItems(colours);
//		set = new Button("Set");
//		clear = new Button("Clear");
//		activity = new TextField();
//		activity.setPromptText("Activity");
//		
//		ObservableList<Integer> hours  = FXCollections.observableArrayList();
//		for (int i = 0; i<24; i++)
//		{
//			hours.add(i);
//		}
//		
//		startHour = new ComboBox<Integer>();
//		startHour.setItems(hours);
//		endHour = new ComboBox<Integer>();
//		endHour.setItems(hours);
//		
//		ObservableList<Integer> mins = FXCollections.observableArrayList();
//		for (int i = 0; i<60; i = i+5)
//		{
//			mins.add(i);
//		}
//		
//		startMin = new ComboBox<Integer>();
//		startMin.setItems(mins);
//		endMin = new ComboBox<Integer>();
//		endMin.setItems(mins);
//		
//		HBox startTimeBox = new HBox(new Label("Start Time:"), startHour, startMin);
//		HBox endTimeBox = new HBox(new Label("End Time:"), endHour, endMin);
//		
//		details = new TextField();
//		weeksUsedBox = new ComboBox<Integer>();
//		ObservableList<Integer> weeksUsed = FXCollections.observableArrayList();
//		weeksUsed.addAll(1, 2, 3, 4);
//		weeksUsedBox.setItems(weeksUsed);
//		weeksUsedBox.getSelectionModel().select(weeksUsedInt-1);
//		
//		//VBox side = new VBox(weekBox, activity, day, startTimeBox, endTimeBox, colour, details, set, clear, weeksUsedBox);
//		//this.setLeft(side);
//		
//		//build grid
//		ttGrid = new GridPane();
//		this.setCenter(ttGrid);
//		Label[] labels = 
//			{
//				new Label("Mon"), 
//				new Label("Tue"), 
//				new Label("Wed"), 
//				new Label("Thu"), 
//				new Label("Fri"), 
//				new Label("Sat"), 
//				new Label("Sun")
//			};
//		int i = 0;
//		this.labels = new ArrayList<String>();
//		for (Label l : labels)
//		{
//			this.labels.add(l.getText());
//			ttGrid.add(l, i, 0);
//			i++;
//		}
//		buildTimetable(weeks[currentWeek-1].replace(' ', '_'));
//	}
//	public void buildTimetable(String week) {
//		try 
//		{
//			if(stmt.execute("SHOW DATABASES WHERE `database` LIKE '"+week+"';") != false)
//			{
//				stmt.executeQuery("USE "+week+";");
//			}
//			else
//			{
//				stmt.executeUpdate("CREATE DATABASE "+week+";");
//				stmt.executeQuery("USE "+week+";");
//			}
//		} 
//		catch (SQLException e1) 
//		{
//			e1.printStackTrace();
//		}
//		for(int x = 0; x<7; x++)
//		{
//			int ttRowCount = 1;
//			try {
//				if(stmt.execute("SHOW TABLES WHERE `table` LIKE '"+labels.get(x)+"';") != false)
//				{
//					ResultSet rs = stmt.executeQuery("SELECT * FROM "+labels.get(x)+";");
//					if (rs.last())
//					{
//						rs.beforeFirst();
//					}
//					while (rs.next()) 
//					{
//						if(rs.getString(5) != null)
//						{
//							Block block = new Block
//							(
//								rs.getTime(1).toLocalTime(),
//								rs.getTime(2).toLocalTime(),
//								rs.getString(3),
//								rs.getString(4),
//								rs.getString(5)
//							);
//							ttGrid.add(block, x, ttRowCount);
//						}
//						else
//						{
//							Block block = new Block
//									(
//										rs.getTime(1).toLocalTime(),
//										rs.getTime(2).toLocalTime(),
//										rs.getString(3),
//										rs.getString(4)
//									);
//							ttGrid.add(block, x, ttRowCount);
//						}
//						
//					}
//				}
//				else
//				{
//					stmt.executeUpdate
//					(
//							"CREATE TABLE "+labels.get(x)+" ( "
//							+ "startTime TIME NOT NULL, "
//							+ "endTime TIME NOT NULL, "
//							+ "activity VARCHAR(40) NOT NULL, "
//							+ "colour VARCHAR(7) NOT NULL, "
//							+ "details VARCHAR(280), "
//							+ "CONSTRAINT "+labels.get(x)
//							+"_PK PRIMARY KEY(startTime, endTime) );"
//					);
//				}
//			}
//			catch (SQLException e) 
//			{
//				e.printStackTrace();
//				try 
//				{
//					stmt.executeUpdate
//					(
//							"CREATE TABLE "+labels.get(x)+" ( "
//							+ "startTime TIME NOT NULL, "
//							+ "endTime TIME NOT NULL, "
//							+ "activity VARCHAR(40) NOT NULL, "
//							+ "colour VARCHAR(7) NOT NULL, "
//							+ "details VARCHAR(280), "
//							+ "CONSTRAINT "+labels.get(x)
//							+"_PK PRIMARY KEY(startTime, endTime) );"
//					);
//				}
//				catch (SQLException e1) 
//				{
//					e1.printStackTrace();
//				}
//			}
//		}
//	}
//	//EHs
//	public void setLastWeekHandler(EventHandler<ActionEvent> e)
//	{
//		lastWeek.setOnAction(e);
//	}
//	public void setNextWeekHandler(EventHandler<ActionEvent> e)
//	{
//		nextWeek.setOnAction(e);
//	}
//	public void setSetHandler(EventHandler<ActionEvent> e)
//	{
//		set.setOnAction(e);
//	}
//	public void setClearHandler(EventHandler<ActionEvent> e)
//	{
//		clear.setOnAction(e);
//	}
//	public void setWeeksUsedHandler(EventHandler<ActionEvent> e)
//	{
//		weeksUsedBox.setOnAction(e);
//	}
//	//get infos
//	public Connection getConn()
//	{
//		return conn;
//	}
//	public Statement getStmt()
//	{
//		return stmt;
//	}
//	public int getWeeksUsedInt()
//	{
//		return weeksUsedInt;
//	}
//	public int getCurrentWeek()
//	{
//		return currentWeek;
//	}
//	public String getDay()
//	{
//		return day.getValue();
//	}
//	public String getActivity()
//	{
//		return activity.getText();
//	}
//	public LocalTime getStartTime()
//	{
//		return LocalTime.of(startHour.getValue(), startMin.getValue());
//	}
//	public LocalTime getEndTime()
//	{
//		return LocalTime.of(endHour.getValue(), endMin.getValue());
//	}
//	public String getDetails()
//	{
//		return details.getText();
//	}
//	public void getWeekDatabase(String week)
//	{
//		try
//		{
//			Statement stmt = conn.createStatement();
//			stmt.executeQuery("USE "+week+ ";");
//			stmt.close();
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//	}
//	public ResultSet getDayData(LocalDate date)
//	{
//		try 
//		{
//			Statement stmt = conn.createStatement();
//			ResultSet temp = stmt.executeQuery("SELECT * FROM "+date.toString()+";");
//			stmt.close();
//			return temp;
//		}
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//			return null;
//		}
//	}
//	//sets
//	public void setWeeksUsedInt(int weeksUsedInt)
//	{
//		this.weeksUsedInt = weeksUsedInt;
//	}
//	public void setCurrentWeek(int currentWeek) 
//	{
//		this.currentWeek = currentWeek;
//	}
//	public void update(Date workingDate) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//}