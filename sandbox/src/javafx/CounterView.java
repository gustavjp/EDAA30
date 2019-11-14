package javafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CounterView extends Application {
	Button button;
	Label label;
	int counter;

	@Override
	public void start(Stage stage) {
		button = new Button("Yes!");
		button.setOnAction(event -> {
			counter++;
			label.setText(Integer.toString(counter));
		});
		label = new Label("0");

		HBox root = new HBox();
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setSpacing(20); // Sets the amount of horizontal space between each child in the hbox.
		root.setAlignment(Pos.CENTER); // Sets the overall alignment of children within the hbox
		root.getChildren().addAll(button, label);

		Scene scene = new Scene(root, 200, 80);
		stage.setScene(scene);

		stage.setTitle("Counter");
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}