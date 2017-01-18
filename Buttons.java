import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;

public class Buttons {

	SidePanel sidePanel;
	static ImagePanel imagePanel;
	static ArrayList<Line> dataLines = new ArrayList<Line>();
	static Scanner input = new Scanner(System.in);

	// Rotate
	public static JButton Rotate() {
		JButton rotateButton = new JButton("Rotate");

		rotateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.print("Angle: ");
				int angle = input.nextInt();

				for (Line l : dataLines) {
					l.Rotate(angle, l.Cx, l.Cy);
				}

			}
		});
		return rotateButton;
	}

	// Translate
	public static JButton Translate() {
		JButton translateButton = new JButton("Translate");
		
		translateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.print("Tx: ");
				int Tx = input.nextInt();
				System.out.print("Ty: ");
				int Ty = input.nextInt();

				for (Line l : dataLines) {
					l.BasicTranslate(Tx, Ty);
				}

			}
		});

		return translateButton;
	}

	// Scale
	public static JButton Scale() {
		JButton scaleButton = new JButton("Scale");

		scaleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.print("Sx: ");
				int Sx = input.nextInt();
				System.out.print("Sy: ");
				int Sy = input.nextInt();

				for (Line l : dataLines) {
					l.Scale(Sx, Sy, l.Cx, l.Cy);
				}

			}
		});

		return scaleButton;
	}

	// ViewPort
	public static JButton AddLine() {
		JButton viewPortButton = new JButton("Add a Line");

		viewPortButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.print("x0: ");
				int x0 = input.nextInt();
				System.out.print("y0: ");
				int y0 = input.nextInt();
				System.out.print("x1: ");
				int x1 = input.nextInt();
				System.out.print("y1: ");
				int y1 = input.nextInt();
				
				Line l = new Line(x0, y0, x1, y1);
				dataLines.add(l);
			}
		});
		
		return viewPortButton;
	}

	// Input
	public static JButton Input() {
		JButton inputButton = new JButton("Input File");
		
		inputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BufferedReader br = null;
				String s;
				
				FileReader fr;
				try {
					fr = new FileReader("input.txt");
					br = new BufferedReader(fr);
					
					while((s = br.readLine()) != null) {
						s.trim();
						String[] ends = s.split(" ");
						int x0, y0, x1, y1;
						
						x0 = Integer.parseInt(ends[0]);
						y0 = Integer.parseInt(ends[1]);
						x1 = Integer.parseInt(ends[2]);
						y1 = Integer.parseInt(ends[3]);

						Line l = new Line(x0, y0, x1, y1);
						
						dataLines.add(l);
					}
					
				} catch (IOException err) {
					err.printStackTrace();
				}
			}
		});
		return inputButton;
	}

	public static JButton Output() {
		JButton outputButton = new JButton("Output File");

		outputButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				    PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
				    
				    for(Line l : dataLines) {
				    	writer.println(l);
				    }
				    
				    System.out.println("Output Successful");
				    writer.close();
				} catch (Exception err) {
					err.printStackTrace();
				}
			}
		});
		
		return outputButton;
	}
	
	public static JButton Draw() {
		JButton drawButton = new JButton("Draw Lines");
		
		drawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dataLines.isEmpty()) {
					System.out.println("No Lines To Draw!");
				} else {
					drawLines();	
				}
			}
		});
		
		return drawButton;
	}
	
	public static JButton Clear() {
		JButton clearButton = new JButton("Clear");
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imagePanel.clear();
				dataLines = new ArrayList<Line>();
			}
		});
		
		return clearButton;
	}

	public static void addButtons(SidePanel sidePanel, ImagePanel imagePanel) {
		Buttons.imagePanel = imagePanel;
		
		sidePanel.add(AddLine());
		sidePanel.add(Input());
		sidePanel.add(Output());
		sidePanel.add(Translate());
		sidePanel.add(Scale());
		sidePanel.add(Rotate());
		sidePanel.add(Draw());
		sidePanel.add(Clear());
	}
	
	private static void drawLines() {
		for(Line l : dataLines) {
			LineDrawing.drawLine(l, imagePanel);
		}
	}
}
