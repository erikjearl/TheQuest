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
	long manHurt = 0;
	long bossHurt = 0;

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
	
	// Y4
		boolean spawnedY4 = false;
		boolean lotsZombies = false;
		public static boolean clearedY4 = false;
		Monster Y4M1 = new Monster(300, 100, 100, 100, 0);
		Monster Y4M2 = new Monster(300, 550, 100, 100, 0);
		Monster Y4M3 = new Monster(100, 300, 100, 100, 2);
		Monster Y4M4 = new Monster(400, 300, 100, 100, 2);
		Monster Y4M5 = new Monster(375, 375, 100, 100, 1);

	
	ArrayList<Monster> monsters = new ArrayList<Monster>();
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	ArrayList<Zombie> zombies = new ArrayList<Zombie>();

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

		for (Monster m : monsters) {
			m.update();
		}
		for (Projectile proj : projectiles) {
			proj.update();
		}
		for (Zombie zomb : zombies) {
			zomb.update();
		}

	}

	void draw(Graphics g) {
	
		boss.draw(g);

		if (man.isAlive) {
			man.draw(g);
		}

		if (key.hasKey) {
			key.draw(g);
		}

		for (Monster m : monsters) {
			m.draw(g);
		}
		for (Projectile proj : projectiles) {
			proj.draw(g);
		}
		for (Zombie zomb : zombies) {
			zomb.draw(g);
		}

		p.draw(g);
		
		if (sword.hasSword) {
			sword.draw(g);
		}
		
		
		
		manageRoom();

	}

	void addMonster(Monster m) {
		monsters.add(m);
	}
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	void addZombie(Zombie z) {
		zombies.add(z);
	}

	void purgeObjects() {
		for (int i = 0; i < monsters.size(); i++) {
			if (monsters.get(i).health < 1) {
				monsters.get(i).isAlive = false;
				monsters.remove(i);
				Player.playerScore++;
			}
		}
		for(int i = 0; i < projectiles.size(); i++) {
			if (!projectiles.get(i).isAlive || (projectiles.get(i).getX()<0 || projectiles.get(i).getX()>750) || (projectiles.get(i).getY()<0 || projectiles.get(i).getY()>750)) {
				projectiles.remove(projectiles.get(i));
			}
		}
		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i).health < 1) {
				zombies.get(i).isAlive = false;
				zombies.remove(i);
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
		for (int i = 0; i < monsters.size(); i++) {
			monsters.remove(i);
		}
	}

	void checkCollision() {
		for (Monster m : monsters) {
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
		for (Zombie zomb : zombies) {
			if (p.collisionBox.intersects(zomb.collisionBox) && !p.isHurt) {
				p.health--;
				p.isHurt = true;
				monsterAtt = GamePanel.ticks;
			}
			if (sword.box.intersects(zomb.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					zomb.health -= 1;
				}
			}
		}
		for (Projectile proj: projectiles) {
			if (p.collisionBox.intersects(proj.collisionBox) && !p.isHurt) {
				p.health--;
				p.isHurt = true;
				proj.isAlive= false;
				monsterAtt = GamePanel.ticks;
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
			for (Projectile proj : projectiles) {
				proj.isAlive = false;
			}
		}
		if (GamePanel.currentAreaX == 2 && GamePanel.currentAreaY == 1) {
			if(!finalTalk) {
				man.isAlive = true;
			}
			else{
				man.isAlive = false;
			}
			
			if (sword.box.intersects(man.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					sword.update();
					man.health -= 1;
					System.out.println("You hit the man");
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
					p.health+= 2;
					
				}
				

			}
		} else if (GamePanel.currentAreaX != 3 && GamePanel.currentAreaY != 1) {
			man.isAlive = false;
		}

		if (GamePanel.currentAreaX == 1 && GamePanel.currentAreaY == 2) {
			key.hasKey = false;
			
			if (p.collisionBox.intersects(boss.collisionBox) && !p.isHurt && boss.isAlive) {
				p.health--;
				p.isHurt = true;
				monsterAtt = GamePanel.ticks;
			}

			if (sword.box.intersects(boss.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					sword.update();
					boss.health -= 1;
					System.out.println("Boss Health: " + boss.health);
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
			
			
			if(GamePanel.ticks % 11  == 0 && boss.isAlive) {
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
			if(GamePanel.ticks % 100  == 0 && boss.isAlive) {
				addProjectile(new Projectile(boss.x + (boss.width/2),boss.y + (boss.height/2),15,15, p.getX(),p.getY()));
				GamePanel.ticks++;
			}
			moveProjectiles();

			if (!Y3M1.isAlive && !Y3M2.isAlive && !Y3M3.isAlive && !boss.isAlive) {
				clearedY3 = true;
				
			}

		} else {
			spawnedY3 = false;
			boss.isDisplayed = false;
			
		}
		
		if (GamePanel.currentAreaX == 3 && GamePanel.currentAreaY == 1) {
			if (!spawnedY4) {
				addMonster(Y4M1);
				addMonster(Y4M2);
				addMonster(Y4M3);
				addMonster(Y4M4);
				addMonster(Y4M5);
				man.isAlive = true;
				man.setHealth(1500);
				spawnedY4 = true;
			}

			moveMonsters();
			manageZombies();
			if(GamePanel.ticks % 200  == 0 && boss.isAlive) {
				addProjectile(new Projectile(man.x + (man.width/2),man.y + (man.height/2),15,15, p.getX(),p.getY()));
				GamePanel.ticks++;
			}
			moveProjectiles();
			if(man.getHealth()<500) {
				if(!lotsZombies) {
					addZombie(new Zombie(450,300, 30,30));
					addZombie(new Zombie(450,500, 30,30));
					addZombie(new Zombie(450,700, 30,30));
					addZombie(new Zombie(600,300, 30,30));
					addZombie(new Zombie(600,500, 30,30));
					addZombie(new Zombie(600,700, 30,30));
					addZombie(new Zombie(200,300, 30,30));
					addZombie(new Zombie(200,500, 30,30));
					addZombie(new Zombie(200,700, 30,30));
					lotsZombies= true;
				}
			}

			if (!Y4M1.isAlive && !Y4M2.isAlive && !Y4M3.isAlive && !Y4M4.isAlive && !Y4M5.isAlive && !man.isAlive) {
				clearedY4 = true;
			}

		} else {
			spawnedY4 = false;
		}
		
	
	}
	
	int dCurrent;
	int bCurrent;
	boolean moved = false;
	Random r = new Random();
	public void moveMonsters() {

			for (Monster m : monsters) {
				
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
				if (m.moveType == 2) {
					if(GamePanel.ticks % 2  == 0) {
						if(dCurrent == 1 && m.getY() <  (TheQuest.height - m.height)) {
								m.setY((m.getY() + dCurrent));
						}
						else {
							dCurrent =-1;
						}
						
						if(dCurrent == -1 && m.getY() >  0) {
							m.setY((m.getY() + dCurrent));
						}
						else {
							dCurrent =1;
						}			
						
					}
				} 
				else if (m.moveType == 1) {
					if(GamePanel.ticks % 10 == 0) {						
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

		public void moveProjectiles() {
		
			for (Projectile proj : projectiles) {
				if(GamePanel.ticks % 10 == 0) {
					if(proj.Xmovement == 0 && proj.Ymovement == 0) {
						proj.Xmovement = (proj.getTargetX() - proj.getX())/150;
						proj.Ymovement = (proj.getTargetY() - proj.getY())/150;
					}
					else{
						proj.setX((proj.getX() + proj.Xmovement));
						proj.setY((proj.getY() + proj.Ymovement));
					}
				}
			}
		}
		
		public void manageZombies() {
			if(GamePanel.ticks % 1000 == 0 && man.isAlive) {
				addZombie(new Zombie(man.x,man.y - 55, 30,30));
				addZombie(new Zombie(man.x-55,man.y+45, 30,30));
				addZombie(new Zombie(man.x,man.y+ 155, 30,30));
				GamePanel.ticks++;
			}
			for (Zombie zomb : zombies) {
				if(GamePanel.ticks % 10 == 0) {						
					if (p.getX() - zomb.getX() < 0) {
						zomb.setX((zomb.getX() - 1));
					}
					else if (p.getX() - zomb.getX() > 0){
						zomb.setX((zomb.getX() + 1));
					}
					
					if (p.getY() - zomb.getY() < 0) {
						zomb.setY(zomb.getY() - 1);
					}
					else if (p.getY() - zomb.getY() > 0){
						zomb.setY(zomb.getY() + 1);
					}
				}
			}
		}



}
