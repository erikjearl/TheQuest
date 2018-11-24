import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {
	int speed;
	int health;
	boolean isHurt;

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 7;
		health = 3;
	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {
		if (!isHurt) {
			g.setColor(Color.DARK_GRAY);
		} else {
			g.setColor(Color.RED);
		}
		g.fillRect(x, y, width, height);

		g.setColor(Color.RED);
		if (health > 2) {
			g.fillRect(650, 21, 20, 20);
		}
		if (health > 1) {
			g.fillRect(680, 21, 20, 20);
		}
		if (health > 0) {
			g.fillRect(710, 21, 20, 20);
		}
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

}
