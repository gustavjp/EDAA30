package sudoku;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		Sudoku sudoku = new Sudoku();
		int[][] matrix = new int[9][9];
		VBox vb = new VBox();
		TilePane tp = new TilePane();
		HBox hbb = new HBox();
		Button solve = new Button("Solve");
		Button clear = new Button("Clear");
		TextField[][] tfa = new TextField[9][9];

		tp.setPrefSize(500, 500);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				final int a = i;
				final int b = j;
				TextField tf = new TextField();
				tf.setEditable(true);
				tf.setPrefSize(50, 50);
				tf.setAlignment(Pos.CENTER);

				tf.setOnKeyReleased(Event -> {
					String s = tf.getText();
					int n = 0;
					try {
						n = Integer.parseInt(s);
						if (n < 1 || n > 9) {
							Alert a1 = new Alert(AlertType.ERROR);
							a1.setContentText("Only input numbers between 1 and 9.");
							a1.setHeaderText("Warning!");
							a1.show();
							tf.clear();
							n = 0;
						}
					} catch (NumberFormatException e) {
						Alert a2 = new Alert(AlertType.ERROR);
						a2.setContentText("Only input numbers.");
						a2.setHeaderText("Warning!");
						a2.show();
						tf.clear();
						n = 0;
					}
					if (n != 0) {
						matrix[a][b] = n;
					}
				});
				tfa[i][j] = tf;
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				tp.getChildren().add(tfa[i][j]);
			}
		}

		tp.setTileAlignment(Pos.TOP_CENTER);

		solve.setOnAction(Event -> {
			sudoku.setMatrix(matrix);
			boolean solved = sudoku.solve();
			int[][] solution = sudoku.getMatrix();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					tfa[i][j].setText(String.valueOf(solution[i][j]));
				}
			}
			if (!solved) {
				Alert a3 = new Alert(AlertType.INFORMATION);
				a3.setContentText("No solution found.");
				a3.setHeaderText("Bad puzzle!");
				a3.show();
			}
		});

		clear.setOnAction(Event -> {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					tfa[i][j].setText("");
				}
			}
		});

		hbb.getChildren().add(solve);
		hbb.getChildren().add(clear);

		hbb.setAlignment(Pos.CENTER);

		vb.getChildren().add(tp);
		vb.getChildren().add(hbb);

		Scene scene = new Scene(vb, 500, 500);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
