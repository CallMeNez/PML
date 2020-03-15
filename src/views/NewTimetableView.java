package views;

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
import javafx.scene.layout.VBox;
import model.TTBox;

public class NewTimetableView extends BorderPane{
	//menu
	private Button set;
	private Button clear;
	private Button remove;
	private TextField activity;
	private ComboBox<Integer> startHour;
	private ComboBox<Integer> startMin;
	private ComboBox<Integer> endHour;
	private ComboBox<Integer> endMin;
	private ComboBox<String> colour;
	private TextField description;
	//repeats
	private ComboBox<Integer> repeatInterval;
	private ComboBox<String> repeatTime;
	private ComboBox<Integer> repeatEndDateDay;
	private ComboBox<Integer> repeatEndDateMonth;
	private ComboBox<Integer> repeatEndDateYear;
	
	private GridPane menu;

	private VBox list;
	//UPDATE TO TREEVIEWS AND HAVE THE OPTIONS TO EDIT IN THERE?
	public NewTimetableView() {
		list = new VBox();
		this.setCenter(list);
		
		activity = new TextField();
		menu = new GridPane();
		menu.add(new Label("Activity: "), 0, 0);
		menu.add(activity, 1, 0, 3, 1);
		
		ObservableList<Integer> hours  = FXCollections.observableArrayList();
		for (int i = 0; i<24; i++)
		{
			hours.add(i);
		}
		
		ObservableList<Integer> mins = FXCollections.observableArrayList();
		for (int i = 0; i<60; i = i+5)
		{
			mins.add(i);
		}
		
		startHour = new ComboBox<Integer>(hours);
		startMin = new ComboBox<Integer>(mins);
		menu.add(new Label("Start: "), 0, 1);
		menu.add(startHour, 1, 1);
		menu.add(startMin, 2, 1);
		
		endHour = new ComboBox<Integer>(hours);
		endMin = new ComboBox<Integer>(mins);
		menu.add(new Label("End: "), 0, 2);
		menu.add(endHour, 1, 2);
		menu.add(endMin, 2, 2);
		description = new TextField();
		menu.add(new Label("Description: "), 0, 3);
		menu.add(description, 1, 3, 3, 1);
		
		ObservableList<String> colours = FXCollections.observableArrayList();
		colours.add("White");
		colours.add("Red");
		colours.add("Orange");
		colours.add("Yellow");
		colours.add("Green");
		colours.add("Cyan");
		colours.add("Blue");
		colours.add("Purple");
		colours.add("Pink");
		colours.add("Grey");
		
		colour = new ComboBox<String>(colours);
		menu.add(new Label("Colour: "), 0, 4);
		menu.add(colour, 1, 4, 3, 1);
		
		clear = new Button("Clear");
		set = new Button("Set");
		menu.add(new Label("Clear: "), 0, 5);
		menu.add(clear, 1, 5);
		menu.add(new Label("Set: "), 2, 5);
		menu.add(set, 3, 5);
		
		remove = new Button("Remove");
		menu.add(remove, 0, 6, 4, 1);
		
		this.setLeft(menu);
	}
	
	public void update(ArrayList<TTBox> boxes)
	{
		list.getChildren().clear();
		boxes.forEach(b -> list.getChildren().add(b));
	}
	//button handlers
	public void setSetHandler(EventHandler<ActionEvent> e)
	{
		set.setOnAction(e);
	}
	public void setClearHandler(EventHandler<ActionEvent> e)
	{
		clear.setOnAction(e);
	}
	public void setRemoveHandler(EventHandler<ActionEvent> e)
	{
		remove.setOnAction(e);
	}
	//sets & gets

	public String getActivity() {
		return activity.getText();
	}

	public void setActivity(String activity) {
		this.activity.setText(activity);
	}

	public Integer getStartHour() {
		return startHour.getValue();
	}

	public void setStartHour(int startHour) {
		this.startHour.setValue(startHour);
	}

	public Integer getStartMin() {
		return startMin.getValue();
	}

	public void setStartMin(int startMin) {
		this.startMin.setValue(startMin);
	}

	public int getEndHour() {
		return endHour.getValue();
	}

	public void setEndHour(int endHour) {
		this.endHour.setValue(endHour);;
	}

	public int getEndMin() {
		return endMin.getValue();
	}

	public void setEndMin(int endMin) {
		this.endMin.setValue(endMin);;
	}

	public String getColour() {
		return colour.getValue();
	}

	public void setColour(String colour) {
		this.colour.setValue(colour);;
	}

	public String getDescription() {
		return description.getText();
	}

	public void setDescription(String description) {
		this.description.setText(description);
	}
}
