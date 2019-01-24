import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	int speed;
	static int health;
	boolean isHurt;
	static int playerScore;

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
		// g.drawRect(x, y, width, height);

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
