import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ObjectManager {
	Player p;
	Sword sword;
	Key key;
	WiseMan man;
	boolean secondTalk;

	// Y1
	boolean spawnedY1 = false;
	public boolean clearedY1 = false;
	Monster Y1M1 = new Monster(100, 300, 100, 100, 1);
	Monster Y1M2 = new Monster(100, 100, 100, 100, 0);
	Monster Y1M3 = new Monster(300, 300, 100, 100, 0);
	Monster Y1M4 = new Monster(300, 100, 100, 100, 0);

	// Y2
	boolean spawnedY2 = false;
	public boolean clearedY2 = false;
	Monster Y2M1 = new Monster(100, 300, 100, 100, 1);
	Monster Y2M2 = new Monster(100, 100, 100, 100, 0);
	Monster Y2M3 = new Monster(300, 300, 100, 100, 0);
	Monster Y2M4 = new Monster(300, 100, 100, 100, 0);

	ArrayList<Monster> Monsters = new ArrayList<Monster>();

	public ObjectManager(Player p, Sword sword, WiseMan man, Key key) {
		this.p = p;
		this.sword = sword;
		this.man = man;
		this.key=key;
	}

	void update() {
		p.update();
		sword.update();
		key.update();
		man.update();

		for (Monster m : Monsters) {
			m.update();
		}

	}

	void draw(Graphics g) {
		p.draw(g);
		if (sword.hasSword) {
			sword.draw(g);
		}
		if (man.isAlive) {
			man.draw(g);
		}
		
		if (key.hasKey) {
			key.draw(g);
		}
		
		for (Monster m : Monsters) {
			m.draw(g);
		}

		manageRoom();

	}

	void addMonster(Monster m) {
		Monsters.add(m);
	}

	void purgeObjects() {
		for (int i = 0; i < Monsters.size(); i++) {
			if (Monsters.get(i).health < 1) {
				Monsters.get(i).isAlive = false;
				Monsters.remove(i);
			}
		}
		if (man.health < 1) {
			man.isAlive = false;
		}
		
		if(p.health <= 0) {
			p.isAlive = false;
		}
		
	}

	void purgeAllMonsters() {
		for (int i = 0; i < Monsters.size(); i++) {
			Monsters.remove(i);
		}
	}

	void checkCollision() {
		for (Monster m : Monsters) {
			if (p.collisionBox.intersects(m.collisionBox)) {
				p.health -= 1;
			}
			if (sword.box.intersects(m.collisionBox)) {
				if (sword.hasSword && sword.isAttacking) {
					m.health -= 1;
				}
			}
		}

	}

	public void manageRoom() {
		//System.out.println("X:" +GamePanel.currentAreaX + " Y:" +GamePanel.currentAreaY);
		if(!p.isAlive) {
			System.out.println("YOU ARE DEAD!");
		}
		if (GamePanel.currentAreaX == 1 && GamePanel.currentAreaY == 0) {
				if (!spawnedY1) {
					addMonster(Y1M1);
					addMonster(Y1M2);
					addMonster(Y1M3);
					addMonster(Y1M4);
					spawnedY1 = true;	
					
				}
				for (Monster m : Monsters) {
					if(m.moveType == 0) {
						
					}
					else if (m.moveType == 1) {
						m.setX(m.getX() - (m.getX()- p.getX() /10));
					}
				}

				if (!Y1M1.isAlive && !Y1M2.isAlive && !Y1M3.isAlive && !Y1M4.isAlive) {
					clearedY1 = true;
				}
				
		} else {
			spawnedY1 = false;
		}

			if (GamePanel.currentAreaX == 0 && GamePanel.currentAreaY == 1) {
				if (!spawnedY2) {
					addMonster(Y2M1);
					addMonster(Y2M2);
					addMonster(Y2M3);
					addMonster(Y2M4);
					spawnedY2 = true;
				}
				
				
				if (!Y2M1.isAlive && !Y2M2.isAlive && !Y2M3.isAlive && !Y2M4.isAlive) {
					clearedY2 = true;
				}
			} else {
				spawnedY2 = false;
			}

			if (GamePanel.currentAreaX == 1&& GamePanel.currentAreaY == 1) {
				purgeAllMonsters();
			}
			if (GamePanel.currentAreaX == 2&& GamePanel.currentAreaY == 1) {
				man.isAlive = true;
				if (sword.box.intersects(man.collisionBox)) {
					if (sword.hasSword && sword.isAttacking) {
						sword.update();
						man.health -= 1;
						System.out.println("You hit the man");
						sword.isAttacking = false;
					}
				}
				if (p.collisionBox.intersects(man.collisionBox)) {
					if (!sword.hasSword) {
						sword.hasSword = true;
						System.out.println("SWORD");
						JOptionPane.showMessageDialog(null, "Here is a Sword to help you on your journey");
					}
					
					if (clearedY1 && clearedY2 && !key.hasKey) {
						key.hasKey =true;
						JOptionPane.showMessageDialog(null,
								"You have proven your self worthy of this key, take it and challenge the boss");
					} else if ((clearedY1 || clearedY2) && !secondTalk && !key.hasKey) {
						secondTalk = true;
						JOptionPane.showMessageDialog(null,
								"Well Done killing the monsters, return to me once they are all dead for a key");
					}
				}
			} else {
				man.isAlive = false;
			}

		if (GamePanel.currentAreaX == 1 && GamePanel.currentAreaY == 2) {
			key.hasKey = false;
		}

	}

}
