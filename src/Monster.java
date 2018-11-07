import java.awt.Color;
import java.awt.Graphics;

public class Monster extends GameObject {
	int health;
	boolean isAlive;
	
	public Monster(int x, int y, int width, int height) {
		super(x, y, width, height);
		health = 5;
		isAlive = true;
	}
	
	void update() {
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
	
	
	
	
}
