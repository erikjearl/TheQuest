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

public class GamePanel extends JPanel implements KeyListener, ActionListener {
	Timer timer;
	static long ticks = 0;
	long startAttack = 0;
	static final int titleScreen = 0;
	static final int GameScreen = 1;
	static final int creditScreen = 2;
	static int currentScreen = titleScreen;
	static int currentAreaX = 1;
	static int currentAreaY = 1;

	boolean up;
	boolean down;
	boolean left;
	boolean right;
	boolean iPressed;
	boolean oPressed;
	boolean pPressed;

	BufferedImage[][] BGImage;
	public static BufferedImage GrassRoom;
	public static BufferedImage BlueRoom;
	public static BufferedImage GreenRoom;
	public static BufferedImage PurpleRoom;
	public static BufferedImage RedRoom;
	public static BufferedImage StoneFloor;

	Font titleFont;
	Font subFont;

	boolean holdingSpaceBar = false;
	boolean isUnlocked = false;

	// objects
	ObjectManager objMan;
	Player player = new Player(350, 350, 50, 50);
	Sword sword = new Sword(player.x, player.y);
	WiseMan wiseMan = new WiseMan(600, 300);
	Key key = new Key(player.x, player.y - 25, 30, 15);
	Boss boss = new Boss(200, 400, 300, 300);

	DeathListener death;
	
