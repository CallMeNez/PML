package model;

import java.sql.Time;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TaskBox extends VBox{
	private String title;
	private Boolean status;
	private String details;
	private CheckBox box;
	private Button delete;
	
	public TaskBox(String title, Boolean status, String details)
	{
		this.title = title;
		this.status = status;
		this.details = details;
		box = new CheckBox("Status");
		this.getChildren().add(new Text(title));
		box.setSelected(status);
		HBox statusBox = new HBox(new Label("Status: "), box);
			this.getChildren().add(statusBox);
		this.getChildren().add(new Text("Details: "+details));
		if(status)
		{
		this.setBackground(new Background(new BackgroundFill(
							Color.GREEN, 
							CornerRadii.EMPTY, 
							Insets.EMPTY)));
		}
		else
		{
			this.setBackground(new Background(new BackgroundFill(
							Color.RED, 
							CornerRadii.EMPTY, 
							Insets.EMPTY)));
		}
		delete = new Button("Delete");
		this.getChildren().add(delete);
	}
	public String getTitle()
	{
		return  title;
	}
	public Boolean getStatus()
	{
		return status;
	}
	public String getDetails()
	{
		return details;
	}
	
	public void setBoxHandler(EventHandler<ActionEvent> e)
	{
		box.setOnAction(e);
	}
	public void setDeleteHandler(EventHandler<ActionEvent> e)
	{
		delete.setOnAction(e);
	}
} 
