package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML 
	private Button loginButton;
	
	@FXML
	private ImageView logoView;
	
	public void initialize(){
	}
	
	public void start(){
		loginButton.requestFocus();
	}
	
	public void login() throws IOException{
		//login logic goes here----------------
		
		//-------------------------------------
		
		//loading the MainOrderScreen and disposing the loginScreen--------------------------------------------------------------------
		Stage orderStage = new Stage();
		final FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("MainOrderScreen.fxml"));
		AnchorPane root = guiLoader.load();
		Scene scene = new Scene(root,550,550);
		orderStage.setTitle("Order your custom MÜSLI");
		orderStage.setScene(scene);
		orderStage.show();
		Stage curStage = (Stage)loginButton.getScene().getWindow(); //Getting the login-Stage
		curStage.close(); //Closing the loginStage when the login was succesful
		MainOrderController controller = (MainOrderController)guiLoader.getController(); //Loading the Controller for the OrderStage
		//-----------------------------------------------------------------------------------------------------------------------------
	}

}
