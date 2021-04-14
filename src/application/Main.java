
/*
 *	jie134 - Ryan Gill
 *	CS-3443-003
 *	Dr. Rathore
 */
package application;
	
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			//Model.initInventory(); //(uncomment to initialize inventory if none exists)
			Model.loadFiles();    //loads the saved data.properties file
			
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Main.fxml"));
			Scene scene = new Scene(root,600,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
