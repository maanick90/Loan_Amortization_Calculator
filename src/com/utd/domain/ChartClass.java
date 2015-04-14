package com.utd.domain;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.utd.Entity.DataValuesClass;

import java.awt.BorderLayout;

/**
 * Class to display the CHART containing Payment No, Principal Used, Interest Amount, Remaining Balance
 * @author Maaniccka
 *
 */
public class ChartClass extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPanel panel1;
	private JTable table;
	private Object dataValues[][];			// to store the values to be displayed in the chart

	/**
	 * Default Constructor
	 */
	public ChartClass() {
		
		// JFrame and JPanel Details
		setTitle("Displaying Chart - Revised");
		setBounds(600, 100, 450, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		getContentPane().add(panel1);
		
		// Column Title
		String columnNames[] = { "Payment No", "Principal Used", "Interest Amount", "Remaining Balance" };
		dataValues = DataValuesClass.getDataValues();
		
		// Create a new table instance
		table = new JTable( dataValues, columnNames );
		
		// Add the table to a scrolling pane
		JScrollPane scrollPane = new JScrollPane(table);
		panel1.add(scrollPane);
		
		setVisible(true);			// set the JFrame Visible
		
	}
	
	/**
	 * getter function for dataValues
	 * @return
	 */
	public Object[][] getDataValues() {
		return dataValues;
	}
}
