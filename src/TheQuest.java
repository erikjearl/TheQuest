import java.awt.Dimension;
import javax.swing.JFrame;

public class TheQuest {
	JFrame frame;
	GamePanel panel;
	final public static int width = 1000;
	final public static int height = 1000;
	
	
	
	public static void main(String[] args) {
		TheQuest quest = new TheQuest();
		quest.setup();
	}
	
	public TheQuest(){
		frame = new JFrame();
		panel = new GamePanel();
	}
	
	void setup() {
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.pack();
		
		frame.addKeyListener(panel);
		
	}
	
}
