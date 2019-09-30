package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Day;
import views.HomeView;
import views.NewCalendarView;
import views.RootPane;
import views.TimetableView;
import java.sql.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Hashtable;

import controllers.CalendarController;
import controllers.RootController;

public class AppLoader extends Application
{
	private static RootPane rp;
	private Connection conn;
	
	private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/";
	
	private static final String USER = "root";
	private static final String PASS = "";
	/**
	 * (in a try-catch)
	 * initialises rp with a new RootPane, with parameters being new a
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
			rp = new RootPane
				(
					new HomeView(), 
					new NewCalendarView(YearMonth.now()),
					new TimetableView(conn)
				);
			new RootController(rp);
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
		stage.setScene(new Scene(rp));
		stage.show();
	}
	public static void main(String[] args)
	{
		launch(args);
		rp.getTimetableView().closeJDBC();
		System.out.println("Database closing ...");
	}
}