import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

public class ObjectManager {
	Player p;
	Sword sword;
	Key key;
	WiseMan man;
	Boss boss;
	boolean secondTalk;
	boolean thirdTalk;
	boolean finalTalk;
	long monsterAtt = 0;

	// Y1
	boolean spawnedY1 = false;
	public static boolean clearedY1 = false;
	Monster Y1M1 = new Monster(600, 300, 100, 100, 0);
	Monster Y1M2 = new Monster(100, 100, 100, 100, 0);
	Monster Y1M3 = new Monster(300, 300, 100, 100, 1);
	Monster Y1M4 = new Monster(300, 100, 100, 100, 1);


	
	// Y2
	boolean spawnedY2 = false;
	public static boolean clearedY2 = false;
	Monster Y2M1 = new Monster(100, 500, 100, 100, 0);
	Monster Y2M2 = new Monster(100, 100, 100, 100, 0);
	Monster Y2M3 = new Monster(300, 300, 100, 100, 1);
	Monster Y2M4 = new Monster(300, 100, 100, 100, 1);

	
	// Y3
	boolean spawnedY3 = false;
	public static boolean clearedY3 = false;
	Monster Y3M1 = new Monster(100, 100, 100, 100, 0);
	Monster Y3M2 = new Monster(100, 250, 100, 100, 1);
	Monster Y3M3 = new Monster(600, 275, 100, 100, 1);
	
	ArrayList<Monster> Monsters = new ArrayList<Monster>();

	public ObjectManager(Player p, Sword sword, WiseMan man, Key key, Boss boss) {
		this.p = p;
		this.sword = sword;
		this.man = man;
		this.key = key;
		this.boss = boss;
	}

	void update() {
		p.update();
		sword.update();
		key.update();
		man.update();
		boss.update();

		for (Monster m : Monsters) {
			m.update();
		}

	}

	void draw(Graphics g) {
		p.draw(g);
		boss.draw(g);

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
				Player.playerScore++;
			}
		}
		if (man.health < 1) {
			man.isAlive = false;
		}

		if (p.health <= 0) {
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
			if (p.collisionBox.intersects(m.collisionBox) && !p.isHurt) {
				p.health--;
				p.isHurt = true;
				monsterAtt = GamePanel.ticks;
			}
			if (sword.box.intersects(m.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					m.health -= 1;
				}
			}
		}

		if (p.isHurt && ((GamePanel.ticks - monsterAtt > 50))) {
			p.isHurt = false;
		}

	}

	public void manageRoom() {
		

		if (GamePanel.currentAreaX == 1 && GamePanel.currentAreaY == 0) {
			if (!spawnedY1) {
				addMonster(Y1M1);
				addMonster(Y1M2);
				addMonster(Y1M3);
				addMonster(Y1M4);
				spawnedY1 = true;

			}

			moveMonsters();

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

			moveMonsters();

			if (!Y2M1.isAlive && !Y2M2.isAlive && !Y2M3.isAlive && !Y2M4.isAlive) {
				clearedY2 = true;
			}
		} else {
			spawnedY2 = false;
		}

		if (GamePanel.currentAreaX == 1 && GamePanel.currentAreaY == 1) {
			purgeAllMonsters();
		}
		if (GamePanel.currentAreaX == 2 && GamePanel.currentAreaY == 1) {
			if(!finalTalk) {
				man.isAlive = true;
			}
			else {
				man.isAlive = false;
			}
			
			if (sword.box.intersects(man.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					sword.update();
					man.health -= 1;
					System.out.println("You hit the man");
					Sword.isAttacking = false;
				}
			}
			if (p.collisionBox.intersects(man.collisionBox)) {
				if (!sword.hasSword) {
					sword.hasSword = true;
					System.out.println("SWORD");
					JOptionPane.showMessageDialog(null, "Here is a Sword to help you on your journey");
				}

				if (clearedY1 && clearedY2 && !thirdTalk) {
					key.hasKey = true;
					thirdTalk=true;
					JOptionPane.showMessageDialog(null,
							"You have proven your self worthy of this key, take it and challenge the boss");
				} else if ((clearedY1 || clearedY2) && !secondTalk && !thirdTalk) {
					secondTalk = true;
					JOptionPane.showMessageDialog(null,
							"Well Done killing the monsters, return to me once they are all dead for a key");
				}  else if (clearedY3 && !finalTalk) {
					finalTalk= true;
					JOptionPane.showMessageDialog(null,
							"That was all easy, looking for a real challenge? Try fighting me!");
					
				}
				

			}
		} else {
			man.isAlive = false;
		}

		if (GamePanel.currentAreaX == 1 && GamePanel.currentAreaY == 2) {
			key.hasKey = false;
			
			if (p.collisionBox.intersects(boss.collisionBox) && !p.isHurt) {
				p.health--;
				p.isHurt = true;
				monsterAtt = GamePanel.ticks;
			}

			if (sword.box.intersects(boss.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					sword.update();
					boss.health -= 1;
					System.out.println("Boss Health: " + boss.health);
					Sword.isAttacking = false;
				}
			}
			
			if (!spawnedY3) {
				addMonster(Y3M1);
				addMonster(Y3M2);
				addMonster(Y3M3);
				boss.isDisplayed = true;
				spawnedY3 = true;
			}

			moveMonsters();
			
			
			if(GamePanel.ticks % 11  == 0 && boss.isDisplayed) {
				if(bCurrent == 1 && boss.getX() <  (TheQuest.width - boss.width)) {
						boss.setX((boss.getX() + bCurrent));
				}
				else {
					bCurrent =-1;
				}
				
				if(bCurrent == -1 && boss.getX() >  0) {
					boss.setX((boss.getX() + bCurrent));
				}
				else {
					bCurrent =1;
				}			
				
			}

			if (!Y3M1.isAlive && !Y3M2.isAlive && !Y3M3.isAlive && !boss.isAlive) {
				clearedY3 = true;
			}

		} else {
			spawnedY3 = false;
			boss.isDisplayed = false;
		}
	}
	
	int dCurrent;
	int bCurrent;
	Random r = new Random();
	public void moveMonsters() {
//		if(r.nextInt(2) == 0) {dCurrent = (-1);}
//		else { dCurrent= 1;}
			for (Monster m : Monsters) {
				if (m.moveType == 0) {
					if(GamePanel.ticks % 2  == 0) {
						if(dCurrent == 1 && m.getX() <  (TheQuest.width - m.width)) {
								m.setX((m.getX() + dCurrent));
						}
						else {
							dCurrent =-1;
						}
						
						if(dCurrent == -1 && m.getX() >  0) {
							m.setX((m.getX() + dCurrent));
						}
						else {
							dCurrent =1;
						}			
						
					}
				} 
				else if (m.moveType == 1) {
					if(GamePanel.ticks % 10 == 0) {
						//System.out.println("Player- x: " + p.getX() + " y: " + p.getY());
						//System.out.println("Monster- x: " + m.getX()+ " y: "+ m.getY());
						
						if (p.getX() - m.getX() < 0) {
							m.setX((m.getX() - 1));
						}
						else if (p.getX() - m.getX() > 0){
							m.setX((m.getX() + 1));
						}
						
						if (p.getY() - m.getY() < 0) {
							m.setY(m.getY() - 1);
						}
						else if (p.getY() - m.getY() > 0){
							m.setY(m.getY() + 1);
						}
					}
				}
			}
		}




}
