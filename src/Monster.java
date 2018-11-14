import java.awt.Color;
import java.awt.Graphics;

public class Monster extends GameObject {
	int health;
	boolean isAlive;
	int moveType;
	
	public Monster(int x, int y, int width, int height, int moveType) {
		super(x, y, width, height);
		health = 5;
		isAlive = true;
		this.moveType = moveType;
	}
	
	void update() {
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x, y, width, height);
	}
	
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	void setX(int x) {
		this.x=x;
	}
	void setY(int y) {
		this.y=y;
	}
	
	
}
