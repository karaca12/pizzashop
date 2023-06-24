package pizza_shop;
import javax.swing.*;

public class Funghi extends Selection {
	private boolean cheese; 

	public Funghi(String size,int amount,Main lbl) {
		super(size,amount);
		if(JOptionPane.showConfirmDialog(lbl, "Would you like add double cheese to your Funghi Pizza?","Cheese",JOptionPane.YES_NO_OPTION)==0) cheese=true;
		else cheese=false;
		double price;
		if(size.equals("Small")) price=50;
		else if(size.equals("Medium")) price=75;
		else price=100;
		if(cheese) price*=1.25;
		setPrice(price);
	}

	@Override
	public String toString() {
		if(cheese) return super.toString()+" Double Cheese Funghi Pizza(s)";
		else return super.toString()+" Funghi Pizza(s)";
	}
}