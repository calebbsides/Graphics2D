import java.awt.Dimension;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SidePanel extends JPanel {
	
	int width, height;
	
	public SidePanel(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

}
