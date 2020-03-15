package controllers;
import views.TasksView;
import model.TTBox;
import model.TaskBox;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.*;

public class TasksController {

		private TasksView tav;
		private Connection conn;
		private Statement stmt;
		private ResultSet rs;
		private ArrayList<TaskBox> boxes;
		private Date workingDate;
		
		public TasksController(TasksView tav, Connection conn, Date workingDate)
		{
			this.tav = tav;
			this.conn = conn;
			this.workingDate = workingDate;
			attachEventHandlers();
			update(workingDate);
		}

		private void attachEventHandlers() {
			tav.setCreateHandler(new CreateHandler());
//			if(tav.getChildren() != null)
//			{
//				tav.getBoxes().forEach(b -> {
//					((TaskBox) b).setBoxHandler(new CheckBoxHandler());
//					((TaskBox) b).setDeleteHandler(new DeleteHandler());
//				});
//			}
		}
		
		private class CheckBoxHandler implements EventHandler<ActionEvent>
		{
			@Override
			public void handle(ActionEvent event) 
			{
				
			}
			
		}
		private class DeleteHandler implements EventHandler<ActionEvent>
		{
			@Override
			public void handle(ActionEvent event)
			{
				
			}
		}
		
		private class CreateHandler implements EventHandler<ActionEvent>
		{
			@Override
			public void handle(ActionEvent event)
			{
				try 
				{
					stmt.executeUpdate("INSERT INTO tasks (taskTitle, taskState, taskDetails, taskDate)"
							+ " VALUES ('"+tav.getTitle()+"', '"+0+"', '"+tav.getDetails()+"', '"+workingDate+"');");
				} 
				catch (SQLException e) {
					e.printStackTrace();
				}
				update(workingDate);
			};
		}
		public TasksView getTasksView() {
			return tav;
		}

		public void update(Date workingDate) 
		{
			ArrayList<TaskBox> boxes = new ArrayList<TaskBox>();
			this.workingDate = workingDate;
			try 
			{
				stmt = conn.createStatement();
				stmt.execute("use pml;");
				rs = stmt.executeQuery(
					"SELECT taskTitle, taskState, taskDetails, taskDate FROM tasks WHERE taskDate = '"
					+workingDate+"';");
				
			while(rs.next())
			{
				TaskBox tb = new TaskBox(
						rs.getString("taskTitle"),
						rs.getBoolean("taskState"),
						rs.getString("taskDetails")
						 
						);
				tb.setBoxHandler(new CheckBoxHandler());
				tb.setDeleteHandler(new DeleteHandler());
				boxes.add(tb);
			}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			tav.update(boxes);
		}

		public void closeDB() {
			if (conn!=null)
			{
			try 
			{
				conn.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
			}
			
		}
	}
