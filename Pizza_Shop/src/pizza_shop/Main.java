package pizza_shop;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


public class Main extends JFrame implements ActionListener{
	int amount;
	private static final long serialVersionUID = 1L;
	private JLabel sizL,seleL,piecL,listL;
	private JComboBox<String> size;
	private JRadioButton margR,pepR,musR,driR;
	private ButtonGroup buttonS;
	private JTextField piecT;
	private JButton buttonA,buttonO;
	
	ArrayList<Selection> selectionList = new ArrayList<Selection>();
	
	public Main() { 
		setLayout(null);
		setSize(600,400);
		setLocationRelativeTo(null);
		setTitle("Order");
		init();
		buttonA.addActionListener(this); 
		buttonO.addActionListener(this);
		setVisible(true);
	}
	
	public void init() {
		sizL = new JLabel("Select size:");
		sizL.setSize(250, 50);
		sizL.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		sizL.setLocation(75, 10);
		add(sizL);
		
		String[] sizes = {"Small","Medium","Large"};
		size = new JComboBox<String>(sizes);
		size.setSelectedIndex(0);
		size.setSize(150, 25);
		size.setLocation(200, 25);
		add(size);
		
		seleL = new JLabel("Select what you want to order:"); 
		seleL.setSize(500, 50);
		seleL.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		seleL.setLocation(75, 55);
		add(seleL);
		
		buttonS = new ButtonGroup();
		
		
		margR = new JRadioButton("Margarita");
		margR.setSize(100, 50);
		margR.setLocation(80, 90);
		add(margR);
		
		pepR = new JRadioButton("Pepperoni");
		pepR.setSize(100, 50);
		pepR.setLocation(80, 130);
		add(pepR);
		
		musR = new JRadioButton("Funghi");
		musR.setSize(100, 50);
		musR.setLocation(200, 90);
		add(musR);
		
		driR = new JRadioButton("Drink");
		driR.setSize(100, 50);
		driR.setLocation(200, 130);
		add(driR);
	
		buttonS.add(margR);
		buttonS.add(pepR);
		buttonS.add(musR);
		buttonS.add(driR);
		
		piecL = new JLabel("Type how many pieces you want to order:");
		piecL.setSize(500, 50);
		piecL.setFont(new Font(Font.DIALOG, Font.BOLD, 15));
		piecL.setLocation(75, 165);
		add(piecL);
		
		piecT = new JTextField();
		piecT.setSize(300, 25);
		piecT.setLocation(75, 210);
		add(piecT);
		
		buttonA = new JButton("Add");
		buttonA.setSize(120, 40);
		buttonA.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		buttonA.setLocation(75, 250);
		add(buttonA);
		
		buttonO = new JButton("Order");
		buttonO.setSize(120, 40);
		buttonO.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		buttonO.setLocation(280, 250);
		buttonO.setEnabled(false);
		add(buttonO);
		
		listL = new JLabel();
		listL.setSize(500, 50);
		listL.setLocation(75, 290);
		add(listL);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sizer = (String)size.getSelectedItem();
		if(e.getSource().equals(buttonA)) {
			if( driR.isSelected() || pepR.isSelected() || musR.isSelected() || margR.isSelected() && !(piecT.getText().isEmpty())) {
				try {
					amount  = Integer.parseInt(piecT.getText().trim());
					Selection ord;
					if(driR.isSelected())  { ord = new Drink(sizer,amount,this); }
					else if(margR.isSelected())  { ord = new Margarita(sizer,amount,this); }
					else if(pepR.isSelected())    { ord = new Pepperoni(sizer,amount,this); }
					else { ord = new Funghi(sizer,amount,this); }
					piecT.setText(null);
					selectionList.add(ord);
					listL.setText(ord.toString()+" added");
					buttonS.clearSelection();
					buttonO.setEnabled(true);
				}
				catch(NumberFormatException e1) {

					JOptionPane.showMessageDialog(this, "Enter an integer as amount");
				}
			}	
			else { JOptionPane.showMessageDialog(this, "Select what you want to order and enter an amount"); 


			}
		}
		if(e.getSource().equals(buttonO)) {
			String report = "";
			double cost=0.0;
			for(int i=0;i<selectionList.size();i++) {
				Selection k = selectionList.get(i);
				report += k.toString();
				double tpoforder = k.getAmount() * k.getPrice();
				cost += tpoforder;
				report = report + " - "+tpoforder+" TL\n";
			}
			JOptionPane.showMessageDialog(this, report);
			JOptionPane.showMessageDialog(this,	 "You should pay "+cost+" TL");
			listL.setText(null);
			buttonO.setEnabled(false);
			selectionList.clear();
		}	
	}

	public static void main(String[] args) {
		new Main();
	}
}
