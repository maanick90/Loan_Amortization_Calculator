package com.utd.Entity;

import java.text.DecimalFormat;

/**
 * Class which stores all the data (principal amount, interest amount and remaining balance) for each month in a 2D array
 * The values in the array are then displayed in the form of a chart to the user. 
 * @author Maaniccka
 *
 */
public class DataValuesClass {
	
	private static Object dataValues[][];
	
	public static void calculate(double P, int N, double R)
	{
		R = R/1200;
		dataValues = new Object[N][4];					// set the Size
		
		Integer paymentNo = 1;
		double temp = Math.pow(1+R, N);
		double temp1 = ( (R * temp) / (temp - 1) );
		double monthlyPayment = P * temp1;
		double IA;
		
		// To format the decimal value. Eg. 56.36246 will be converted to 56.36
		DecimalFormat df = new DecimalFormat("0.00");

		for(int i=0; i<N; i++, paymentNo++)
		{
			dataValues[i][0] = paymentNo;		// Payment No
			
			IA = P * R;
			dataValues[i][2] = IA;				// Interest Used

			dataValues[i][1] = (monthlyPayment) - Double.parseDouble(dataValues[i][2].toString()); // Principal Amount

			P = P - Double.parseDouble(dataValues[i][1].toString());		
			dataValues[i][3] = P;														// Remaining Balance
			
			// format all the values
			dataValues[i][1] = df.format(Math.round(Double.parseDouble((dataValues[i][1]).toString()) * 100.0) / 100.0);
			dataValues[i][2] = df.format(Math.round(Double.parseDouble((dataValues[i][2]).toString()) * 100.0) / 100.0);
			dataValues[i][3] = df.format(Math.round(Double.parseDouble((dataValues[i][3]).toString()) * 100.0) / 100.0);
		}
		dataValues[N-1][3] = 0.0;					// set the Remaining Balance of the last Payment No
	}

	public static Object[][] getDataValues() {
		return dataValues;
	}
}
