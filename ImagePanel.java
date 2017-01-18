import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {

	int width, height;
	public static BufferedImage img;
	public static Graphics2D g2d;
	
	public ImagePanel(int width, int height) {
		this.width = width;
		this.height = height;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		AffineTransform at = new AffineTransform();
		at.translate(width/2, height/2);
		at.rotate(Math.toRadians(-90));
		at.translate(-width/2, -height/2);
		g2d.drawImage(img, at, null);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}
	
	public void clear() {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				img.setRGB(i, j, Color.BLACK.getRGB());
			}
		}
		this.repaint();
	}
}
