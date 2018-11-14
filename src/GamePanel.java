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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
	Timer timer;
	static long ticks =0;
	long startAttack = 0;
	static final int titleScreen = 0;
	static final int GameScreen = 1;
	static final int creditScreen = 1;
	int currentScreen = titleScreen; 
	static int currentAreaX =1;
	static int currentAreaY =1;
	
	 boolean up;
 	 boolean down;
     boolean left;
	 boolean right;
	 
	 BufferedImage[][] BGImage;
	 public static BufferedImage GrassRoom;
	 public static BufferedImage BlueRoom;
	 public static BufferedImage GreenRoom;
	 public static BufferedImage PurpleRoom;
	 public static BufferedImage RedRoom;
	 
	 Font titleFont;
	 Font subFont;
	 
	 boolean holdingSpaceBar = false;
	 
	
	 
	 
	 //objects
	 ObjectManager objMan;
	 Player player = new Player(350, 350, 50, 50);
	 Sword sword = new Sword(player.x, player.y);
	 WiseMan wiseMan = new WiseMan(600, 325);
	 Key key = new Key(player.x,player.y - 25, 30, 15);
	
	 
	 public GamePanel() {
		 titleFont = new Font("Arial",Font.BOLD,48);
		 subFont = new Font("Arial",Font.PLAIN,30);
		 timer = new Timer(1000/60, this);
		 objMan = new ObjectManager(player, sword, wiseMan, key);
		 
		 try {
			GrassRoom = ImageIO.read(this.getClass().getResourceAsStream("GrassRoom.png"));
			BlueRoom = ImageIO.read(this.getClass().getResourceAsStream("BlueRoom.png"));
			GreenRoom = ImageIO.read(this.getClass().getResourceAsStream("GreenRoom.png"));
			PurpleRoom = ImageIO.read(this.getClass().getResourceAsStream("PurpleRoom.png"));
			RedRoom = ImageIO.read(this.getClass().getResourceAsStream("RedRoom.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 BGImage = new BufferedImage[3][3];
		 BGImage[0][1] = BlueRoom;
		 BGImage[1][0] = PurpleRoom;
		 BGImage[1][1] = GrassRoom;
		 BGImage[1][2] = RedRoom;
		 BGImage[2][1] = GreenRoom;
		 
	}
	 
	 void startGame() {
		 timer.start();
	 }
	 
	 void updateGameScreen() {
		 objMan.update();
		 if(!player.isAlive) {
			 currentScreen = creditScreen;
		 }
	 }
	 
	void drawTitleScreen(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(0, 0, TheQuest.width, TheQuest.height);
		
		g.setFont(titleFont);
		g.setColor(Color.CYAN);
		g.drawString("The Quest", 235, 300);
		g.setFont(subFont);
		g.drawString("press space to start", 235, 350);
		
	}
	
	void drawGameScreen(Graphics g) {
		updateGameScreen();
		g.drawImage(BGImage[currentAreaX][currentAreaY], WIDTH-1, HEIGHT-2, null);		
		objMan.checkCollision();
		objMan.purgeObjects();
		objMan.update();
		objMan.draw(g);
		
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
				up=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_S) {
				down=true;
			}
			if(e.getKeyCode() == KeyEvent.VK_A ) {
				left=true;
				sword.isRight = false;
			}
			if(e.getKeyCode() == KeyEvent.VK_D ) {
				right=true;
				sword.isRight = true;
			}
			if(e.getKeyCode() == KeyEvent.VK_K ) {
				key.hasKey =true;
			}
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE && !Sword.isAttacking && !holdingSpaceBar) {
			sword.update();
			Sword.isAttacking = true;
			startAttack = ticks;
			holdingSpaceBar = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W ) {
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
			
		if(e.getKeyCode() == KeyEvent.VK_SPACE ) {
			Sword.isAttacking = false;
			holdingSpaceBar = false;
		}	
	}
		

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ticks++;
		
		if(player.y < 0) {
			if(currentAreaY == 0 || (BGImage[currentAreaX][currentAreaY-1] == null) ) {
				up = false;
			}
			else {
				currentAreaY--;
				player.y= TheQuest.height - player.height;
			}
			
		}
		
		if(player.y > TheQuest.height - player.height) {
			if(currentAreaY == 2 || (BGImage[currentAreaX][currentAreaY+1] == null)) {
				down = false;
			}
			else if(currentAreaY == 1 && !key.hasKey) {
				player.y-=10;
				down = false;
				JOptionPane.showMessageDialog(null,
						"You must first get they key from a wise man");
				
			}
			else if (currentAreaY == 0){
				currentAreaY++;
				player.y= 0;
			}
			else if (currentAreaY == 1 && key.hasKey) {
				currentAreaY++;
				player.y= 0;
			}
		}
		
		if(player.x < 0) {
			if(currentAreaX == 0 || (BGImage[currentAreaX-1][currentAreaY] == null)) {
				left = false;
			}
			else {
				player.x= TheQuest.width - player.width;
				currentAreaX--;
			}
		}
		if(player.x > TheQuest.width - player.width) {
			if(currentAreaX == 2 || (BGImage[currentAreaX+1][currentAreaY] == null)) {
				right = false;
			}
			else{
				player.x= 0 ;
				currentAreaX++;
			}
			
		}
		
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
		
		
		
		if (ticks- startAttack > 100) { Sword.isAttacking = false;}
		//System.out.println(ticks + " " + startAttack);
		key.y = player.y -25;
		if (!Sword.isAttacking) {
			sword.y=player.y-Sword.width;
			if(sword.isRight) {
				sword.x = player.x+player.width;
				key.x = player.x - 60;
			}
			else {
				sword.x = player.x-Sword.width;
				key.x = player.x + player.width + 50;
			}
		}
		
		else {
			sword.y = player.y+ (player.height/2);
			if(sword.isRight) {
				sword.x = player.x+player.width;
				key.x = player.x - 60;
			}
			else {
				sword.x = player.x-Sword.height;
				key.x = 2 * player.x + player.width + 50;
			}
		}

		repaint();
	}
}
