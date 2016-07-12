package application;


import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class CerealChooser extends AnchorPane{

	@FXML
	private TextField amountTField;

	@FXML
	private Slider amountSlider; 

	@FXML
	private ComboBox<String> cerealTypeCb;

	public void initialize(){
		amountTField.textProperty().addListener((observable, oldValue, newValue) -> {
			String amount = amountTField.getText();
			Double d = new Double(amount);
			amountSlider.setValue(d);
		});
		int amount = (int) amountSlider.getValue();
		amountTField.setText(Integer.toString(amount));
		cerealTypeCb.getItems().addAll(
				"Flocken 1",
				"Flocken 2",
				"Flocken 3",
				"Flocken 4",
				"Flocken 5",
				"Flocken 6"
				);

	}

	public void sliderMoved(){
		int amount = (int) amountSlider.getValue();
		amountTField.setText(Integer.toString(amount));
	}


	public double getMax() {
		return amountSlider.getMax();
	}


}
