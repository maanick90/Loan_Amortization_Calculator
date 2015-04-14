package com.utd.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.utd.domain.GraphClass;
import com.utd.domain.ChartClass;

/**
 * Class that listens for the button clicked (graph or chart) and calls the class appropriately
 * OOAD concept implemented in this class 
 * @author Maaniccka
 *
 */
public class ListenForButton implements ActionListener {

	/**
	 * overriding method 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().compareToIgnoreCase("Chart") == 0) {
			new ChartClass(); // To Display Chart
		} else if (e.getActionCommand().compareToIgnoreCase("Graph") == 0) {
			GraphClass.callGraphClass(); // To Display Graph
		}
	}
}