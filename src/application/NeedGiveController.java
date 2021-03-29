package application;

import java.io.IOException;
import java.util.ArrayList;

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
	private static boolean need = true;
	private static ArrayList<String> users = new ArrayList<String>();
	private static ArrayList<String> items = new ArrayList<String>();
	private static ArrayList<Integer> amounts = new ArrayList<Integer>();
	private static ObservableList<String> obsInventory = FXCollections.observableArrayList();
	
	
	/*********************************************
	 * 				verifyText()
	 *********************************************
	 *
	 *	Parameters:
	 *		text: String
	 *	Return Type: 
	 *		boolean
	 *
	 *	Tests if text is a valid String for use
	 *  as a username or product name. Checks if
	 *  the string contains at least one alphabetical
	 *  character
	 *********************************************
	 */
	public boolean verifyText(String text) {
		
		//empty field
		if (text.length() == 0)
			return false;
		
		//checks for at least one alphabetical char
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
				return true;
		}
		return false;   //no alphabetical char found
	}
	
	/*********************************************
	 * 				verifyInt()
	 *********************************************
	 *
	 *	Parameters: 
	 *		text: String
	 *	Return Type: 
	 *		double
	 *
	 *	Checks if the text string is valid for use
	 *	in the item quantity field. Invalid if the 
	 *	string cannot be parsed to an int
	 *********************************************
	 */
	public boolean verifyInt(String text) {
		
		//empty
		if (text.length() == 0)
			return false;
		
		//compares unicode values in string
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c < '0' || c > '9')
				return false;	//non-int char found
		}
		return true;
	}
	
	
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
    	need = true;
    	
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
    	need = false;
    	
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
    	output += "Offer a donation using the Give Button, or press the Need Button if you require supplies";
    	a.setHeaderText("About A-Thousand-Hands Donations");
    	a.setContentText(output);
    	a.show();   //displays the alert
    }
    
    
    
    
    //NeedGive.FXML elements
    
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
    	if (verifyText(text)) {
    		a.setContentText("User ID is valid!");
    		
    	//invalid 
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("User ID is invalid, please use some alphabetical characters");
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
    	if (verifyText(text)) {
    		a.setContentText("Product Name is valid!");
    		
    	//invalid
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setContentText("Product Name is invalid, please use some alphabetical characters");
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
    	if (verifyInt(text)) {
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
 	 *	
	 *****************************************************************
	 */
    @FXML
    void process(ActionEvent event) {
  
    	//makes alert object
    	Alert a = new Alert(AlertType.CONFIRMATION);
    	a.setHeaderText("Donation Complete!");
    	
    	//checks for valid input in all fields
    	if (verifyText(userField.getText()) && 
    		verifyText(productField.getText()) && 
    		verifyInt(quantityField.getText())) {
    		
    		//assigns variables to fields if they are valid
    		String user = userField.getText();
        	String item = productField.getText();
        	Integer amount = Integer.valueOf(quantityField.getText());
        	
        	//flags
        	boolean exists = false, found = false;
    		
        	//on Need View
    		if (need == true) {
    			
    			//the ArrayLists for user, item, and quantity data are all the same size
    			int i = 0;
    			
    			//iterates over arrays
    			while(i < items.size()) {
    				
    				//if a matching item name is found
    				if (items.get(i).equals(item)) {
    					exists = true;
    					
    					//if the amounts are also the same
    					if (amounts.get(i).intValue() >= amount.intValue()) {
    						
    						//Donation is possible
    						Integer difference = amounts.get(i) - amount;  //deduction from inventory
    						amounts.set(i, difference);
    						
    						//clears input fields
    						userField.clear();
    		    			productField.clear();
    		    			quantityField.clear();
    		    			
    		    			//alert message for donation
    						a.setContentText("You have received " + item + " (x" + amount + ") from user: " + users.get(i) + ".\nBe well, " + user + "!");
    						break;  //stops the iteration, always gives first item found
    						
    					
    					//if the item is found, but there is not enough inventory
    					}else
    						found = true;
    				}
    				i++;	//conditions finished, increment LCV
    			}//end while
    			
    			
    			//item is not in inventory
    			if (exists == false) {
    				a.setAlertType(AlertType.ERROR);
    				a.setHeaderText("Item not found");
    				a.setContentText("Sorry, we could not find " + item + " in our inventory");
    				
    			//item is in inventory, but user has requested too much
    			}else if(found == true) {
    				a.setAlertType(AlertType.ERROR);
    				a.setHeaderText("Not enough inventory");
    				a.setContentText("We found the item " + item + ", but we do not have " + amount + " in stock.");
    			}
    		
    		//On Give View
    		}else {
    			users.add(user);
    			items.add(item);
    			amounts.add(amount);
    			userField.clear();
    			productField.clear();
    			quantityField.clear();
    			a.setContentText("You have donated " + item + " (x" + amount + ")\nThank you " + user + "!");
    		}
    		
    	//One or more input fields is invalid
    	}else {
    		a.setAlertType(AlertType.ERROR);
    		a.setHeaderText("Invalid Donation");
    		a.setContentText("One of your input fields is invalid. Use the buttons next to the input fields to determine validity.");
    	}
    	
    	//show alert after all conditions
    	a.show();

    }
    

    
    
    
    //Inventory.FXML elements
    
    @FXML
    private AnchorPane inventoryPane;
    
    @FXML
    private Button invHomeBtn;
    
    @FXML
    private Button invInventoryBtn;
    
    @FXML
    private Button searchBtn;
    
    @FXML
    private Button showAllBtn;
    
    @FXML
    private TextField userSearch;
    
    @FXML
    private TextField itemSearch;
    
    @FXML
    private ListView<String> inventoryList;
    

    /*****************************************************************
	 * 				inventoryToHome()
	 *****************************************************************
	 *
	 *	Parameters: 
	 *		event: ActionEvent - home button pressed
	 *	Return Type:
	 *		none
	 *	Decorator:
	 *		FXML 
	 *	
	 *	Switches the scene from the inventory view to the Main view
	 *	Does so by instantiating a new AnchorPane (the parent element 
	 *	of each view hierarchy) 
 	 *	
	 *****************************************************************
	 */
    @FXML
    void inventoryToHome(ActionEvent event) throws IOException {
    	
    	AnchorPane pane = FXMLLoader.load(getClass().getResource("Main.fxml"));
    	inventoryPane.getChildren().setAll(pane);

    }
    
    /*****************************************************************
	 * 				showAll()
	 *****************************************************************
	 *
	 *	Parameters: 
	 *		event: ActionEvent - show all button pressed
	 *	Return Type:
	 *		none
	 *	Decorator:
	 *		FXML 
	 *	
	 *	Shows all the items in inventory on the ListView element
	 *	if there is nothing in inventory, an alert will apear
 	 *	
	 *****************************************************************
	 */
    @FXML
    void showAll(ActionEvent event) {
    	
    	//manages lists
    	inventoryList.getItems().clear();
    	obsInventory.clear();
    	
    	//empty string for transfer from three ArrayList to one ObservableList
    	//this type is required for ListView by JavaFX
    	String data = "";
    	
    	//ArrayLists are same size
    	if (users.size() > 0) {
	    	for (int i = 0; i < users.size(); i++) {
	    		
	    		//sets data string by concatenating strings from ArrayLists
	    		data = items.get(i) + " (x" + amounts.get(i) + ") Donated by: " + users.get(i);
	    		obsInventory.add(data);	//adds string to the ObservableList
	    	}
	    	
	    	//sets the ListView based on the ObservableList
	    	inventoryList.setItems(obsInventory);
	    	
	    //empty inventory
    	}else {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setHeaderText("Inventory is empty");
    		a.setContentText("Try donating an item!");
    		a.show();
    	}
    	
    }
    
    /*****************************************************************
   	 * 				searchInventory()
   	 *****************************************************************
   	 *
   	 *	Parameters: 
   	 *		event: ActionEvent - search button pressed
   	 *	Return Type:
   	 *		none
   	 *	Decorator:
   	 *		FXML 
   	 *	
   	 *	Basic Linear-Search of inventory
   	 *	Displays on the ListView any items found matching the user's
   	 *	input, displays blank otherwise. Handles input errors and can 
   	 *	search for the username, item name, or both. 
   	 *	
   	 *****************************************************************
   	 */
    @FXML
    void searchInventory(ActionEvent event) {
    	
    	//manages lists
    	inventoryList.getItems().clear();
    	obsInventory.clear();
    	
    	//local declarations
    	Alert a = new Alert(AlertType.ERROR);
    	a.setHeaderText("No data found");
    	String userData = userSearch.getText(), itemData = itemSearch.getText(), data = "";
    	
    	//if both fields are empty
    	if (userData.equals("") && itemData.equals("")) {
    		a.setContentText("Please enter something in the search fields");
    		a.show();
    		
    	//if only item is given
    	}else if(userData.equals("")) {
    		for (int i = 0; i < users.size(); i++) {
    			if (items.get(i).equals(itemData)) {
    				data = items.get(i) + " (x" + amounts.get(i) + ") Donated by: " + users.get(i);
    				obsInventory.add(data);
    			}
    		}
    	
    	//if only user name has been given
    	}else if(itemData.equals("")) {
    		for (int i = 0; i < users.size(); i++) {
    			if(users.get(i).equals(userData)) {
    				data = items.get(i) + " (x" + amounts.get(i) + ") Donated by: " + users.get(i);
    				obsInventory.add(data);
    			}
    		}
    	
    	//if both user name and item are given
    	}else if (itemData.length() > 0 && userData.length() > 0){
    		for (int i = 0; i < users.size(); i++) {
    			if (users.get(i).equals(userData) && items.get(i).equals(itemData)) {
    				data = items.get(i) + " (x" + amounts.get(i) + ") Donated by: " + users.get(i);
    				obsInventory.add(data);
    			}
    		}
    		
    	}
    	
    	//sets InventoryList object to the data in the ObservableList object
    	inventoryList.setItems(obsInventory);
    	
    }
	
}

