import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyFrame extends JFrame{
	private GraphicsDemo graphVisual = new GraphicsDemo();
	private static final int WIDTH = 600;
	private static final int HEIGHT = 600;
	public MyFrame(){
		setTitle("Test Frame");
		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(graphVisual);
		setVisible(true);
	}
}