	public GamePanel(DeathListener death) {
		this.death = death;
		titleFont = new Font("Arial", Font.BOLD, 48);
		subFont = new Font("Arial", Font.PLAIN, 30);
		timer = new Timer(1000 / 60, this);
		objMan = new ObjectManager(player, sword, wiseMan, key, boss);

		try {
			GrassRoom = ImageIO.read(this.getClass().getResourceAsStream("GrassRoom.png"));
			BlueRoom = ImageIO.read(this.getClass().getResourceAsStream("BlueRoom.png"));
			GreenRoom = ImageIO.read(this.getClass().getResourceAsStream("GreenRoom.png"));
			PurpleRoom = ImageIO.read(this.getClass().getResourceAsStream("PurpleRoom.png"));
			RedRoom = ImageIO.read(this.getClass().getResourceAsStream("RedRoom.png"));
			StoneFloor = ImageIO.read(this.getClass().getResourceAsStream("StoneFloor.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BGImage = new BufferedImage[4][3];
		BGImage[0][1] = BlueRoom;
		BGImage[1][0] = PurpleRoom;
		BGImage[1][1] = GrassRoom;
		BGImage[1][2] = RedRoom;
		BGImage[2][1] = GreenRoom;
		BGImage[3][1] = StoneFloor;
		

	}

	void startGame() {
		timer.start();
	}

	void updateGameScreen() {
		objMan.update();
		if (!player.isAlive) {
			currentScreen++;
			//System.out.println("DEAD");
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
		
		g.drawImage(BGImage[currentAreaX][currentAreaY], WIDTH - 1, HEIGHT - 2, null);
		objMan.checkCollision();
		objMan.purgeObjects();
		objMan.update();
		objMan.draw(g);
		g.setFont(subFont);
		g.drawString("" + Player.playerScore, 10, 29);

	}

	void drawCreditScreen(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, TheQuest.width, TheQuest.height);
		
		g.setFont(titleFont);
		g.setColor(Color.MAGENTA);
		g.drawString("The Quest", 235, 300);
		
		 if (!player.isAlive) {
		g.setFont(subFont);
		g.drawString("YOU DIED", 275, 350);
		g.drawString("Score: " + Player.playerScore, 291, 385);
		 }
		else if(player.isAlive) {
			g.setFont(subFont);
			g.drawString("YOU WON!", 273, 350);
			g.drawString("Score: " + Player.playerScore * Player.health, 291, 385);
		}
		
		
	}

	@Override
	public void paintComponent(Graphics g) {

		if (currentScreen == titleScreen) {
			drawTitleScreen(g);
		} else if (currentScreen == GameScreen) {
			drawGameScreen(g);
		} else if (currentScreen == creditScreen) {
			drawCreditScreen(g);
		} else if (currentScreen > 2) {
			currentScreen = titleScreen;
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

		if (currentScreen == titleScreen || currentScreen == creditScreen) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				currentScreen++;
				//objMan = new ObjectManager(player, sword, wiseMan, key, boss);
				  currentAreaX = 1;
				  currentAreaY = 1;
				  ObjectManager.clearedY1 = false;
				  ObjectManager.clearedY2 = false;
				  ObjectManager.clearedY3 = false;
				  ObjectManager.clearedY4 = false;
				  Player.health = 5;
				  Player.playerScore = 0;
				  timer.stop();
				  death.restart();
			}
		}

		if (currentScreen > creditScreen) {
			currentScreen = titleScreen;
		}

		if (currentScreen == GameScreen)
		{
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = true;
			sword.isRight = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = true;
			sword.isRight = true;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_I) {
			iPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_O) {
			oPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			pPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_K) {
			currentScreen++;
			System.out.println("skip");
		}
		if (e.getKeyCode() == KeyEvent.VK_J) {
			objMan.finalTalk = true;
			System.out.println("boss dead");
		}
		

		if (e.getKeyCode() == KeyEvent.VK_SPACE && !Sword.isAttacking && !holdingSpaceBar) {
			sword.update();
			Sword.isAttacking = true;
			startAttack = ticks;
			holdingSpaceBar = true;
		}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			holdingSpaceBar = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_I) {
			iPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_O) {
			oPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_P) {
			pPressed = false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		ticks++;
		
		if(iPressed&&oPressed&&pPressed) {
			Player.health++;
			pPressed = false;
		}
		
		//System.out.println(up  + " "+ down + " " +left + " " +right + player.x + " " + player.y);
		
		if (up) {
			player.y -= player.speed;
		}
		if (down) {
			player.y += player.speed;
		}
		if (left) {
			player.x -= player.speed;
		}
		if (right) {
			player.x += player.speed;
		}

		

		if (player.y < 0) {
			if (currentAreaY == 0 || (BGImage[currentAreaX][currentAreaY - 1] == null)) {
				//up = false;
				player.y= 1;
			} else {
				currentAreaY--;
				player.y = TheQuest.height - player.height;
			}

		}

		if (player.y > TheQuest.height - player.height) {
			if (currentAreaY == 2 || (BGImage[currentAreaX][currentAreaY + 1] == null)) {
				//down = false;
				player.y = 699;

			} else if (currentAreaY == 1 && !key.hasKey && !isUnlocked) {
				player.y = 695;
				down = false;
				JOptionPane.showMessageDialog(null, "You must first get they key from a wise man");

			} else if (currentAreaY == 0) {
				currentAreaY++;
				player.y = 0;
			} else if (currentAreaY == 1) {
				currentAreaY++;
				player.y = 0;
				isUnlocked = true;
			}

		}

		if (player.x < 0) {
			if (currentAreaX == 0 || (currentAreaX==3 && wiseMan.isAlive) || (BGImage[currentAreaX - 1][currentAreaY] == null)) {
				//left = false;
				player.x = 1;
			} else {
				player.x = TheQuest.width - player.width;
				currentAreaX--;
			}
		}
		if (player.x > TheQuest.width - player.width) {
			if ((currentAreaX == 2 && !objMan.talkedZ) || currentAreaX == 3 || (BGImage[currentAreaX + 1][currentAreaY] == null)) {
				//right = false;
				player.x = 699;
			} else {
				player.x = 0;
				currentAreaX++;
			}

		}

		if (ticks - startAttack > 10) {
			Sword.isAttacking = false;
		}

		// System.out.println(ticks + " " + startAttack);
		key.y = player.y - 25;
		if (!Sword.isAttacking) {
			sword.y = player.y - Sword.width;
			if (sword.isRight) {
				sword.x = player.x + player.width;
				key.x = player.x - 60;
			} else {
				sword.x = player.x - Sword.width;
				key.x = player.x + player.width + 50;
			}
		}

		else {
			sword.y = player.y + (player.height / 2);
			if (sword.isRight) {
				sword.x = player.x + player.width;
				key.x = player.x - 60;
			} else {
				sword.x = player.x - Sword.height;
				key.x = 2 * player.x + player.width + 50;
			}
		}

		repaint();
	}
}
