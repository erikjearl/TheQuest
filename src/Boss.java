import java.awt.Color;
import java.awt.Graphics;

public class Boss extends GameObject {
	int health;
	boolean isDisplayed;
	boolean isAlive;

	public Boss(int x, int y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		health = 1000;
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
			
			if (isAlive && health > 400) {
				g.drawImage(GamePanel.Boss, x, y, width, height, null);
			}else if(health >0) {
				g.drawImage(GamePanel.Boss2, x, y, width, height, null);
			}
			if (isAlive) {
				g.setColor(Color.red);
				g.drawRect(x, y, height, width);
			}
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