package com.utd.domain;

import com.utd.Controller.*;
import com.utd.Entity.DataValuesClass;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;


/**
 * This is the main class to be run by the user.
 * 
 * This class displays a JFrame to the user and get the Principal amount, Terms, No of months from the user, validates them and
 * allows the user to generate Chart and Graph as needed.
 * @author Maaniccka
 */
public class index extends JFrame {

	private static final long serialVersionUID = -8220626466119211678L;		
	private JPanel contentPane;
	public static JTextField txtPrincipal;
	public static JTextField txtRate;
	public static JTextField txtTerms;
	private JButton btnGraph; 
	private JButton btnChart;
	
	private JLabel lblErrorPrincipal;
	private JLabel lblErrorRate;
	private JLabel lblErrorTerms;

	/**
	 * Main function to be run
	 * @param args
	 */
	public static void main(String[] args) {

		index frame = new index();		// create a new JFrame
		frame.setVisible(true);
	}
	
	/**
	 * Default Constructor
	 */
	public index() {
		
		// JFrame and JPanel Details
		setTitle("Loan Amortization Calculator - Revised");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Labels for Principal, Rate, Terms
		JLabel lblPrincipal = new JLabel("Principal(P) :");
		lblPrincipal.setBounds(26, 31, 83, 14);
		contentPane.add(lblPrincipal);
		JLabel lblRate = new JLabel("Rate(R) :");
		lblRate.setBounds(26, 56, 64, 14);
		contentPane.add(lblRate);
		JLabel lblNewLabel = new JLabel("Terms(N) :");
		lblNewLabel.setBounds(26, 81, 83, 14);
		contentPane.add(lblNewLabel);

		// JTextBox to get Principal Value
		txtPrincipal = new JTextField();
		txtPrincipal.setBounds(119, 28, 86, 20);
		ListenForKeys l4keys = new ListenForKeys();
		txtPrincipal.addKeyListener(l4keys);
		txtPrincipal.setColumns(10);
		txtPrincipal.setToolTipText("Enter the Principal Amount");
		contentPane.add(txtPrincipal);
		
		// JTextBox to get Rate Value
		txtRate = new JTextField();
		txtRate.setBounds(119, 53, 86, 20);
		txtRate.addKeyListener(l4keys);
		txtRate.setColumns(10);
		txtRate.setToolTipText("Enter Annual Interest Rate");
		contentPane.add(txtRate);
		
		// JTextBox to get Terms Value
		txtTerms = new JTextField();
		txtTerms.setBounds(119, 78, 86, 20);
		txtTerms.addKeyListener(l4keys);
		txtTerms.setColumns(10);
		txtTerms.setToolTipText("Enter No of Months"); 
		contentPane.add(txtTerms);

		// Button to generate Chart
		btnChart = new JButton("Chart");
		btnChart.setBounds(62, 109, 89, 23);
		btnChart.setEnabled(false);
		ListenForButton l4ButtonChart = new ListenForButton();
		btnChart.addActionListener(l4ButtonChart);
		contentPane.add(btnChart);

		// Button to generate Graph
		btnGraph = new JButton("Graph");
		btnGraph.setBounds(180, 109, 89, 23);
		btnGraph.setEnabled(false);
		btnGraph.addActionListener(l4ButtonChart);
		contentPane.add(btnGraph);
		
		// JLabel to display the suffix '$' for Principal Amount
		JLabel lblDollar = new JLabel("$");
		lblDollar.setBounds(215, 31, 46, 14);
		lblDollar.setFont(new Font(lblDollar.getFont().getName(), Font.PLAIN, lblDollar.getFont().getSize()));
		contentPane.add(lblDollar);
		
		// JLabel to display the suffix '%' for Rate value
		JLabel lblPercentage = new JLabel("%");
		lblPercentage.setBounds(215, 56, 46, 14);
		lblPercentage.setFont(new Font(lblPercentage.getFont().getName(), Font.PLAIN, lblPercentage.getFont().getSize()));
		contentPane.add(lblPercentage);
		
		// JLabel to display the suffix 'mon' for Terms values
		JLabel lblMon = new JLabel("mon");
		lblMon.setBounds(215, 81, 46, 14);
		lblMon.setFont(new Font(lblMon.getFont().getName(), Font.PLAIN, lblMon.getFont().getSize()));
		contentPane.add(lblMon);
		
		// JLabel to display any errors in entering the Principal Amount
		lblErrorPrincipal = new JLabel();
		lblErrorPrincipal.setBounds(254, 28, 200, 14);
		contentPane.add(lblErrorPrincipal);
		
		// JLabel to display any errors in entering the Rate Amount
		lblErrorRate = new JLabel("");
		lblErrorRate.setBounds(254, 53, 200, 14);
		contentPane.add(lblErrorRate);
		
		// JLabel to display any errors in entering the Terms Amount
		lblErrorTerms = new JLabel("");
		lblErrorTerms.setBounds(254, 81, 200, 14);
		contentPane.add(lblErrorTerms);
	}
	
