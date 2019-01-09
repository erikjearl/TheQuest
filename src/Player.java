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
		playerScore =0;
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
		for(int i =0; i < health; i++) {
			g.fillRect(710 - (25 *i), 17, 20, 20);
		}
		
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

}
