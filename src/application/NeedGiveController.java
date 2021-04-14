/*
 *	jie134 - Ryan Gill
 *	CS-3443-003
 *	Dr. Rathore
 */
package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;


/********************************************************************
 * 			NeedGiveController - Covid-19 Donation app
 ********************************************************************
 *Purpose:
 *		Main Controller for the Thousand-Hands app.
 *		Contains both model and view logic in one class per the Lab 4
 *		submission guidelines. 
 *
 *Notes:
 *		A bit hacky, definitely helps me understand why we would use
 *		a model class as well as using a 1:1 relationship between 
 *		FXML files and java controllers, per Oracle recommendations.
 *		All FXML files were linked to this controller using the 
 *		fx:controller parameter in a named (fx:id) AnchorPane tag.
 *		I separated code by FXML relation, so it can be refactored 
 *		later.  
 *
 *General App Functions:
 *		Simulates a donation system that allows users to donate or 
 *		receive products using their username, product name, and amount
 *		to create an inventory that can be later viewed and searched.
 *
 *
 ********************************************************************
 */

public class NeedGiveController {
	
	//class attributes. They must be static since calling the scene change
	//methods reinstatiates the class, static variables allows the data to 
	//remain intact
	
	
	
	
    
    @FXML
    private AnchorPane needGivePane;
    
    @FXML
    private Button needHomeBtn;

    @FXML
    private Button needInvBtn;
    
    @FXML
    private Button userBtn;
    
    @FXML
    private Button productBtn;
    
    @FXML
    private Button quantityBtn;
    
    @FXML
    private Button addSubBtn;
    
    @FXML
    private TextField userField;
    
    @FXML
    private TextField productField;
    
    @FXML
    private TextField quantityField;
    
    /*****************************************************************
	 * 				needToMain()
	 *****************************************************************
	 *
	 *	Parameters: 
	 *		event: ActionEvent - home button pressed
	 *	Return Type:
	 *		none
	 *	Decorator:
	 *		FXML 
	 *	
	 *	Switches the scene from the NeedGive view to the Main view
	 *	Does so by instantiating a new AnchorPane (the parent element 
	 *	of each view hierarchy) 
 	 *	
	 *****************************************************************
	 */

