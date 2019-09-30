package views;

import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class HomeView extends AnchorPane {
	
/**
 * A constructor to build the view attached to the home button, it has no functionality attached and is basically just a placeholder.
 */
	public HomeView()
	{
		Text home = new Text("Plan My Life :)");
		this.getChildren().add(home);
		this.setTopAnchor(home, 200.0);
		this.setBottomAnchor(home, 200.0);
		this.setLeftAnchor(home,  200.0);
		this.setRightAnchor(home, 200.0);
	}

}
