package pizza_shop;
import javax.swing.*;

public class Drink extends Selection {
	private String drinko;
	String[] drinks = {"Water","Fanta","Pepsi"};

	public Drink(String size,int amount,Main lbl) {
		super(size,amount);
		try {
			drinko = (String) JOptionPane.showInputDialog(lbl,"Select a drink.","Select a drink",
					JOptionPane.QUESTION_MESSAGE,null,drinks,drinks[0]);
			
			double price;
			if(size.equals("Small")) price=3;
			else if(size.equals("Medium")) price=6;
			else price=9.0;
			if(drinko.equals("Fanta")) price*=2;
			if(drinko.equals("Pepsi")) price*=2.5;
			setPrice(price);
		}
		catch(NullPointerException e) {
		}
	}
	
	@Override
	public String toString() {
		return super.toString()+drinko+"(s)";
	}
}
