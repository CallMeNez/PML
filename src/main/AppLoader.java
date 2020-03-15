package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Day;
import views.HomeView;
import views.NewCalendarView;
import views.RootPane;
import views.NewTimetableView;
import views.TasksView;

import java.sql.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Hashtable;

import controllers.CalendarController;
import controllers.RootController;

public class AppLoader extends Application
{
	private static RootController rc;
	private static Connection conn;
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/";
	
	private static final String USER = "root";
	private static final String PASS = "";
	/**
	 * (in a try-catch)
	 * initialises rc with a new RootController, with parameters being new a
	 * HomeView, NewCalendarView(YearMonth.now), and TimetableView(WIP).
	 * A new RootController is then assigned rp.
	 */
	@Override
	public void init() 
	{
		try 
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		}
		catch(SQLException | ClassNotFoundException e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		try
		{
			rc = new RootController(new RootPane
				(
					new HomeView(), 
					new NewCalendarView(YearMonth.now()),
					new NewTimetableView(),
					new TasksView()
				), conn);
		}
		catch(RuntimeException e) 
		{
			e.printStackTrace();
			System.exit(0);
		}
	}
	/**
	 * builds a stage of 800 x 600 with the title "Plan My Life" and sets the scene to 
	 * rp, then shows the stage.
	 */
	@Override
	public void start(Stage stage) throws Exception
	{
		stage.setMinWidth(800);
		stage.setMinHeight(600);
		stage.setTitle("Plan My Life");
		stage.setScene(new Scene(rc.getRootPane()));
		stage.show();
	}
	public static void main(String[] args)
	{
		launch(args);
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rc.closeDB();
		System.out.println("Database closing ...");
	}
}