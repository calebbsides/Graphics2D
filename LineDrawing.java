import java.awt.Color;
import java.awt.image.BufferedImage;

public class LineDrawing {

	private static BufferedImage image = ImagePanel.img;
	private static int x0, y0, x1, y1;

	public static void drawLine(Line l, ImagePanel panel) {		
		x0 = l.x0;
		y0 = l.y0;
		x1 = l.x1;
		y1 = l.y1;

		if (x0 == x1) {
			vertical(panel);
		} // if vertical

		if (y0 == y1) {
			horizontal(panel);
		} // if horizontal

		if (x0 == x1 && y0 == y1) {
			setPixel(x0, y0, panel);
		} // if single pixel

		int x = x0;
		int y = y0;

		int w = x1 - x0;
		int h = y1 - y0;
		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;

		dx1 = (w < 0) ? -1 : 1;
		dy1 = (h < 0) ? -1 : 1;
		dx2 = dx1;

		int longest = Math.abs(w);
		int shortest = Math.abs(h);

		if (!(longest > shortest)) {
			longest = Math.abs(h);
			shortest = Math.abs(w);
			if (h < 0)
				dy2 = -1;
			else if (h > 0)
				dy2 = 1;
			dx2 = 0;
		}

		int numerator = longest >> 1;

		for (int i = 0; i <= longest; i++) {
			setPixel(x, y, panel);

			numerator += shortest;

			if (numerator >= longest) {
				numerator -= longest;
				x += dx1;
				y += dy1;
			} else {
				x += dx2;
				y += dy2;
			} // if else
		} // for

	}// drawLine

	public static void vertical(ImagePanel panel) {
		if ((y1 - y0) < 0) {
			int tmp = y0;
			y0 = y1;
			y1 = tmp;
		}

		for (int i = y0; i < y1; i++) {
			setPixel(x0, i, panel);
		}
	}// vertical

	public static void horizontal(ImagePanel panel) {
		if ((x1 - x0) < 0) {
			int tmp = x0;
			x0 = x1;
			x1 = tmp;
		}

		for (int i = x0; i < x1; i++) {
			setPixel(i, y0, panel);
		}
	}// horizontal

	private static void setPixel(int x, int y, ImagePanel panel) {
		if(!Viewport.hasViewPort) {
			try {
				image.setRGB(y, x, Color.GREEN.getRGB());
			} catch(Exception e) {}
		}
		panel.repaint();
	}// setPixel

}
