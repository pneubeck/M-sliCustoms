package application;

import java.io.IOException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class MainOrderController {

	@FXML
	private Accordion accordion;

	@FXML
	private GridPane cerealGridPane;

	@FXML
	private Button amountContinueButton,addChooserButton;

	@FXML
	private Slider amountSlider;

	@FXML
	private CheckBox  schokoStrauselCheck,cookiesCheck,zartbitterSplitterCheck,walnussCheck,haselnussCheck;

	private int gridCounter;

	private Order order;

	//private double remainingWeight=2000;

	public void initialize(){
		amountSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			order.setTotalAmount((Double) newValue);
			//			System.out.println("textfield changed from " + oldValue + " to " + newValue);
		});
		accordion.setExpandedPane(accordion.getPanes().get(0));
		gridCounter = 0;
		addChooserClick();
	}

	public void amountContinueButton(){
		//		System.out.println("from main: "+order.getUserName());
		order.setTotalAmount(amountSlider.getValue());
		//		System.out.println("from main: "+order.getTotalAmount());
		//remainingWeight = order.getTotalAmount();
		accordion.setExpandedPane(accordion.getPanes().get(1));
	}

	public void addChooserClick(){
		int row = 0;
		int column = 0;
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"cerealChooser.fxml"));
		try {
			if(gridCounter>=4){
				JOptionPane.showMessageDialog(null, "Sie können keine Sorten mehr hinzufügen.", 
						"Fehler beim hinzufügen einer neuen Sorte", JOptionPane.OK_CANCEL_OPTION);
			}else{
				if(gridCounter < 2){
					row = 0;
				}else{
					row = gridCounter/2;
				}
				column = gridCounter-(row*2);
				AnchorPane chooser = fxmlLoader.load();
				////				for (Node node : chooser.getChildren()){
				////				System.out.println(node.getClass());
				////				if(node instanceof Slider ){
				////					System.out.println("geilo");
				////					((Slider) node).setMax(remainingWeight);
				////					System.out.println(((Slider) node).getValue());
				////				}
				//				
				////				System.out.println(chooser.getMax());
				cerealGridPane.add(chooser,column,row);
				gridCounter++;
				System.out.println(cerealGridPane.getChildren());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getAmounts(){
		order.emptyTopping();
		Double total = (double) 0;
		for (Node aPane : cerealGridPane.getChildren()){
			Double a =  (double) 0;
			String t ="";
			for (Node node : ((AnchorPane) aPane).getChildren()){
				if(node instanceof Slider ){
					a+=((Slider) node).getValue();
					total+=((Slider) node).getValue();
					//					System.out.println("geilo");
					//					((Slider) node).getValue();
					//					System.out.println(((Slider) node).getValue());
				}
				if(node instanceof ComboBox ){
					t+=""+((ComboBox<?>) node).getValue();
					//					System.out.println("geilo");
					//					((Slider) node).getValue();
					//					System.out.println(((ComboBox<?>) node).getValue());
				}
			}
			if(a != 0 && !t.isEmpty()) {
				order.fillMap(t,a) ;
				System.out.println(a+" "+t);
			}
		}
		if(schokoStrauselCheck.isSelected()) order.fillList(schokoStrauselCheck.getText());
		if(cookiesCheck.isSelected()) order.fillList(cookiesCheck.getText());
		if(zartbitterSplitterCheck.isSelected()) order.fillList(zartbitterSplitterCheck.getText());
		if(walnussCheck.isSelected()) order.fillList(walnussCheck.getText());
		if(haselnussCheck.isSelected()) order.fillList(haselnussCheck.getText());

		if(total > order.getTotalAmount()) {
		System.out.println(total);
		System.out.println(order.getTotalAmount());
		crazyShit(total);
		}



		System.out.println(order);

	}
	public void crazyShit(Double total) {
		HashMap<String, Double> hm = order.getCerealAmount();
		Double ta = order.getTotalAmount();
		Double percent = total/100;
		for (String s : hm.keySet()){
			Double d = hm.get(s);
			Double percentage = d/percent;
			order.fillProposedMap(s, (ta/100)*percentage);
		}
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	//	public double getRemainingWeight() {
	//		return remainingWeight;
	//	}
	//
	//	public void setRemainingWeight(double remainingWeight) {
	//		this.remainingWeight = remainingWeight;
	//	}
}
