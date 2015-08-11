package ninefoo.application;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import ninefoo.lib.pert.Panel.PertPanel;
import ninefoo.lib.pert.Shape.PertShape;

public class PertDriver {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pert");
		PertPanel pertPanel = new PertPanel(8);
		
		PertShape p0 = pertPanel.addNode();
		PertShape p1 = pertPanel.addNode();
		PertShape p2 = pertPanel.addNode();
		PertShape p3 = pertPanel.addNode();
		PertShape p4 = pertPanel.addNode();
		PertShape p5 = pertPanel.addNode();
		PertShape p6 = pertPanel.addNode();
		PertShape p7 = pertPanel.addNode();
		
		p0.eventNumber = 1;
		p0.expectedDate = 1.2;
		
		pertPanel.addEdge(p0, p1);
		pertPanel.addEdge(p0, p2);
		pertPanel.addEdge(p0, p3);

		pertPanel.addEdge(p1, p4);
		pertPanel.addEdge(p2, p4);

		pertPanel.addEdge(p3, p5);
		
		pertPanel.addEdge(p4, p6);
		pertPanel.addEdge(p5, p6);

		pertPanel.addEdge(p6, p7);

		pertPanel.prettify();
		
		frame.add(pertPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500,500));
		frame.setLocationRelativeTo(null);
	}
}
