package views;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import model.DayBox;

public class NewCalendarView extends VBox
{
	private YearMonth workMonth;
	private Button lastMonth;
	private Button nextMonth;
	private Text title;
	private GridPane calendar;
	private ArrayList<Text> labels;
	
/**
 * NewCalendarView builds the HBox at the top that holds the month buttons
 *  and work month and calls buildTitle, then builds the labels 
 *  list that holds the day names, 
 * and finally builds the calendar GridPane and calls buildCalendar.
 * @param YearMonth workMonth
 */
	public NewCalendarView(YearMonth workMonth) 
	{
		this.workMonth = workMonth;
		
		//build title and boxes
		title = new Text();
		lastMonth = new Button();
		nextMonth = new Button();
		buildTitle();
		HBox top = new HBox(lastMonth, title, nextMonth);
		top.setAlignment(Pos.BASELINE_CENTER);
		
		//build headers
		labels = new ArrayList<Text>();
		labels.add(new Text("Monday"));
		labels.add(new Text("Tuesday"));
		labels.add(new Text("Wednesday"));
		labels.add(new Text("Thursday"));
		labels.add(new Text("Friday"));
		labels.add(new Text("Saturday"));
		labels.add(new Text("Sunday"));
		
		//build Calendar
		calendar = new GridPane();
		buildCalendar();
		this.getChildren().addAll(top, calendar);
	}

/**
 * buildCalendar takes the labels Monday-Sunday and adds them to the top row of the calendar GridPane, then finds the Monday of the first week of the month (which can be in the month prior)
 * and builds DayBoxes for each date to build up the calendar on the GridPane using 2 for loops to decide the coordinates. They're set as invisible if they don't belong to the month shown,
 * but are still attached to the GridPane.
 */
	public void buildCalendar()
	{
		int x = 0;
		for(Text t : labels)
		{
			calendar.add(t, x, 0);
			x++;
		}
		LocalDate itdate = LocalDate.of(workMonth.getYear(), workMonth.getMonth(), 1);
		while(!itdate.getDayOfWeek().equals(DayOfWeek.MONDAY))
		{
			itdate = itdate.minusDays(1);
		}
		for (int i = 1; i<6; i++)
		{
			for (int j = 0; j<7; j++)
			{
				DayBox db = new DayBox(itdate);
				
				db.setMinSize(100, 100);
				db.setBorder
							(
								new Border
								(
									new BorderStroke
									(
										Color.BLACK, 
										BorderStrokeStyle.SOLID, 
										CornerRadii.EMPTY, 
										BorderWidths.DEFAULT
									)
								)
							);
				
				Text no = new Text(Integer.toString(itdate.getDayOfMonth()));
				db.getChildren().add(no);
				db.setLeftAnchor(no, 5.0);
				db.setTopAnchor(no, 5.0);
				if (itdate.getMonthValue() != workMonth.getMonthValue())
				{
					db.setVisible(false);
				}
				calendar.add(db, j, i);
				itdate = itdate.plusDays(1);
				
			}
		}
		
	}

/**
 * buildTitle sets the Month name at the top, along with the months in the buttons either side of it.
 */
	public void buildTitle()
	{
		title.setText(workMonth.getMonth().toString());
		lastMonth.setText(workMonth.getMonth().minus(1).toString());
		nextMonth.setText(workMonth.getMonth().plus(1).toString());
	}
/**
 * Sets the work month to the month prior.
 */
	public void lastMonth()
	{
		workMonth = workMonth.minusMonths(1);
	}
/**
 * Sets the work month to the next after.
 */
	public void nextMonth()
	{
		workMonth = workMonth.plusMonths(1);
	}
/**
 * Clears the calendar GridPane of all children.
 */
	public void clearCalendar()
	{
		calendar.getChildren().removeAll(calendar.getChildren());
	}
	//EHs
/**
 * Sets the Event Handler for the Last Month button at the top.	
 * @param EventHandler<ActionEvent>
 */
	public void setLastMonthHandler(EventHandler<ActionEvent> e)
	{
		lastMonth.setOnAction(e);
	}
/**
 * Sets the Event Handler for the Next Month button at the top.
 * @param EventHandler<ActionEvent>
 */
	public void setNextMonthHandler(EventHandler<ActionEvent> e)
	{
		nextMonth.setOnAction(e);
	}
}
