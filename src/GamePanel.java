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
	 
	 Player player = new Player(350, 350, 50, 50);
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
		g.drawString("The Quest", 235, 300);
		g.setFont(subFont);
		g.drawString("press space to start", 225, 350);
		
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
		
		if(player.y < 0) {
			if(currentAreaY == 0 || (currentAreaY == 1 && currentAreaX ==0) || (currentAreaY == 1 && currentAreaX ==2)) {
				up = false;
			}
			else {
				currentAreaY--;
				player.y= TheQuest.height - player.height;
			}
			
		}
		
		if(player.y > TheQuest.height - player.height) {
			if(currentAreaY == 2 || (currentAreaY == 1 && currentAreaX ==0) || (currentAreaY == 1 && currentAreaX ==2)) {
				down = false;
			}
			else {
				currentAreaY++;
				player.y= 0;
			}
		}
		
		if(player.x < 0) {
			if(currentAreaX == 0 || (currentAreaX ==1 && currentAreaY ==0) || (currentAreaX ==1 && currentAreaY ==2) ) {
				left = false;
			}
			else {
				player.x= TheQuest.width - player.width;
				currentAreaX--;
			}
		}
		if(player.x > TheQuest.width - player.width) {
			if(currentAreaX == 2 || (currentAreaX ==1 && currentAreaY ==0) || (currentAreaX ==1 && currentAreaY ==2)) {
				right = false;
			}
			else{
				player.x= 0 ;
				currentAreaX++;
			}
			
		}
		
		
	}
}
