import java.awt.Color;
import java.awt.Graphics;

public class Key extends GameObject{
boolean hasKey;
	
	public Key(int x, int y, int width, int height) {
		super(x, y, width, height);
		hasKey=false;
		// TODO Auto-generated constructor stub
	}
	
	void update() {
		super.update();
	}
	void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, height, width);
	}
}
