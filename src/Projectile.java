import java.awt.Color;
import java.awt.Graphics;

public class Projectile extends GameObject{
	boolean isAlive;
	int targetX;
	int targetY;
	int Xmovement;
	int Ymovement;
	public Projectile(int x, int y, int width, int height, int targetX, int targetY) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
		isAlive = true;
		this.targetX = targetX;
		this.targetY= targetY;
		Xmovement=0;
		Ymovement =0;
	}
	
	void update() {
		super.update();
	}
	void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, height, width);
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
	int getTargetX() {
		return targetX;
	}
	int getTargetY() {
		return targetY;
	}
}
