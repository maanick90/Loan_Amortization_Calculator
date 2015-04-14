package com.utd.domain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.utd.Entity.DataValuesClass;

/**
 * This class generates the Graph which includes both BAR CHART and LINE GRAPH
 * @author Maaniccka
 *
 */
public class GraphClass extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Integer N;
	private Integer PaymentNo[];
	private Double Balance[];

	/**
	 * Parameterized Constructor
	 * @param N
	 * @param PaymentNo
	 * @param Balance
	 */
	public GraphClass(Integer N, Integer PaymentNo[], Double Balance[]) {
		this.N = N;
		this.PaymentNo = PaymentNo;
		this.Balance = Balance;
	}
	
	@Override
	public void paint(Graphics g) {
		
//		double defaultWidth = N*50;
//		double defaultHeight = N*20;
		
// 		Frame Dimension: get the frame dimension 
		Dimension d = getSize();
		double frameWidth = d.getWidth();
		double frameHeight = d.getHeight();
		
// 		Fonts: Set Different Fonts		
		Font font = new Font("SansSerif", Font.BOLD, 20);
		g.setFont(font);
		Font paymentNoFont = new Font("SansSerif", Font.BOLD, 11);
		g.setFont(paymentNoFont);
			

// 		Axis: Draw y-axis and then x-axis		
		double x1 = 20.0;
		double y1 = 30.0;
		double x2 = 20.0;
		double y2 = frameHeight - 50;
		double x3 = frameWidth - 30;
		double y3 = frameHeight - 50;
		double xAxisLength = x3 - x2;
		double yAxisLength = y2 - y1;
		if(yAxisLength <= 0)
			g.drawLine((int) x1, (int) y1, (int) x1, (int) y1);
		else
			g.drawLine((int) x1 - 5, (int) y1, (int) x2 - 5, (int) y2 + 5);
		g.drawLine((int) x2 - 5, (int) y2 + 5, (int) x3, (int) y3 + 5);
		
// 		Origin: Find the Origin's X Position & Y Position		
		double originXPos = 20;
		double originYPos = frameHeight - 50;
// 		Bar: Find the width of each bar and the maxValue of Balance (Obviously the first month remaining balance)		
		double widthEachRectangularBar = xAxisLength / N;
		double maxValue = Balance[0];
		for(int i=1;i<N;i++)
			if(maxValue < Balance[i])
				maxValue = Balance[i];
		
// 		Main Execution: 		
		double barXPos = originXPos;
		double barTopYPos = originYPos;
		double xPrev = 0, yPrev = 0, xNew = 0, yNew = 0;
		for(int i=0;i<N;i++, barXPos = barXPos + widthEachRectangularBar)	// run through the loop
		{
			barTopYPos = yAxisLength / maxValue * Balance[i];
			if(i%2==0)
				g.setColor(Color.red);
			else
				g.setColor(Color.yellow);
			g.fillRect((int) barXPos, (int)(originYPos - barTopYPos), (int)widthEachRectangularBar, (int) barTopYPos);
			g.drawRect((int) barXPos, (int)(originYPos - barTopYPos), (int)widthEachRectangularBar, (int) barTopYPos);
			g.setColor(Color.black);
			g.setFont(paymentNoFont);
			if(N <=60)
			{
				if(PaymentNo[i] == N)
					g.drawString("0", (int)(barXPos + widthEachRectangularBar/2 - 5), (int)(originYPos - barTopYPos-10));
				else
					g.drawString(Balance[i].toString(), (int)(barXPos + widthEachRectangularBar/2 - 5), (int)(originYPos - barTopYPos-10));
				g.drawString(PaymentNo[i].toString(), (int)(barXPos + (widthEachRectangularBar/2)), (int)originYPos + 25);
			}
			
//			Draw the Line
			g.setColor(Color.BLUE);
			xNew = barXPos;
			yNew = originYPos - barTopYPos;
			if(i>0)
				g.drawLine((int) xPrev, (int) yPrev, (int) xNew, (int) yNew);
			xPrev = barXPos;
			yPrev = originYPos - barTopYPos;
		
		}

//		Draw Scale Details
		x1 = frameWidth/2 - 30;
		y1 = frameHeight - 10;
		g.setColor(Color.BLUE);
		g.drawString("Payment No", (int) x1, (int) y1);
		x1 = originXPos - 15;
		y1 = originYPos - yAxisLength - 2;
		g.drawString("Balance", (int) x1, (int) y1);
		
	} // end of overriden Method "paint"

	/**
	 * function that gets called from the Controller when the "Graph" button is clicked in the index JFrame
	 */
	public static void callGraphClass() {
		
		Integer N = Integer.parseInt(index.txtTerms.getText());		// get the Terms value
		int frameHeight1 = 0;
		// JFrame details
		JFrame frame = new JFrame("Displaying Graph (Bar Chart & Line Graph) - Revised");
		if(N<12)
			frameHeight1 = 240;
		else 
			frameHeight1 = N*20;
		frame.setBounds(100, 100, N*50, frameHeight1);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// initialize the dataValues
		Object dataValues[][];
		dataValues = DataValuesClass.getDataValues(); 
		
		// Values to be used in x-axis and y-axis
		Integer PaymentNo[] = new Integer[N];
		Double Balance[] = new Double[N];
		
		// extract the x-axis and y-axis values from dataValues
		for(int i=0;i<N;i++) {
			PaymentNo[i] = i+1;
			Balance[i] = Double.parseDouble(dataValues[i][3].toString());
		}
		
		// add new JPanel
		frame.getContentPane().add(new GraphClass(N,PaymentNo, Balance));
	}
}
