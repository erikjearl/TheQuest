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
	Font textFont = new Font("Herculanum", Font.PLAIN, 16);
	
	long monsterAtt = 0;
	long manHurt = 0;
	long bossHurt = 0;
	Date startString = new Date();
	Date now;
	boolean talked = false;

	
	
	//check
	boolean welcome = false;
	boolean secondTalk= false;
	boolean thirdTalk= false;
	boolean finalTalk= false;
	boolean merchant1= false;
	boolean walkedIn= false;
	boolean talkedZ= false;
	
	// text box
	private static final int WELCOME_STRING = 1;
	private static final String welcomeString1 = "Welcome to the quest, here you will find monsters and adventures,";
	private static final String welcomeString2 = "you should probly go check with the old man to see what your job is";
	
	private static final int SWORD_STRING = 2;
	private static final String swordString1 = "Here is a Sword to help you on your journey";
	private static final String swordString2 = "";
	
	private static final int SECOND_STRING = 3;
	private static final String secondString1 = "Well done killing the monsters, return to me once they are all dead for a key";
	private static final String secondString2 = "";
	
	private static final int THIRD_STRING = 4;
	private static final String thirdString1 = "You have proven your self worthy of this key, take it and challenge the boss";
	private static final String thirdString2 = "";
	
	private static final int FINAL_STRING = 5;
	private static final String finalString1 = "That was all easy, looking for a real challenge? Try fighting me!";
	private static final String finalString2 = "";
	
	private static final int M_STRING = 6;
	private static final String mString1 = "Hey you!";
	private static final String mString2 = "follow me if you want some help";
	
	private static final int MERCHANT_STRING = 7;
	private static final String merchantString1 = "Im looking to betray the wizard who stole my people...";
	private static final String merchantString2 = "so if you want to buy some extra lives for points you can hit me and the deal is on";
	
	private static final int BUY_STRING = 8;
	private static final String buyString1 = "Pleasure doing buisness";
	private static final String buyString2 = "I have more hearts for sale if you can afford the points";
	
	private static final int BOSS_STRING = 9;
	private static final String bossString1 = "You killed the boss!";
	private static final String bossString2 = "I'm sure the old man wants to reward you... go see him";
	
	private static final int ENTER_STRING = 10;
	private static final String enterString1 = "Welcome to the final battle";
	private static final String enterString2 = "Good Luck...";
	
	private static final int ZOMBIESPAWN_STRING = 11;
	private static final String zombieSpawnString1 = "Im down to 20% health";
	private static final String zombieSpawnString2 = "Lets see if you can handle my zombie army";
	
	private static final int ENDING_STRING = 12;
	private static final String endingString1 = "RIP";
	private static final String endingString2 = "";
	
	private int stringState = WELCOME_STRING;
	
	
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
		if(m.health>0) {
			monsters.add(m);
		}
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

		if (Player.health <= 0) {
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
				Player.health--;
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
				Player.health--;
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
				Player.health--;
				p.isHurt = true;
				proj.isAlive= false;
				monsterAtt = GamePanel.ticks;
			}
		}
		
		if (p.isHurt && ((GamePanel.ticks - monsterAtt > 50))) {
			p.isHurt = false;
		}
		
		if (sword.box.intersects(z1.collisionBox) && z1.isAlive && Player.playerScore > 9) {
			if (sword.hasSword && Sword.isAttacking) {
				Sword.isAttacking = false;
				Player.playerScore-=10;
				Player.health++;
				makeText(BUY_STRING);
			}
		}

	}
	
	public void manageRoom(Graphics g) {
			now = new Date();
			if(stringState != WELCOME_STRING && !welcome) {
			startString = new Date();
			stringState = WELCOME_STRING;
			welcome = true;
			}
			drawString(g);
			
		
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
				if(!clearedY1) {
					Player.playerScore+= (r.nextInt(10) + 1);
				}
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
				if(!clearedY2) {
					Player.playerScore+= (r.nextInt(10) + 1);
				}
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
					makeText(MERCHANT_STRING);
				}
			}
		}
		
		if(talkedZ && (GamePanel.currentAreaX != 1 || GamePanel.currentAreaY != 1)) {
			z1.isAlive=false;
		}
		
		if (GamePanel.currentAreaX == 2 && GamePanel.currentAreaY == 1) {
			if(!finalTalk) {
				man.isAlive = true;
			}
			
			if (clearedY3 && !finalTalk) {
				finalTalk= true;
				makeText(FINAL_STRING);
			}
			
			if (sword.box.intersects(man.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					sword.update();
					man.health -= 1;
					//System.out.println("You hit the man");
				}
			}
			if (p.collisionBox.intersects(man.collisionBox)) {
				
				if (!sword.hasSword) {
					makeText(SWORD_STRING);
				} else if (clearedY1 && clearedY2 && !thirdTalk) {
					key.hasKey = true;
					thirdTalk=true;
					makeText(THIRD_STRING);
				} else if ((clearedY1 || clearedY2) && !secondTalk && !thirdTalk) {
					secondTalk = true;
					makeText(SECOND_STRING);
				} else if (finalTalk) {
					man.isAlive = false;
				}
			}
			if(!man.isAlive) {
				if (!talked) {
					makeText(M_STRING);
					talked=true;
				}
				if(GamePanel.ticks % 10  == 0) {
					if(!walkedIn && z1.x < 200) {
							z1.x+=3;
						}
						else {
							walkedIn = true;
							z1.x-=3;
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
				Player.health--;
				monsterAtt = GamePanel.ticks;
			}

			if (sword.box.intersects(boss.collisionBox)) {
				if (sword.hasSword && Sword.isAttacking) {
					sword.update();
					boss.health -= 1;
					//System.out.println("Boss Health: " + boss.health);
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
				if(!clearedY3) {
					Player.playerScore+= (r.nextInt(20) + 1);
					makeText(BOSS_STRING);
				}
				clearedY3 = true;
			}

		} else {
			spawnedY3 = false;
			boss.isDisplayed = false;
			
		}
		
		if (GamePanel.currentAreaX == 3 && GamePanel.currentAreaY == 1) {
			if (!spawnedY4) {
				makeText(ENTER_STRING);
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
					//System.out.println("Man's Health: " +man.health);
				}
			}
			if (p.collisionBox.intersects(man.collisionBox)&& !p.isHurt && man.isAlive) {
				p.isHurt = true;
				Player.health--;
				monsterAtt = GamePanel.ticks;
			}

			moveMonsters();
			manageZombies();
			if(GamePanel.ticks % 200  == 0 && man.isAlive) {
				addProjectile(new Projectile(man.x + (WiseMan.width/2),man.y + (WiseMan.height/2),15,15, p.getX(),p.getY()));
				GamePanel.ticks++;
			}
			moveProjectiles();
			if(man.getHealth() < 300) {
				if(!lotsOfZombies) {
					addZombie(new Zombie(450,150, 30,30,0));
					addZombie(new Zombie(450,360, 30,30,0));
					addZombie(new Zombie(450,550, 30,30,0));
					addZombie(new Zombie(600,150, 30,30,0));
					addZombie(new Zombie(600,360, 30,30,0));
					addZombie(new Zombie(600,550, 30,30,0));
					
					addZombie(new Zombie(50,90, 40,50,1));
					addZombie(new Zombie(50,660, 40,50,1));
					
					makeText(ZOMBIESPAWN_STRING);
					lotsOfZombies= true;
				}
				if(GamePanel.ticks % 125  == 0 && man.isAlive) {
					addProjectile(new Projectile(man.x + (WiseMan.width/2),man.y + (WiseMan.height/2),15,15, p.getX(),p.getY()));
					GamePanel.ticks++;
				}
				
			}

			if (!Y4M1.isAlive && !Y4M2.isAlive && !Y4M3.isAlive && !Y4M4.isAlive && !Y4M5.isAlive && !man.isAlive) {
				clearedY4 = true;
				makeText(ENDING_STRING);
				GamePanel.currentScreen++;
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
						//System.out.println(zomb.y);
						//GamePanel.ticks++;
					}
				
			
			}
			
			
		}
		
		private void makeText(int state) {
			now = new Date();
			if(stringState != state) {
				startString = new Date();
				stringState = state;
				sword.hasSword = true;
				}
		}
		
		public void drawString(Graphics g) {
			String text1 = "";
			String text2 = "";
			g.setFont(textFont);
				
				if(stringState > 0 && now.getTime() - startString.getTime() < 3000) {
					g.setColor(Color.black);
					g.fillRect(0, 675, 750, 75);
					g.setColor(Color.white);
					g.fillRect(2, 677, 746, 71);
					g.setColor(Color.BLACK);
					
					if (stringState == WELCOME_STRING) {
						text1 = welcomeString1;
						text2 = welcomeString2;
					}
					else if (stringState == SWORD_STRING) {
						text1 = swordString1;
						text2 = swordString2;
					}
					else if (stringState == SECOND_STRING) {
						text1 = secondString1;
						text2 = secondString2;
					}
					else if (stringState == THIRD_STRING) {
						text1 = thirdString1;
						text2 = thirdString2;
					}
					else if (stringState == FINAL_STRING) {
						text1 = finalString1;
						text2 = finalString2;
					}
					else if (stringState == M_STRING) {
						text1 = mString1;
						text2 = mString2;
					}
					else if (stringState == MERCHANT_STRING) {
						text1 = merchantString1;
						text2 = merchantString2;
					}
					else if (stringState == BUY_STRING) {
						text1 = buyString1;
						text2 = buyString2;
					}
					else if (stringState == BOSS_STRING) {
						text1 = bossString1;
						text2 = bossString2;
					}
					else if (stringState == ENTER_STRING) {
						text1 = enterString1;
						text2 = enterString2;
					}
					else if (stringState == ZOMBIESPAWN_STRING) {
						text1 = zombieSpawnString1;
						text2 = zombieSpawnString2;
					}
					else if (stringState == ENDING_STRING) {
						text1 = endingString1;
						text2 = endingString2;
					}
					
					//System.out.println(text1+ " " +text2);
					if(text2 == "") {
						g.drawString(text1, 35, 715);
					} else {
						g.drawString(text1, 35, 705);
						g.drawString(text2, 35, 730);	
					}
				
				
				}else {
						stringState = 0;	
				}
				

		}



}
