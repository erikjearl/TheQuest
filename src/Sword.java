import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Sword extends GameObject {
	static boolean isAttacking;
	static boolean isRight = true;
	static int width = 15;
	static int height = 45;
	boolean hasSword = false;
	Rectangle box;

	public Sword(int x, int y) {
		super(x, y, width, height);
		box = new Rectangle(x, y, width, height);
	}

	void update() {
		// super.update();
		// System.out.println(collisionBox);
		if (isAttacking) {
			box.setBounds(x, y, height, width);
		} else {
			box.setBounds(x, y, width, height);
		}

	}

	void draw(Graphics g) {
		if (isAttacking) {
			g.setColor(Color.BLACK);
			if (isRight) {
				g.drawImage(GamePanel.SwordDR, x, y, 45, 15, null);
			} else {
				g.drawImage(GamePanel.SwordDL, x, y, 45, 15, null);
			}
		} else {
			g.setColor(Color.BLACK);
			g.drawImage(GamePanel.SwordUp, x, y, width, height, null);
		}
		// g.drawRect(x, y, width, height);
	}

	boolean getIsAttacking() {
		return isAttacking;
	}

	void setIsAttacking(boolean isAttacking) {
		Sword.isAttacking = isAttacking;
	}

}
