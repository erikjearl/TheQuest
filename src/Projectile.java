import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject{
	boolean isAlive;
	public Projectile(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		isAlive = true;
	}
	
	void update() {
		super.update();
	}
	void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, height, width);
	}
}
