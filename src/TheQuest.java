import java.awt.Dimension;

import javax.swing.JFrame;

public class TheQuest{
	JFrame frame;
	GamePanel panel;
	final public static int width = 750;
	final public static int height = 750;
	
	DeathListener death = new DeathListener() {
		@Override
		public void restart() {
			// TODO Auto-generated method stub
			panel.removeAll();
			frame.dispose();
			TheQuest quest = new TheQuest();
			quest.setup();
		}};
	
	public static void main(String[] args) {
		TheQuest quest = new TheQuest();
		quest.setup();
	}

	public TheQuest() {
		frame = new JFrame();
		panel = new GamePanel(death);
	}

	void setup() {
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height);
		frame.getContentPane().setPreferredSize(new Dimension(width, height));
		frame.setResizable(false);
		frame.pack();

		frame.addKeyListener(panel);

		panel.startGame();
	}

}
