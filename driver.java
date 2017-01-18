import java.awt.BorderLayout;

import javax.swing.JFrame;

public class driver {
	
	public static void main(String[] args) {
		
		int width = 500;
		int height = 500;
		
		JFrame frame = new JFrame("GraphiX");
		ImagePanel imagePanel = new ImagePanel(width, height);
		SidePanel sidePanel = new SidePanel(150, height);
		Buttons.addButtons(sidePanel, imagePanel);
		
		frame.add(sidePanel, BorderLayout.WEST);
		frame.add(imagePanel, BorderLayout.EAST);
		
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
