package ninefoo.application;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import ninefoo.lib.pert.Panel.PertPanel;
import ninefoo.lib.pert.Shape.PertShape;

public class PertDriver {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pert");
		PertPanel pertPanel = new PertPanel();
		
//		pertPanel.addNodeMode();
		PertShape p1 = pertPanel.addNode(new Point(100, 100));
		PertShape p2 = pertPanel.addNode(new Point(500, 500));
		pertPanel.addEdge(p1, p2);
		
		pertPanel.dragNodeMode();
		
		frame.add(pertPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500,500));
		frame.setLocationRelativeTo(null);
	}
}
