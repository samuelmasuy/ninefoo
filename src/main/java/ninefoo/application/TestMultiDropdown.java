package ninefoo.application;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ninefoo.lib.multiDropdown.MultiDropdown;

public class TestMultiDropdown {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		JPanel orgPanel = new JPanel();
		final MultiDropdown panel = new MultiDropdown("Add dependency", new String[]{"One", "Two"});
		panel.setPreferredSize(new Dimension(250,200));
		panel.setBackground(Color.RED);
		orgPanel.add(panel);
		frame.add(orgPanel);
		
		panel.addDropdown("One");
		panel.addDropdown("Test");
		panel.addDropdown("Two");
		panel.addDropdown("One");
		
		String a = "1";
		Double.parseDouble(a);
		
		new Thread(){
			public void run() {
				while(true){
					System.out.println(Arrays.toString(panel.getData()));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
		}.start();
		
		// Configure
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(300, 300));
		frame.setVisible(true);
		frame.pack();
	}
}
