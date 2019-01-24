import java.awt.Color;
import java.awt.Graphics;

public class Key extends GameObject {
	boolean hasKey;

	public Key(int x, int y, int width, int height) {
		super(x, y, width, height);
		hasKey = false;

	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.drawImage(GamePanel.Key, x, y, width, height, null);
	}
}
