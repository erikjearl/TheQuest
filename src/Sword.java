import java.awt.Color;
import java.awt.Graphics;

public class Sword extends GameObject{
	static boolean isAttacking;
	boolean isRight = true;
	static int width = 15;
	static int height = 45;
	boolean hasSword = false;
	
	public Sword(int x, int y) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}
	
	void update(){
		super.update();
	}

	void draw(Graphics g) {
		if(isAttacking) { 
			g.setColor(Color.BLACK);
			g.fillRect(x, y, height, width);
		}
		else {
			g.setColor(Color.BLACK);
			g.fillRect(x, y, width, height);
		}
	}
	
	boolean getIsAttacking() {
		return isAttacking;
	}
	
	void setIsAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	
}
