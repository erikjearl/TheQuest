import java.awt.Color;
import java.awt.Graphics;

public class Zombie extends GameObject{
	int health;
	boolean isAlive;
	int type;
	public Zombie(int x, int y, int width, int height, int type) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		isAlive = true;
		this.type = type;
		if(type ==3) {
			health =9999;
		} else {health = 25;}
	}

		void update() {
			super.update();
		}
		
		void draw(Graphics g) {
			if(isAlive) {
			g.setColor(Color.cyan);
			g.fillRect(x, y, width, height);
			}
			
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

