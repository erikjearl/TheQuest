import java.awt.Color;
import java.awt.Graphics;

public class WiseMan extends GameObject{
	static int width =50;
	static int height = 75;
	int health;
	boolean isAlive;
	
	public WiseMan(int x, int y) {
		super(x, y, width, height);
		health = 10;
		isAlive = false;
	}
	
	void update(){
		super.update();
	}
	
	void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
	
}
