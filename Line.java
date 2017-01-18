import java.awt.image.BufferedImage;

public class Line {

	int x0, x1, y0, y1, Cx, Cy, offsetX, offsetY;
	BufferedImage image = ImagePanel.img;
	int[] end0 = new int[3];
	int[] end1 = new int[3];
	

	// Constructor
	public Line(int x0, int y0, int x1, int y1) {
		offsetX = image.getWidth() / 2;
		offsetY = image.getHeight() / 2;

		this.x0 = x0 + offsetX;
		this.y0 = y0 + offsetY;
		this.x1 = x1 + offsetX;
		this.y1 = y1 + offsetY;

		Cx = ((this.x0 + this.x1) / 2);
		Cy = ((this.y0 + this.y1) / 2);

		end0[0] = this.x0;
		end0[1] = this.y0;
		end0[2] = 1;

		end1[0] = this.x1;
		end1[1] = this.y1;
		end1[2] = 1;
	}
	
	//getTranslate
	private double[][] getTranslate(double Tx, double Ty) {
		double[][] translate = { { 1, 0, 0 }, { 0, 1, 0 }, { Tx, Ty, 1 } };
		return translate;
	}
	
	//getScale
	private double[][] getScale(double Sx, double Sy) {
		double[][] scale = { { Sx, 0, 0 }, { 0, Sy, 0 }, { 0, 0, 1 } };
		return scale;
	}
	
	//getRotate
	private double[][] getRotate(double angle) {
		double angleR = Math.toRadians(angle);
		double cosX = Math.cos(angleR);
		double sinX = Math.sin(angleR);
		
		double[][] rotate = { { cosX, -sinX, 0 }, { sinX, cosX, 0 }, { 0, 0, 1 } };
		return rotate;
	}

	// BasicTranslate
	public void BasicTranslate(double Tx, double Ty) {
		ApplyTransform(getTranslate(Tx, Ty));
	}

	// BasicScale
	public void BasicScale(double Sx, double Sy) {
		ApplyTransform(getScale(Sx, Sy));
	}

	// BasicRotate
	public void BasicRotate(double angle) {
		ApplyTransform(getRotate(angle));
	}

	// Scale
	public void Scale(double Sx, double Sy, int Cx, int Cy) {
		double[][] result = Concatenate(getTranslate(-Cx, -Cy), getScale(Sx, Sy));
		result = Concatenate(result, getTranslate(Cx, Cy));
		ApplyTransform(result);
	}

	// Rotate
	public void Rotate(double angle, int Cx, int Cy) {
		double[][] result = Concatenate(getTranslate(-Cx, -Cy), getRotate(angle));
		result = Concatenate(result, getTranslate(Cx, Cy));
		ApplyTransform(result);
	}

	// Concatenate
	private double[][] Concatenate(double[][] m1, double[][] m2) {
		int m1Rows = m1.length;
		int m1Cols = m1[0].length;
		int m2Cols = m2[0].length;

		double[][] result = new double[m1Rows][m2Cols];

		for (int i = 0; i < m1Rows; i++) {
			for (int j = 0; j < m2Cols; j++) {
				for (int k = 0; k < m1Cols; k++) {
					result[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}

		return result;
	}

	// ApplyTransform
	private void ApplyTransform(double[][] m) {
		for (int i = 0; i < 3; i++) {
			end0[i] = (int) ((end0[0] * m[0][i]) + (end0[1] * m[1][i]) + (end0[2] * m[2][i]));
			end1[i] = (int) ((end1[0] * m[0][i]) + (end1[1] * m[1][i]) + (end1[2] * m[2][i]));
		}

		x0 = end0[0];
		y0 = end0[1];
		x1 = end1[0];
		y1 = end1[1];
	}

	// toString
	public String toString() {
		return (x0 - offsetX) + " " + (y0 - offsetY) + " " + (x1 - offsetX) + " " + (y1 - offsetY);
	}
}