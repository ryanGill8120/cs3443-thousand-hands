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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import model.Model;

public class InventoryController {
	
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
    	
    	//empty string for transfer from HashMap to ObservableList
    	//this type is required for ListView by JavaFX
    	String data = "";
    	
    	//if there is inventory
    	if (Model.hash.size() > 0) {
	    	for (Entry<String, String> entry: Model.hash.entrySet()) {
	    		
	    		//sets data string by concatenating strings from HashMap key and value
	    		data = entry.getKey() + " (x" + entry.getValue() + ")";
	    		Model.obsInventory.add(data);	//adds string to the ObservableList
	    	}
	    	
	    	//sets the ListView based on the ObservableList
	    	inventoryList.setItems(Model.obsInventory);
	    	
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
   	 *	Basic Search of inventory using HashMap
   	 *	Displays on the ListView any items found matching the user's
   	 *	input, displays blank otherwise. Handles input errors and can 
   	 *	searches for the item name 
   	 *	
   	 *****************************************************************
   	 */
    @FXML
    void searchInventory(ActionEvent event) {
    	
    	//manages lists
    	inventoryList.getItems().clear();
    	Model.obsInventory.clear();
    	
    	//local declarations
    	Alert a = new Alert(AlertType.ERROR);
    	a.setHeaderText("No data found");
    	String data = itemSearch.getText();
    	
    	//Checks if item is in inventory
    	int amount = Model.getNumberOfItemsInInventory(data);
    	if (amount > 0) {
    		data += " (x" + amount + ")";
    		Model.obsInventory.add(data);
    	}else {
    		a.setHeaderText("Item not found");
    		a.setContentText("Please search again");
    		a.show();
    	}
    	
    	
    	//sets InventoryList object to the data in the ObservableList object
    	inventoryList.setItems(Model.obsInventory);
    	
    }
	
}
