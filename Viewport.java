
public class Viewport {
	
	static int Vx0, Vy0, Vx1, Vy1;
	private static Line top, bottom, left, right;
	public static boolean hasViewPort = false;
	
	public static void drawViewport(int Vx0, int Vy0, int Vx1, int Vy1, ImagePanel panel) {
		Viewport.Vx0 = Vx0;
		Viewport.Vy0 = Vy0;
		Viewport.Vx1 = Vx1;
		Viewport.Vy1 = Vy1;
		
		top = new Line(Vx0, Vy1, Vx1, Vy1);
		LineDrawing.drawLine(top, panel);
		
		bottom = new Line(Vx0, Vy0, Vx0, Vy1);
		LineDrawing.drawLine(bottom, panel);
		
		left = new Line(Vx0, Vy0, Vx1, Vy0);
		LineDrawing.drawLine(left, panel);
		
		right = new Line(Vx1, Vy0, Vx1, Vy1);
		LineDrawing.drawLine(right, panel);
		
		hasViewPort = true;
	}
	
}
