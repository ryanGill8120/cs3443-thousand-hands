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
    	Models.obsInventory.clear();
    	
    	//empty string for transfer from three ArrayList to one ObservableList
    	//this type is required for ListView by JavaFX
    	String data = "";
    	
    	//ArrayLists are same size
    	if (Models.hash.size() > 0) {
	    	for (Entry<String, String> entry: Models.hash.entrySet()) {
	    		
	    		//sets data string by concatenating strings from ArrayLists
	    		data = entry.getKey() + " (x" + entry.getValue() + ")";
	    		Models.obsInventory.add(data);	//adds string to the ObservableList
	    	}
	    	
	    	//sets the ListView based on the ObservableList
	    	inventoryList.setItems(Models.obsInventory);
	    	
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
    	Models.obsInventory.clear();
    	
    	//local declarations
    	Alert a = new Alert(AlertType.ERROR);
    	a.setHeaderText("No data found");
    	String data = itemSearch.getText();
    	
    	if (Models.hash.size() > 0) {
	    	for (Entry<String, String> entry: Models.hash.entrySet()) {
	    		
	    		if(entry.getKey().equals(data)) {
		    		//sets data string by concatenating strings from ArrayLists
		    		data = entry.getKey() + " (x" + entry.getValue() + ")";
		    		Models.obsInventory.add(data);	//adds string to the ObservableList
	    		}
	    	}
	    	
	    	//sets the ListView based on the ObservableList
	    	inventoryList.setItems(Models.obsInventory);
	    	
	    //empty inventory
    	}else {
    		a.setHeaderText("Inventory is empty");
    		a.setContentText("Try donating an item!");
    		a.show();
    	}
    	
    	
    	
    	//sets InventoryList object to the data in the ObservableList object
    	inventoryList.setItems(Models.obsInventory);
    	
    }
	
}
