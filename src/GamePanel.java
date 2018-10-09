import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
	 static final int titleScreen = 0;
	 static final int GameScreen = 1;
	 static final int creditScreen = 1;
	 int currentScreen = titleScreen; 
	 Font titleFont;
	 Font subFont;
	 
	 public GamePanel() {
		 titleFont = new Font("Arial",Font.BOLD,48);
		 subFont = new Font("Arial",Font.PLAIN,30);
	}
	 
	 
	 
	void drawTitleScreen(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, TheQuest.width, TheQuest.height);
		
		g.setFont(titleFont);
		g.setColor(Color.CYAN);
		g.drawString("The Quest", 400, 350);
		g.setFont(subFont);
		g.drawString("press space to start", 385, 400);
		
	}
	
	void drawGameScreen(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, TheQuest.width, TheQuest.height);
	}
	
	void drawCreditScreen(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, TheQuest.width, TheQuest.height);
	}
	 
	@Override
	public void paintComponent(Graphics g){
		
		if(currentScreen == titleScreen){
			drawTitleScreen(g);
		  }else if(currentScreen == GameScreen){
			drawGameScreen(g);
		  }else if(currentScreen == creditScreen){
			drawCreditScreen(g);

		}
		
		repaint();
	}
	
	
	
	
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(currentScreen == titleScreen) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				currentScreen++;
			}
		}
		
		if(currentScreen > creditScreen){
			currentScreen = titleScreen;
		}
		
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("paint");
	}
}
