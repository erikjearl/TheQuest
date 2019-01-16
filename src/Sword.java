import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Sword extends GameObject{
	static boolean isAttacking;
	boolean isRight = true;
	static int width = 15;
	static int height = 45;
	boolean hasSword = false;
	Rectangle box;
	
	public Sword(int x, int y) {
		super(x, y, width, height);
		box = new Rectangle(x, y, width, height);
	}
	
	void update(){
		//super.update();
		//System.out.println(collisionBox);
		if(isAttacking) { 
			box.setBounds(x, y, height, width);
		}
		else {
			box.setBounds(x, y, width, height);
		}
		
		
		
	}

	void draw(Graphics g) {
		if(isAttacking) { 
			g.setColor(Color.BLACK);
			//g.fillRect(x+xAdjust, y+yAdjust, height, width);
			g.fillRect(x, y, height, width);
		}
		else {
			g.setColor(Color.BLACK);
			//g.fillRect(x+xAdjust, y+yAdjust, width, height);
			g.fillRect(x, y, width, height);
		}
	}
	
	boolean getIsAttacking() {
		return isAttacking;
	}
	
	void setIsAttacking(boolean isAttacking) {
		Sword.isAttacking = isAttacking;
	}
	
}
