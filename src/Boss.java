import java.awt.Color;
import java.awt.Graphics;

public class Boss extends GameObject {
	int health;
	boolean isDisplayed;
	boolean isAlive;

	public Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		health = 10;
		isAlive = true;
		isDisplayed = true;
	}

	void update() {
		super.update();
		if (health < 1) {
			isAlive = false;
		}
	}

	void draw(Graphics g) {
		if (isDisplayed) {
			if (isAlive) {
				g.setColor(Color.magenta);
			} else {
				g.setColor(Color.black);
			}
			g.fillRect(x, y, height, width);
		}
	}
}