	/**
	 * Class implementing all the methods in the interface KeyListener
	 * @author mxm135730
	 *
	 */
	public class ListenForKeys implements KeyListener
	{

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
		}

		/**
		 * Validation Function
		 * function executed when a number is typed in any of the JTextBox
		 */
		@Override
		public void keyReleased(KeyEvent e) {
			
			int flag = 1;
			String P = txtPrincipal.getText().trim();		// get the Principal Value
			String R = txtRate.getText().trim();			// get the Rate value
			String N = txtTerms.getText().trim();			// get the Terms value
			
			// set the values of all Error Labels to Empty initially
			lblErrorPrincipal.setText("");
			lblErrorRate.setText("");
			lblErrorTerms.setText("");

			// disable the two buttons initially
			btnChart.setEnabled(false);
			btnGraph.setEnabled(false);
			
			if(P.length()==0)											// No Principal amount entered
			{
				lblErrorPrincipal.setText("Enter a value");
				flag = 0;
			}
			if(R.length()==0)											// No Rate value entered											
			{
				lblErrorRate.setText("Enter a value");
				flag = 0;
			}
			if(N.length()==0)											// No of Months not entered
			{
				lblErrorTerms.setText("Enter a value");
				flag = 0;
			}

			if(R.length()!=0 && R.charAt(0) == '.')						// if the rate value starts with '.', then convert it into "0."
				txtRate.setText("0" + txtRate.getText());
				
			for(int i=0,j, dotCount = 0;i<P.length();i++)				// validating the Principal Amount				
			{
				j = (int) P.charAt(i);
				if(P.charAt(i) == '.')
					dotCount++;
				else if(!((j>=48 && j<=57))){								// check if any alphabet is entered
					lblErrorPrincipal.setText("Only Numbers allowed");
					flag = 0;
				}
			if(dotCount == 2) {												// check if two decimal points '.' are entered
					lblErrorPrincipal.setText("Only Decimal allowed");
					flag = 0;
				}
			}
		
			for(int i=0,j, dotCount=0;i<R.length();i++)				// validating the Rate Value
			{
				j = (int) R.charAt(i);
				if(R.charAt(i) == '.')
					dotCount++;
				else if(!((j>=48 && j<=57))){								// check if any alphabet is entered
					lblErrorRate.setText("Only Numbers allowed");
					flag = 0;
				}
				if(dotCount == 2) {											// check if two decimal points '.' are entered
					lblErrorRate.setText("Only Decimal allowed");
					flag = 0;
				}
			}
			
			for(int i=0,j;i<N.length();i++)				// validating the Terms Value
			{
				j = (int) N.charAt(i);
			if(!((j>=48 && j<=57))){										// check if any alphabet is entered
					lblErrorTerms.setText("Only Numbers allowed");
					flag = 0;
				}
			}
		
			if(flag == 1)												// if all validation passes, then enable the 2 buttons
			{
				DataValuesClass.calculate(Double.parseDouble(txtPrincipal.getText()), Integer.parseInt(txtTerms.getText()), Double.parseDouble(txtRate.getText()));
				btnChart.setEnabled(true);
				btnGraph.setEnabled(true);
			}
		}
    }
}
