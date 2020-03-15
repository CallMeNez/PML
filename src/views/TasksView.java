package views;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.TTBox;
import model.TaskBox;

public class TasksView extends VBox{
	private ListView<TaskBox> tasksList;
	private ObservableList<TaskBox> boxes;
	
	private TextField title;
	private TextField details;
	private Button create;
	
	
	public TasksView() {
		tasksList = new ListView<TaskBox>();
		boxes = FXCollections.observableArrayList();
		tasksList.setItems(boxes);
		this.getChildren().add(tasksList);
		
		Label titleLabel = new Label("Title: ");
		title = new TextField("Title");
		HBox titleBox = new HBox(titleLabel, title);
		
		Label detailsLabel = new Label("Details: ");
		details = new TextField("Details");
		HBox detailsBox = new HBox(detailsLabel, details);
		
		create = new Button("Create");
		VBox menu = new VBox(titleBox, detailsBox, create);
		this.getChildren().add(menu);
	}
	public void update(ArrayList<TaskBox> boxes)
	{
		this.boxes.clear();
		boxes.forEach(b -> 
		{
			if(b.getStatus())
			{
				this.boxes.add(b);
			}
		});
		boxes.forEach(b ->
		{
			if(!b.getStatus())
			{
				this.boxes.add(b);
			}
		tasksList.setItems(this.boxes);
		});
		ArrayList<String> titles = new ArrayList<String>(); boxes.forEach(b -> titles.add(b.getTitle()));
	}
	public ObservableList<TaskBox> getBoxes() {
		return boxes;
	}
	public String getTitle() {
		return title.getText();
	}
	public String getDetails() {
		return details.getText();
	}
	public void setCreateHandler(EventHandler<ActionEvent> e)
	{
		create.setOnAction(e);
	}
}
