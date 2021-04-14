
/*
 *	jie134 - Ryan Gill
 *	CS-3443-003
 *	Dr. Rathore
 */
package application;

import java.io.IOException;
import java.util.Map.Entry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Model;

/********************************************************************
 * 			MainController - Covid-19 Donation app
 ********************************************************************
 *Purpose:
 *		Main Controller for the first view of Thousand-Hands app.
 *	    Primarily contains logic to switch views based on the user's
 *		selection.
 *
 *Notes:
 *		A simple refactor from lab4 into a new controller file  
 *
 *
 ********************************************************************
 */

public class MainController {
	
	
	//Main.FXML elements
	
		@FXML
		private AnchorPane root;
		
		@FXML
	    private Button needBtn;

	    @FXML
	    private Button giveBtn;

	    @FXML
	    private Button inventoryBtn;
	    
	    @FXML
	    private Label needLabel;
	    
	    @FXML
	    private Button mainHomeBtn;
	    
	    @FXML
	    private Button aboutBtn;
	    
	    @FXML
	    private AnchorPane homeAnchor;
	    
	    /*****************************************************************
		 * 				mainToNeed()
		 *****************************************************************
		 *
		 *	Parameters: 
		 *		event: ActionEvent - need button pressed
		 *	Return Type:
		 *		none
		 *	Decorator:
		 *		FXML 
		 *	
		 *	Switches the scene from the Main view to the NeedGive view
		 *	Does so by instantiating a new AnchorPane (the parent element 
		 *	of each view hierarchy) then makes some style changes based on
		 *	the users selection of need. Flips the need tag as well so the
		 *	program can allow the user to receive a donation
		 *	
		 *****************************************************************
		 */

	    @FXML
	    void mainToNeed(ActionEvent event) throws IOException {
	    	
	    	//sets the static flag
	    	Model.need = true;
	    	
	    	//new AnchorPane object with view we want to load 
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("NeedGive.fxml"));
	    	
	    	//style changes, elements must be accessed directly from the new object.
	    	//the class attributes seem inaccessible with getter methods, (I get a 
	    	//null-pointer error) the getChildren methods allow you to traverse the child
	    	//objects of the AnchorPane object
	    	pane.setStyle("-fx-background-color: #f58994;");
	    	Node outer = pane.getChildren().get(0);
	    	for (Node inner: ((AnchorPane)outer).getChildren()) {
	    		if (inner instanceof Label) {
	    			((Label) inner).setText("We're here to help!");
	    		}
	    	}
	    	
	    	//sets the scene using modified object by accessing the current scene's children
	    	root.getChildren().setAll(pane);
	    }
	    
	    /*****************************************************************
		 * 				mainToGive()
		 *****************************************************************
		 *
		 *	Parameters: 
		 *		event: ActionEvent - give button pressed
		 *	Return Type:
		 *		none
		 *	Decorator:
		 *		FXML 
		 *	
		 *	Switches the scene from the Main view to the NeedGive view
		 *	Does so by instantiating a new AnchorPane (the parent element 
		 *	of each view hierarchy) then makes some style changes based on
		 *	the users selection of give. Flips the need tag as well so the
		 *	program can allow the user to make a donation. Logic identical
		 *	to mainToNeed(), but sets style and need flag appropriately
	 	 *	
		 *****************************************************************
		 */

	    @FXML
	    void mainToGive(ActionEvent event) throws IOException {
	    	
	    	//sets the static flag
	    	Model.need = false;
	    	
	    	//new AnchorPane object with view we want to load 
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("NeedGive.fxml"));
	    	
	    	//style changes, elements must be accessed directly from the new object.
	    	//the class attributes seem inaccessible with getter methods, (I get a 
	    	//null-pointer error) the getChildren methods allow you to traverse the child
	    	//objects of the AnchorPane object
	    	pane.setStyle("-fx-background-color: #83fc83;");
	    	Node outer = pane.getChildren().get(0);
	    	for (Node inner: ((AnchorPane)outer).getChildren()) {
	    		if (inner instanceof Label) {
	    			((Label) inner).setText("Thank you for donating!");
	    		}
	    	}
	    	
	    	//sets the scene using modified object by accessing the current scene's children
	    	root.getChildren().setAll(pane);
	    	

	    }
	    
	    /*****************************************************************
		 * 				mainToInventory()
		 *****************************************************************
		 *
		 *	Parameters: 
		 *		event: ActionEvent - inventory button pressed
		 *	Return Type:
		 *		none
		 *	Decorator:
		 *		FXML 
		 *	
		 *	Switches the scene from the Main view to the Inventory view
		 *	Does so by instantiating a new AnchorPane (the parent element 
		 *	of each view hierarchy) 
	 	 *	
		 *****************************************************************
		 */

	    @FXML
	    void mainToInventory(ActionEvent event) throws IOException {
	    	
	    	AnchorPane pane = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
	    	root.getChildren().setAll(pane);

	    }
	    
	    /*****************************************************************
		 * 				viewAbout()
		 *****************************************************************
		 *
		 *	Parameters: 
		 *		event: ActionEvent - about button pressed
		 *	Return Type:
		 *		none
		 *	Decorator:
		 *		FXML 
		 *	
		 *	Shows an alert containing about information and directions about
		 *  the application.
	 	 *	
		 *****************************************************************
		 */
	    @FXML
	    void viewAbout(ActionEvent event) {
	    	
	    	//Instantiates an alert object and sets its text
	    	Alert a = new Alert(AlertType.INFORMATION);
	    	String output = "A-Thousand-Hands Donations is an application that helps those in need during the COVID-19 pandemic. ";
	    	output += "Offer a donation using the Give Button, or press the Need Button if you require supplies.\nThank you for donating:\n\n";
	    	int i=0;
	    	for (Entry<String, String> entry: Model.users.entrySet()) {
	    		if (i < 5)
	    			output += entry.getKey() + "\n";
	    		i++;
	    	}
	    	a.setHeaderText("About A-Thousand-Hands Donations");
	    	a.setContentText(output);
	    	a.show();   //displays the alert
	    }
	    
	    

}
