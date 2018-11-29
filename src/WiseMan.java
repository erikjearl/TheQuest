import java.awt.Color;
import java.awt.Graphics;

public class WiseMan extends GameObject{
	static int width =65;
	static int height = 120;
	int health;
	boolean isAlive;
	
	public WiseMan(int x, int y) {
		super(x, y, width, height);
		health = 5000;
		isAlive = false;
	}
	
	void update(){
		super.update();
		//System.out.println(collisionBox);
	}
	
	void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
	}
	
	public int getHealth(){
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	
}
