package application;


import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CerealChooser extends AnchorPane{
	
	@FXML
	private TextField amountTField;
	
	@FXML
	private Slider amountSlider; 
	
	public void initialize(){
		
	}
	
	public void sliderMoved(){
		int amount = (int) amountSlider.getValue();
		amountTField.setText(Integer.toString(amount));
	}
	
	public void textfieldChanged(){
		String amount = amountTField.getText();
		Double d = new Double(amount);
		amountSlider.setValue(d);
	}
}
