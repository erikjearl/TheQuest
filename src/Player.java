import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject{
	int speed;
	int health;

	public Player(int x, int y, int width, int height) {
		super(x, y, width, height);
		speed = 7;
		health = 3;
	}
	
	void update(){
		super.update();
	}

	void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		//g.fillRect(x+xAdjust, y+yAdjust, width, height);
		g.fillRect(x, y, width, height);
	}
	
	int getX() {
		return x;
	}
	
	int getY() {
		return y;
	}
	
	
	
}
