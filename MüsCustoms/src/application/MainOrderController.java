package application;

import java.awt.Checkbox;
import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class MainOrderController {
	
	@FXML
	private Accordion accordion;
	
	@FXML
	private GridPane cerealGridPane;
	
	@FXML
	private Button amountContinueButton,addChooserButton;
	
	@FXML
	private javafx.scene.control.CheckBox schokoSträuselCheck,cookiesCheck,zartbitterSplitterCHeck,walnussCheck,haselnussCheck;
	
	private int gridCounter;
	
	public void initialize(){
		accordion.setExpandedPane(accordion.getPanes().get(0));
		gridCounter = 0;
		addChooserClick();
	}
	 
	public void amountContinueButton(){
		accordion.setExpandedPane(accordion.getPanes().get(1));
	}
	
	public void addChooserClick(){
		int row = 0;
		int column = 0;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"cerealChooser.fxml"));
		try {
			if(gridCounter>=5){
				JOptionPane.showMessageDialog(null, "Sie können keine Sorten mehr hinzufügen.", 
						"Fehler beim hinzufügen einer neuen Sorte", JOptionPane.OK_CANCEL_OPTION);
			}else{
				if(gridCounter < 2){
					row = 0;
				}else{
					row = gridCounter/2;
				}
				column = gridCounter-(row*2);
				cerealGridPane.add(fxmlLoader.load(),column,row);
				gridCounter++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
