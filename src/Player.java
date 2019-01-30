import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	int speed;
	static int health;
	boolean isHurt;
	static boolean shield;
	static boolean canUseShield = true;
	static int playerScore;
	Color blue  = new Color (0,0,255, 10);
	Color blue1 = new Color (0,0,255, 35);
	Color blue2 = new Color (0,0,255, 55);
	Color blue3 = new Color (0,0,255, 75);
	Color blue4 = new Color (0,0,255, 95);
	Color blue5 = new Color (0,0,255, 105);
	
	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 7;
		health = 5;
		playerScore = 0;
	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {

		if (Sword.isRight) {
			if (!isHurt) {
				g.drawImage(GamePanel.Knight, x, y, width, height, null);
			} else {
				g.drawImage(GamePanel.HurtKnight, x, y, width, height, null);
			}
		} else {
			if (!isHurt) {
				g.drawImage(GamePanel.Knight2, x, y, width, height, null);
			} else {
				g.drawImage(GamePanel.HurtKnight2, x, y, width, height, null);
			}
		}
		if(shield) {
			g.setColor(Color.BLUE);
			g.drawRect(x, y, width+5, height+5);
		}
//		if(canUseShield) {
//			g.setColor(Color.BLUE);
//			g.fillRect(710, 47, 20, 20);
//		}
		if(GamePanel.ticks- ObjectManager.useShield < 0) {
			g.setColor(blue);
		}
		else if (GamePanel.ticks- ObjectManager.useShield < 100) {
			g.setColor(blue1);
		}
		else if (GamePanel.ticks- ObjectManager.useShield < 200) {
			g.setColor(blue2);
		}
		else if (GamePanel.ticks- ObjectManager.useShield < 300) {
			g.setColor(blue3);
		}
		else if (GamePanel.ticks- ObjectManager.useShield < 400) {
			g.setColor(blue4);
		}else if (GamePanel.ticks- ObjectManager.useShield < 500) {
			g.setColor(blue5);
		}else if(canUseShield){
			g.setColor(Color.BLUE);
		}
		g.fillRect(710, 47, 20, 20);
		
		
		
		g.setColor(Color.RED);
		for (int i = 0; i < health; i++) {
			g.fillRect(710 - (25 * i), 17, 20, 20);
		}

	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

}
