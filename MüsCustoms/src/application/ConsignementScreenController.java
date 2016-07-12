package application;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ConsignementScreenController {

	@FXML
	private Label extra1,extra2,extra3,extra4,extra5,extra6,
	placeholder1,placeholder11,placeholder12,placeholder13,placeholder14,
	flockenSlot1,flockenSlot2,flockenSlot3,flockenSlot4;

	private ArrayList<Label> flockenSlots =  new ArrayList<Label>();
	private ArrayList<Label> extras =  new ArrayList<Label>();
	private ArrayList<Label> placeholders =  new ArrayList<Label>();

	private Order order;

	public void initialize(){
	}

	public void fillLists() {
		flockenSlots =  new ArrayList<Label>();
		extras =  new ArrayList<Label>();
		placeholders =  new ArrayList<Label>();
		flockenSlots.add(flockenSlot1);
		flockenSlots.add(flockenSlot2);
		flockenSlots.add(flockenSlot3);
		flockenSlots.add(flockenSlot4);
		extras.add(extra1);
		extras.add(extra2);
		extras.add(extra3);
		extras.add(extra4);
		extras.add(extra5);
		extras.add(extra6);
		placeholders.add(placeholder11);
		placeholders.add(placeholder12);
		placeholders.add(placeholder13);
		placeholders.add(placeholder14);
	}

	public void getData() {
		placeholder1.setText(order.getTotalAmount()+" g");
		placeholder1.setVisible(true);
		int c1=0, c2=0, c3=0;
		for(String s : order.getCerealAmount().keySet()){
			flockenSlots.get(c1).setText(s);
			flockenSlots.get(c1).setVisible(true);
			c1++;
		}
//		System.out.println(order.getProposedCerealAmount());
		if(order.getProposedCerealAmount().isEmpty()){
			for(Double d : order.getCerealAmount().values()){
				placeholders.get(c2).setText(d+" g");
				placeholders.get(c2).setVisible(true);
				c2++;
			}
		}
		if(!order.getProposedCerealAmount().isEmpty()){
			for(Double d : order.getProposedCerealAmount().values()){
				placeholders.get(c2).setText(d.intValue()+" g");
				placeholders.get(c2).setVisible(true);
				c2++;
			}
		}
		for(String s : order.getToppings()){
			extras.get(c3).setText(s);
			extras.get(c3).setVisible(true);
			c3++;
		}

	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	} 
}
