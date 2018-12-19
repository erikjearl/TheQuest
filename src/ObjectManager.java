import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.JOptionPane;

public class ObjectManager {
	Player p;
	Sword sword;
	Key key;
	WiseMan man;
	Boss boss;
	Font textFont = new Font("Comic Sans", Font.PLAIN, 16);
	
	long monsterAtt = 0;
	long manHurt = 0;
	long bossHurt = 0;
	Date startString = new Date();
	Date now;

	
	
	//check
	boolean welcome;
	boolean secondTalk;
	boolean thirdTalk;
	boolean finalTalk;
	boolean merchant1;
	boolean walkedIn;
	boolean talkedZ;
	
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
		Zombie z1 = new Zombie(-5,360, 70,90,3);
		boolean spawnedY4 = false;
		boolean lotsOfZombies = false;
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
		
		manageRoom(g);

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
		for (int i = 0; i < zombies.size(); i++) {
			if(zombies.get(i).type ==0) {
				zombies.remove(i);
			}
		}
		for (Projectile proj : projectiles) {
			proj.isAlive = false;
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
			if (p.collisionBox.intersects(zomb.collisionBox) && !p.isHurt && zomb.type != 3) {
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
	
	public void manageRoom(Graphics g) {
				now = new Date();
				drawString(g,"Welcome to the quest, here you will find monsters and adventures,","you should probly go check with the old man to see what your job is");

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

		if (GamePanel.currentAreaX == 1 && GamePanel.currentAreaY == 1) { //middle
			purgeAllMonsters();
			if(walkedIn) {
				z1.x= 300;
				if(!talkedZ) {
					talkedZ = true;
					//JOptionPane.showMessageDialog(null, "Zombie King: Im looking to betray the wizard who stole my people..."
						//	+ " so if you want to buy some extra lives for points you can hit me and the deal is on");
					//now = new Date();
				}
				//drawString(g,"Im looking to betray the wizard who stole my people...","so if you want to buy some extra lives for points you can hit me and the deal is on");
				
			}
			
		}
		
		if(talkedZ && (GamePanel.currentAreaX != 1 || GamePanel.currentAreaY != 1)) {
			z1.isAlive=false;
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
					//JOptionPane.showMessageDialog(null, "Here is a Sword to help you on your journey");
					now = new Date();
					drawString(g,"Here is a Sword to help you on your journey","");
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
			if(finalTalk) {
				if(GamePanel.ticks % 10  == 0) {
						if(!walkedIn && z1.x < 200) {
							z1.x++;
						}
						else {
							walkedIn = true;
							z1.x--;
						}
					
				}
				if(!merchant1) {
					addZombie(z1);
					merchant1 = true;
				}
			}
		} else if (GamePanel.currentAreaX != 3 || GamePanel.currentAreaY != 1) {
			man.isAlive = false;
		}

		if (GamePanel.currentAreaX == 1 && GamePanel.currentAreaY == 2) {
			key.hasKey = false;
			
			if (p.collisionBox.intersects(boss.collisionBox) && !p.isHurt && boss.isAlive) {
				p.isHurt = true;
				p.health--;
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
			if (sword.box.intersects(man.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					sword.update();
					man.health -= 1;
					System.out.println("Man's Health: " +man.health);
				}
			}
			if (p.collisionBox.intersects(man.collisionBox)&& !p.isHurt && man.isAlive) {
				p.isHurt = true;
				p.health--;
				monsterAtt = GamePanel.ticks;
			}

			moveMonsters();
			manageZombies();
			if(GamePanel.ticks % 200  == 0 && man.isAlive) {
				addProjectile(new Projectile(man.x + (WiseMan.width/2),man.y + (WiseMan.height/2),15,15, p.getX(),p.getY()));
				GamePanel.ticks++;
			}
			moveProjectiles();
			if(man.getHealth()<500) {
				if(!lotsOfZombies) {
					addZombie(new Zombie(450,150, 30,30,0));
					addZombie(new Zombie(450,360, 30,30,0));
					addZombie(new Zombie(450,550, 30,30,0));
					addZombie(new Zombie(600,150, 30,30,0));
					addZombie(new Zombie(600,360, 30,30,0));
					addZombie(new Zombie(600,550, 30,30,0));
					
					addZombie(new Zombie(50,90, 40,50,1));
					addZombie(new Zombie(50,660, 40,50,1));
					lotsOfZombies= true;
				}
				if(GamePanel.ticks % 125  == 0 && man.isAlive) {
					addProjectile(new Projectile(man.x + (WiseMan.width/2),man.y + (WiseMan.height/2),15,15, p.getX(),p.getY()));
					GamePanel.ticks++;
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
				if(GamePanel.ticks % 7 == 0) {
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
		int spawnTimer=600;
		public void manageZombies() {
			if(GamePanel.ticks % spawnTimer == 0 && man.isAlive) {
				addZombie(new Zombie(man.x,man.y - 75, 30,30,0));
				addZombie(new Zombie(man.x-65,man.y+45, 30,30,0));
				addZombie(new Zombie(man.x,man.y+ 175, 30,30,0));
				if(lotsOfZombies) {
					addZombie(new Zombie(100,100, 30,30,0));
					addZombie(new Zombie(100,630, 30,30,0));
					spawnTimer = 300;
				}
				
				GamePanel.ticks++;
			}
			for (Zombie zomb : zombies) {
				if(GamePanel.ticks % 4 == 0 && zomb.type == 0) {						
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
				} else if (GamePanel.ticks % 75  == 0 && (zomb.type ==1 && zomb.isAlive)) {
						addProjectile(new Projectile(zomb.x + (zomb.width/2),zomb.y + (zomb.height/2),15,15, p.getX(),p.getY()));
						System.out.println(zomb.y);
						//GamePanel.ticks++;
					}
				
			
			}
			
			
		}
		
		
		public void drawString(Graphics g, String text1, String text2) {
			g.setFont(textFont);
			//System.out.println(text1+" " +text2);
			if(now.getTime() - startString.getTime() < 4000) {
				System.out.println(text1+" " +text2);
				g.setColor(Color.black);
				g.fillRect(0, 675, 750, 75);
				g.setColor(Color.white);
				g.fillRect(2, 677, 746, 71);
				g.setColor(Color.BLACK);
				if(text2 == "") {
					g.drawString(text1, 45, 675);
				} else {
					g.drawString(text1, 45, 705);
					g.drawString(text2, 45, 730);	
				}
			}
		}



}
