package views;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
public class RootPane extends BorderPane 
{
	private HomeView home;
	private NewCalendarView calendar;
	private NewTimetableView timetable;
	private TasksView tasks;
	private VBox side;
	private AnchorPane main;
	private AnchorPane homeBox;
	private AnchorPane calBox;
	private AnchorPane ttBox;
	private AnchorPane taBox;
	
	/**
	 * WIP, needs additions to it with each module added later.
	 * builds a side VBox for each of the module tiles in it on the left,
	 * and an anchor pane as each of the module tiles.
	 * These panes get action events assigned by the controller.
	 * and a main section for where the module view will display if its tile is
	 * clicked on.
	 * All views used as parameters are assigned to corresponding private variables.
	 * @param home
	 * @param calendar
	 * @param timetable
	 * @param tasksView 
	 */
	public RootPane(HomeView home, NewCalendarView calendar, NewTimetableView timetable, TasksView tasks)
	{
		//build side
		side = new VBox();
		homeBox = new AnchorPane();
		calBox = new AnchorPane();
		ttBox = new AnchorPane();
		taBox = new AnchorPane();
		
		Text homeName = new Text("Home");
		homeName.setTextAlignment(TextAlignment.CENTER);
		homeBox.getChildren().add(homeName);
		homeBox.setTopAnchor(homeName, 5.0);
		homeBox.setLeftAnchor(homeName, 5.0);
		homeBox.setRightAnchor(homeName, 5.0);
		homeBox.setBottomAnchor(homeName, 5.0);
		homeBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		Text calName = new Text("Calendar");
		calName.setTextAlignment(TextAlignment.CENTER);
		calBox.getChildren().add(calName);
		calBox.setTopAnchor(calName, 5.0);
		calBox.setLeftAnchor(calName, 5.0);
		calBox.setRightAnchor(calName, 5.0);
		calBox.setBottomAnchor(calName, 5.0);
		calBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		Text ttName = new Text("Timetable");
		ttName.setTextAlignment(TextAlignment.CENTER);
		ttBox.getChildren().add(ttName);
		ttBox.setTopAnchor(ttName, 5.0);
		ttBox.setLeftAnchor(ttName, 5.0);
		ttBox.setRightAnchor(ttName, 5.0);
		ttBox.setBottomAnchor(ttName, 5.0);
		ttBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		Text taName = new Text("Tasks");
		taName.setTextAlignment(TextAlignment.CENTER);
		taBox.getChildren().add(taName);
		taBox.setTopAnchor(taName, 5.0);
		taBox.setLeftAnchor(taName, 5.0);
		taBox.setRightAnchor(taName, 5.0);
		taBox.setBottomAnchor(taName, 5.0);
		taBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		
		
		side.getChildren().addAll(homeBox, calBox, ttBox, taBox);
		this.setLeft(side);
		
		//main
		main = new AnchorPane();
		this.setCenter(main);
		
		//set views
		this.home = home;
		this.calendar = calendar;
		this.timetable = timetable;
		this.tasks = tasks;
		
		setMainHome();
	}
	/**
	 * returns HomeView
	 * @return home
	 */
	public HomeView getHomeView()
	{
		return home;
	}
	/**
	 * returns NewCalendarView
	 * @return calendar
	 */
	public NewCalendarView getCalendarView()
	{
		return calendar;
	}
	/**
	 * returns TimetableView
	 * @return timetable
	 */
	public NewTimetableView getTimetableView()
	{
		return timetable;
	}
	/**
	 * returns TasksView
	 * @return tasks
	 */
	public TasksView getTasksView() {
		return tasks;
	}
	/**
	 * sets the main pane to the HomeView
	 */
	public void setMainHome()
	{
		main.getChildren().clear();
		main.getChildren().add(home);
		main.setTopAnchor(home, 5.0);
		main.setLeftAnchor(home, 5.0);
		main.setRightAnchor(home, 5.0);
		main.setBottomAnchor(home, 5.0);

	}
	/**
	 * sets the main pane to the CalendarView
	 */
	public void setMainCalendar()
	{
		main.getChildren().clear();
		main.getChildren().add(calendar);
		main.setTopAnchor(calendar, 5.0);
		main.setLeftAnchor(calendar, 5.0);
		main.setRightAnchor(calendar, 5.0);
		main.setBottomAnchor(calendar, 5.0);

	}
	/**
	 * sets the main pane to the TimetableView
	 */
	
	public void setMainTimetable()
	{
		main.getChildren().clear();
		main.getChildren().add(timetable);
		main.setTopAnchor(timetable, 5.0);
		main.setLeftAnchor(timetable, 5.0);
		main.setRightAnchor(timetable, 5.0);
		main.setBottomAnchor(timetable, 5.0);
	}
	/**
	 * sets the main pane to the TasksView
	 */
	public void setMainTasks()
	{
		main.getChildren().clear();
		main.getChildren().add(tasks);
		main.setTopAnchor(tasks, 5.0);
		main.setLeftAnchor(tasks, 5.0);
		main.setRightAnchor(tasks, 5.0);
		main.setBottomAnchor(tasks, 5.0);
	}
	/**
	 * adds a MouseEvent to the Home tile
	 * @param e
	 */
	public void setHomeHandler(EventHandler<MouseEvent> e)
	{
		homeBox.setOnMouseClicked(e);
	}
	/**
	 * adds a MouseEvent to the Calendar tile
	 * @param e
	 */
	public void setCalendarHandler(EventHandler<MouseEvent> e)
	{
		calBox.setOnMouseClicked(e);
	}
	/**
	 * adds a MouseEvent to the Timetable tile
	 * @param e
	 */
	public void setTimetableHandler(EventHandler<MouseEvent> e)
	{
		ttBox.setOnMouseClicked(e);
	}
	public void setTasksHandler(EventHandler<MouseEvent> e)
	{
		taBox.setOnMouseClicked(e);
	}
}
