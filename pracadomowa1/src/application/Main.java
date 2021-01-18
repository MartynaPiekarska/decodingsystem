package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		
		try {
			BorderPane root = new BorderPane();
			root.setId("my-root");
			Scene scene = new Scene(root,500,350);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			// ------------------------------------------------------------------------------------
			Label labelSource = new Label("Źródło:");
			Label space = new Label();
			Label labelResult = new Label("Wynik operacji:");
			TextField text1 = new TextField();
			text1.getStyleClass().add("my-field");
			TextField text2 = new TextField();
			text2.getStyleClass().add("my-field");
			
			VBox left_vbox = new VBox(10);
			left_vbox.getChildren().addAll(labelSource, text1, space, labelResult, text2);
			root.setLeft(left_vbox);
			left_vbox.setPadding(new Insets(40, 0, 0, 60));
			
			Button copyButton = new Button("  Kopiuj  ");
			copyButton.setId("my-button");
			VBox center_vbox = new VBox(10);
			copyButton.setLayoutX(250);
			copyButton.setLayoutY(100);
			center_vbox.getChildren().add(copyButton);
			root.setCenter(center_vbox);
			center_vbox.setPadding(new Insets(130, 0, 0, -80));
			
			// ------------------------------------------------------------------------------------
			Label labelRdBtn = new Label("Operacja:");
			RadioButton r1 = new RadioButton("kodowanie");
			RadioButton r2 = new RadioButton("dekodowanie");
			
			VBox right_vbox = new VBox(10);
			right_vbox.getChildren().addAll(labelRdBtn, r1, r2);
			root.setRight(right_vbox);
			right_vbox.setPadding(new Insets(40, 20, 10, 0));
			
			
			r1.setUserData("Wybrano przycisk nr 1");
			r2.setUserData("Wybrano przycisk nr 2");
			
			ToggleGroup tgroup = new ToggleGroup();
			r1.setToggleGroup(tgroup);
			r2.setToggleGroup(tgroup);
			
			tgroup.selectedToggleProperty().addListener(
					(observable_value, old_toggle, new_toggle) ->{
						if (r1.isSelected()) {
							System.out.println("Wybrano kodowanie");
						} else {
							System.out.println("Wybrano dekodowanie");
						}
						
						System.out.println(new_toggle.getUserData().toString());
						
					}
					);
			
			// ------------------------------------------------------------------------------------
			
			Button actionButton = new Button(" Wykonaj ");
			actionButton.setId("my-button");
			
			HBox bottom_hbox = new HBox(10);
			root.setBottom(bottom_hbox);
			bottom_hbox.getChildren().add(actionButton);
			bottom_hbox.setPadding(new Insets(10, 10, 70, 60));

			
			// KONSTRUKTORY z RLESys
			RLESys rle = new RLESys(text1.getText(), text2.getText());
			RLESys rle2 = new RLESys(text1.getText(), text2.toString());
			
			actionButton.setOnAction(event->{
				System.out.println("Wciśnięto przycisk wykonaj");
				if (tgroup.getSelectedToggle() != null)
					System.out.println("Wybrano jeden z przycisków radiowych");
				else
					System.out.println("Nie wybrano operacji");
				
				
				// KODUJ I ODKODUJ
				if (r1.isSelected()) {
					rle.encode(text1.getText());
					text2.setText(rle.encodedText.substring(0, rle.encodedText.length()-1));
				}
				else if(r2.isSelected()) {	
					rle2.decode(text1.getText());
					text2.setText(rle2.decodedText.toString());
				}

			}); 
			
			
			// PRZYCISK KOPIUJ
			copyButton.setOnAction(event->{
				System.out.println("Wciśnięto przycisk kopiowania");
				text1.setText(text2.getText());
				text2.clear();

			}); 
			
			// ------------------------------------------------------------------------------------
			
			primaryStage.setTitle("Martyna Wierzbicka Homework no1");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
