import java.awt.Color;
import java.awt.Graphics;

public class Zombie extends GameObject{
	int health;
	boolean isAlive;
	
	public Zombie(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		health = 25;
		isAlive = true;
	}

		void update() {
			super.update();
		}
		
		void draw(Graphics g) {
			g.setColor(Color.cyan);
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

