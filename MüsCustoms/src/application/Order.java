package application;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
	private String userName;
	private Double totalPrice;
	private Double totalAmount;
	private HashMap<String, Double> cerealAmount = new HashMap<String, Double>();
	private HashMap<String, Double> proposedCerealAmount = new HashMap<String, Double>();
	private ArrayList<String> toppings = new ArrayList<String>();

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public HashMap<String, Double> getCerealAmount() {
		return cerealAmount;
	}

	public void setCerealAmount(HashMap<String, Double> cerealAmount) {
		this.cerealAmount = cerealAmount;
	}

	public ArrayList<String> getToppings() {
		return toppings;
	}

	public void setToppings(ArrayList<String> toppings) {
		this.toppings = toppings;
	}

	public HashMap<String, Double> getProposedCerealAmount() {
		return proposedCerealAmount;
	}

	public void setProposedCerealAmount(HashMap<String, Double> proposedCerealAmount) {
		this.proposedCerealAmount = proposedCerealAmount;
	}
	
	public void fillMap(String cerealType, Double cerealAmount) {
		this.cerealAmount.put(cerealType, cerealAmount);
	}
	
	public void fillProposedMap(String cerealType, Double cerealAmount) {
		this.proposedCerealAmount.put(cerealType, cerealAmount);
	}
	
	public void fillList(String topping) {
		this.toppings.add(topping);
	}
	
	public void emptyTopping() {
		toppings.clear();
	}

	@Override
	public String toString() {
		return "Order [userName=" + userName + ", totalPrice=" + totalPrice + ", totalAmount=" + totalAmount
				+ ", cerealAmount=" + cerealAmount + ", proposedCerealAmount=" + proposedCerealAmount + ", toppings="
				+ toppings + "]";
	}

	public void emptyProposedAmounts() {
		proposedCerealAmount.clear();
	}
}
