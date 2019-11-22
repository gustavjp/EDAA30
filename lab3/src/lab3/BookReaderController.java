package lab3;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class BookReaderController extends Application {
	
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); // se handledning
		Scanner ss = new Scanner(new File("undantagsord.txt"));
		ss.findWithinHorizon("\uFEFF", 1);
		ss.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		Set<String> stopWords = new HashSet<String>();
		while(ss.hasNext()) {
			String word = ss.next().toLowerCase();
			stopWords.add(word);
		}
		ss.close();
		GeneralWordCounter gp = new GeneralWordCounter(stopWords);
		while(s.hasNext()) {
			String word = s.next().toLowerCase();
			gp.process(word);
		}
		s.close();
		
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("BookReader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		HBox hb = new HBox();
		Button b1 = new Button("Alphabetic");
		Button b2 = new Button("Frequency");
		Button b3 = new Button("Search");
		TextField tf = new TextField();
		hb.getChildren().addAll(b1, b2, b3, tf);
		root.setBottom(hb);
		
		ObservableList<Map.Entry<String, Integer>> words 
		= FXCollections.observableArrayList(gp.getWords());
		ListView<Map.Entry<String, Integer>> listView = new ListView<Map.Entry<String, Integer>>(words);
		root.setCenter(listView);
		
		b1.setOnAction(event -> {
			words.sort((e1, e2) -> e1.getKey().compareTo(e2.getKey()));
		});
		b2.setOnAction(event -> {
			words.sort((e1, e2) -> e1.getValue().compareTo(e2.getValue()));
		});
		b3.setOnAction(event -> {
			Predicate<Map.Entry<String, Integer>> searchPredicate = me -> me.getKey().equals(tf.getText().toLowerCase().replaceAll("\\s", ""));
			try {
				listView.scrollTo(words.filtered(searchPredicate).get(0));
			} catch (IndexOutOfBoundsException e){
				Alert a = new Alert(Alert.AlertType.ERROR, "The word " + tf.getText().toLowerCase().replaceAll("\\s", "") + " not found in list.");
				a.show();
			}
		});
		tf.setOnKeyPressed(event -> {
			if(event.getCode().equals(KeyCode.ENTER)) {
				b3.fire();
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