    @FXML
    void needToMain(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("Main.fxml"));
    	pane.setStyle("-fx-background-color: rgba(148, 211, 247, 1);");
    	needGivePane.getChildren().setAll(pane);
    }

    /*****************************************************************
	 * 				needToInv()
	 *****************************************************************
	 *
	 *	Parameters: 
	 *		event: ActionEvent - inventory button pressed
	 *	Return Type:
	 *		none
	 *	Decorator:
	 *		FXML 
	 *	
	 *	Switches the scene from the NeedGive view to the Inventory view
	 *	Does so by instantiating a new AnchorPane (the parent element 
	 *	of each view hierarchy) 
 	 *	
	 *****************************************************************
	 */
    @FXML
    void needToInv(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("Inventory.fxml"));
    	pane.setStyle("-fx-background-color: rgba(148, 211, 247, 1);");
    	needGivePane.getChildren().setAll(pane);

    }
    
    
    /*****************************************************************
	 * 				checkUser()
	 *****************************************************************
	 *
	 *	Parameters: 
	 *		event: ActionEvent - user button pressed
	 *	Return Type:
	 *		none
	 *	Decorator:
	 *		FXML 
	 *	Helpers:
	 *		verifyText()
	 *	
	 *	Gets user info and determines its validity
 	 *	
	 *****************************************************************
	 */
    @FXML
    void checkUser(ActionEvent event) {
    	
    	String text = userField.getText();  //gets field info
    	Alert a = new Alert(AlertType.CONFIRMATION);
	   	a.setHeaderText("User ID");
	   	
	   	//valid username
    	if (Model.verifyUser(text)) {
    		a.setContentText("User ID is valid!");
    		
    	//invalid 
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("User ID is invalid, please enter username in the format:\n\nabc123");
    	}
    	a.show();	//displays alert after condition
    }

    /*****************************************************************
	 * 				checkProduct()
	 *****************************************************************
	 *
	 *	Parameters: 
	 *		event: ActionEvent - item button pressed
	 *	Return Type:
	 *		none
	 *	Decorator:
	 *		FXML 
	 *	Helpers:
	 *		verifyText()
	 *	
	 *	Gets product info and determines its validity
 	 *	
	 *****************************************************************
	 */
    @FXML
    void checkProduct(ActionEvent event) {
    	
    	String text = productField.getText();	//gets field info
    	Alert a = new Alert(AlertType.CONFIRMATION);
	   	a.setHeaderText("Product");
	   	
	   	//valid item
    	if (Model.verifyProduct(text)) {
    		a.setContentText("Product Name is valid!");
    		
    	//invalid
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Product Name is invalid, please use only alphabetical and numerical characters");
    	}
    	a.show();  //displays alert
    }

    /*****************************************************************
	 * 				checkQuantity()
	 *****************************************************************
	 *
	 *	Parameters: 
	 *		event: ActionEvent - amount button pressed
	 *	Return Type:
	 *		none
	 *	Decorator:
	 *		FXML 
	 *	Helpers:
	 *		verifyInt()
	 *	
	 *	Gets amount info and determines its validity
 	 *	
	 *****************************************************************
	 */
    @FXML
    void checkQuantity(ActionEvent event) {
    	
    	String text = quantityField.getText();
    	Alert a = new Alert(AlertType.CONFIRMATION);
	   	a.setHeaderText("Item Quantity");
	   	
	   	//valid
    	if (Model.verifyInt(text)) {
    		a.setContentText("Item Quantity is valid!");
    		
    	//invalid
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Item Quantity is invalid, please input an integer");
    	}
    	a.show();

    }
    
    /*****************************************************************
	 * 				process()
	 *****************************************************************
	 *
	 *	Parameters: 
	 *		event: ActionEvent - add/sub button pressed
	 *	Return Type:
	 *		none
	 *	Decorator:
	 *		FXML 
	 *	Helpers:
	 *		verifyText()
	 *		verifyInt()
	 *	
	 *	Core Donation Algorithm
	 *	Handles input errors and out of stock problems
	 *	Determines if the user selected need or give on the main view
	 *	using need flag, then makes a donation or receives a product
	 *	if the item is available. The result to the user will be some
	 *	alerts describing the donation, but the back end will still 
	 *	process the inventory accordingly 
     * @throws IOException 
     * @throws InvalidUserException 
 	 *	
	 *****************************************************************
	 */
    @FXML
    void process(ActionEvent event) throws IOException, InvalidUserException {
  
    	//System.out.println(Models.hash);
    	//makes alert object
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	FileOutputStream writer = new FileOutputStream(Model.file,true);
    	a.setHeaderText("Donation Complete!");
    	
    	//checks for valid input in all fields
    	if (Model.verifyUser(userField.getText()) && 
    		Model.verifyProduct(productField.getText()) && 
    		Model.verifyInt(quantityField.getText())) {
    		
    		//assigns variables to fields if they are valid
    		String user = userField.getText();
        	String item = productField.getText();
        	String amount = quantityField.getText();
        	
        	//adds user's name to map of existing users
        	Model.addUserName(user);
        	
    		
        	//on Need View
    		if (Model.need == true) {
    			
    			//subtractItem() will return -1 if item is not found, 0 if item is found but user has requested too much
    			//Otherwise, it returns the difference after making the withdrawal
    			int difference = Model.subtractItem(item, amount);
    			if (difference > 0) {
    				//clears input fields
					userField.clear();
	    			productField.clear();
	    			quantityField.clear();
	    			
	    			//alert message for donation
					a.setContentText("You have received " + item + " (x" + amount + ")\nThere are " + difference + " " + item + " remaining.\nBe well, " + user + "!");
					
				
				//item is found but user has requested too much
    			}else if(difference == 0) {
    				a.setAlertType(AlertType.ERROR);
    				a.setHeaderText("Not enough inventory");
    				a.setContentText("We found the item " + item + ", but we do not have " + amount + " in stock.");
    				
    			//item not found
    			}else {
    				a.setAlertType(AlertType.ERROR);
    				a.setHeaderText("Item not found");
    				a.setContentText("Sorry, we could not find " + item + " in our inventory");
    			}
    			
    		//On Give View
    		}else {
    			
    			//Model.additem() returns true if the item is found in inventory in order to change alerts
    			if (Model.addItem(item, amount)) {
    				a.setContentText("You have added " + item + " (x" + amount + ") to existing inventory.\nThank you " + user + "!");
    			}else {
    				a.setContentText("You have donated " + item + " (x" + amount + ")\nThank you " + user + "!");
    			}
    			
    			//clear input fields
    			userField.clear();
    			productField.clear();
    			quantityField.clear();
    			
    		}
    		
    	//One or more input fields is invalid
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Donation");
    		a.setContentText("One of your input fields is invalid. Use the buttons next to the input fields to determine validity.");
    	}
    	
    	//show alert after all conditions
    	a.show();
    	writer.close();

    }
}


