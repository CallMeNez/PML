/* package dumpster;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

public class CalendarView extends VBox
{
	
	private ArrayList<DayBox> calendarBoxes;
	private YearMonth yearMonth;
	private LocalDate date;
	private Button lastMonth;
	private Button nextMonth;
	private GridPane calendar;
	
	public CalendarView(YearMonth yearMonth)
	{
		//make calendar grid pane
		calendarBoxes = new ArrayList<>(35);
		this.yearMonth = yearMonth;
		date = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
		calendar = new GridPane();
		
		for (int i = 0; i < 5; i++) 
		{
            for (int j = 0; j < 7; j++) 
            {
                DayBox db = new DayBox(date);
                date = date.plusDays(1);
                System.out.println(db.getDate().toString());
                db.setMinSize(200,200);
                db.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                
                Text no = new Text(""+db.getDate().getDayOfMonth());
                db.setTopAnchor(no, 5.0);
                db.setLeftAnchor(no, 5.0);
                calendar.add(db,j,i);
                if (db.getDay().getDate().getMonth() != yearMonth.getMonth())
                {
                	db.setVisible(false);
                }
                calendarBoxes.add(db);

		
		//top section
		Text month = new Text(yearMonth.getMonth().toString());
		lastMonth = new Button(yearMonth.getMonth().minus(1).toString());
		nextMonth = new Button(yearMonth.getMonth().plus(1).toString());
		HBox top = new HBox(lastMonth, month, nextMonth);
		
		//column headers
		Label[] days = new Label[] 
				{ 
					new Label("Monday"), 
					new Label("Tuesday"), 
					new Label("Wednesday"), 
					new Label("Thursday"), 
					new Label("Friday"), 
					new Label("Saturday"), 
					new Label("Sunday")
				};
		
		GridPane columnHeaders = new GridPane();
		columnHeaders.setMinWidth(100);
		int column = 0;
		for (Label l : days) {
            columnHeaders.add(l, column++, 0);
        }
		
		//calendar
		calendar.setMinSize(600, 400);
		buildCalendar();
		
		this.getChildren().addAll(top, columnHeaders, calendar);
	}
	private void buildCalendar()
	{
		// TODO Auto-generated method stub
		while(!date.getDayOfWeek().toString().equals("MONDAY"))
		{
			date = date.minusDays(1);
		}
		
            }
        }
		
	}
	//EventHandlers
	public void setLastMonth(EventHandler<ActionEvent> e)
	{
		lastMonth.setOnAction(e);
	}
	public void setNextMonth(EventHandler<ActionEvent> e)
	{
		nextMonth.setOnAction(e);
	}
	//sets and gets
	public YearMonth getYearMonth()
	{
		return yearMonth;
	}
}
*/