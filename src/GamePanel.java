import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
	Timer timer;
	
	
	static final int titleScreen = 0;
	 static final int GameScreen = 1;
	 static final int creditScreen = 1;
	 int currentScreen = titleScreen; 
	int currentAreaX =1;
	int currentAreaY =1;
	BufferedImage[][] BGImage;
	 
	 
	 Font titleFont;
	 Font subFont;
	 
	 Player player = new Player(475, 475, 50, 50);
	 boolean up;
 	 boolean down;
     boolean left;
	 boolean right;
	 
	 public static BufferedImage GreenRoom;
	 public static BufferedImage RedRoom;
	 
	 public GamePanel() {
		 titleFont = new Font("Arial",Font.BOLD,48);
		 subFont = new Font("Arial",Font.PLAIN,30);
		 timer = new Timer(1000/60, this);
		 
		 try {
			GreenRoom = ImageIO.read(this.getClass().getResourceAsStream("GreenRoom.png"));
			RedRoom = ImageIO.read(this.getClass().getResourceAsStream("RedRoom.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 BGImage = new BufferedImage[3][3];
		 BGImage[0][1] = RedRoom;
		 BGImage[1][0] = RedRoom;
		 BGImage[1][1] = GreenRoom;
		 BGImage[1][2] = RedRoom;
		 BGImage[2][1] = RedRoom;
		 
	}
	 
	 void startGame() {
		 timer.start();
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
		g.drawImage(BGImage[currentAreaX][currentAreaY], WIDTH, HEIGHT, null);		
		
		//g.setColor(Color.GREEN);
		//g.fillRect(0, 0, TheQuest.width, TheQuest.height);
		
		player.draw(g);
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
		
		if(e.getKeyCode() == KeyEvent.VK_W ) {
			//rocket.y-=rocket.speed;
				up=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				down=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_A ) {
				left=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_D ) {
				right=true;
			}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W ) {
			//rocket.y-=rocket.speed;
				up=false;
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				down=false;
			}
			if(e.getKeyCode() == KeyEvent.VK_A ) {
				left=false;
			}
			if(e.getKeyCode() == KeyEvent.VK_D ) {
				right=false;
			}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(up) {
			player.y-=player.speed;
		}
		if(down) {
			player.y+=player.speed;
		}
		if(left) {
			player.x-=player.speed;
		}
		if(right) {
			player.x+=player.speed;
		}
		
		if(player.y + player.height < 0) {
			player.y= 1000 - player.height;
			currentAreaY--;
		}
		
		if(player.y > 1000) {
			player.y= 0 - player.height;
			currentAreaY++;
		}
		
		if(player.x + player.width < 0) {
			player.x= 1000;
			currentAreaX--;
		}
		if(player.x > 1000) {
			player.x= 0- player.height;
			currentAreaX++;
		}
		
		
	}
}
