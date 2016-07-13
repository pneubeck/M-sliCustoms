package application;

import java.io.IOException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainOrderController {

	@FXML
	private Accordion accordion;

	@FXML
	private GridPane cerealGridPane;

	@FXML
	private Button amountContinueButton,addChooserButton,placeOrderBtn, continueBtn;

	@FXML
	private Slider amountSlider;

	@FXML
	private CheckBox  schokoStrauselCheck,cookiesCheck,zartbitterSplitterCheck,walnussCheck,haselnussCheck;

	private int gridCounter;
	private Order order;

	public void initialize(){
		amountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			order.setTotalAmount((Double) newValue);
		});
		accordion.setExpandedPane(accordion.getPanes().get(0));
		gridCounter = 0;
		addChooserClick();
	}

	public void amountContinueButton(){
		order.setTotalAmount(amountSlider.getValue());
		accordion.setExpandedPane(accordion.getPanes().get(1));
	}
	
	public void continueButton(){
		accordion.setExpandedPane(accordion.getPanes().get(2));
	}

	public void addChooserClick(){
		int row = 0;
		int column = 0;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"cerealChooser.fxml"));
		try {
			if(gridCounter>=4){
				JOptionPane.showMessageDialog(null, "You cannot add more types of Müsli.", 
						"Error", JOptionPane.OK_CANCEL_OPTION);
			}else{
				if(gridCounter < 2){
					row = 0;
				}else{
					row = gridCounter/2;
				}
				column = gridCounter-(row*2);
				AnchorPane chooser = fxmlLoader.load();
				cerealGridPane.add(chooser,column,row);
				gridCounter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getAmounts() throws IOException{
		order.emptyProposedAmounts();
		order.emptyTopping();
		Double total = (double) 0;
		for (Node aPane : cerealGridPane.getChildren()){
			Double a =  (double) 0;
			String t ="";
			for (Node node : ((AnchorPane) aPane).getChildren()){
				if(node instanceof Slider ){
					a+=((Slider) node).getValue();
					total+=((Slider) node).getValue();
				}
				if(node instanceof ComboBox ){
					t+=""+((ComboBox<?>) node).getValue();
				}
			}
			if(a != 0 && !t.isEmpty()) {
				order.fillMap(t,a) ;
			}
		}
		if(schokoStrauselCheck.isSelected()) order.fillList(schokoStrauselCheck.getText());
		if(cookiesCheck.isSelected()) order.fillList(cookiesCheck.getText());
		if(zartbitterSplitterCheck.isSelected()) order.fillList(zartbitterSplitterCheck.getText());
		if(walnussCheck.isSelected()) order.fillList(walnussCheck.getText());
		if(haselnussCheck.isSelected()) order.fillList(haselnussCheck.getText());
		if(total > order.getTotalAmount()) {
			calculateProposed(total);
		}
		confirm();
	}

	public void calculateProposed(Double total) {
		HashMap<String, Double> hm = order.getCerealAmount();
		Double ta = order.getTotalAmount();
		Double percent = total/100;
		for (String s : hm.keySet()){
			Double d = hm.get(s);
			Double percentage = d/percent;
			order.fillProposedMap(s, (ta/100)*percentage);
		}
	}

	public void confirm() throws IOException{
		Stage orderStage = new Stage();
		final FXMLLoader guiLoader = new FXMLLoader(getClass().getResource("ConsignementScreen.fxml"));
		AnchorPane root = guiLoader.load();
		Scene scene = new Scene(root,550,550);
		orderStage.setTitle("Order your custom MÜSLI");
		orderStage.setScene(scene);
		orderStage.show();
		ConsignementScreenController controller = (ConsignementScreenController)guiLoader.getController();
		controller.setOrder(order);
		controller.fillLists();
		controller.getData();
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
