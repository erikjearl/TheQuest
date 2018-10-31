import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ObjectManager {
	Player p;
	Sword sword;
	WiseMan man;
	
	ArrayList<Monster> Monsters = new ArrayList<Monster>();
	
	public ObjectManager(Player p, Sword sword, WiseMan man) {
		this.p = p;
		this.sword = sword;
		this.man = man;
	}
	
	void update() {
		p.update();
		sword.update();
		man.update();
		
		for(Monster m: Monsters) {
			m.update();
		}
		
	}
	
	void draw(Graphics g) {
		p.draw(g);
		if(sword.hasSword) {sword.draw(g);}
		if(man.isAlive) {man.draw(g);}
		
		for(Monster m: Monsters) {
			m.draw(g);
		}
		
		
	}
	
	void addMonster(Monster m) {
		Monsters.add(m);
	}
	
	
	
	void purgeObjects() {
		for(int i =0; i< Monsters.size(); i++) {
			if(Monsters.get(i).health < 1) {
				Monsters.remove(i);
			}
		}
		if (man.health < 1) {
			man.isAlive = false;
		}
		
	}
	
	void checkCollision() {
		if(!sword.hasSword && man.isAlive) {
			if(p.collisionBox.intersects(man.collisionBox)) {
        		sword.hasSword = true;
        		System.out.println("SWORD");
        		JOptionPane.showMessageDialog(null, "Here is a Sword to help you on your journey");
			}
		}
		for(Monster m : Monsters){
	        if(p.collisionBox.intersects(m.collisionBox)) {
	        		p.health -= 1;
	        }
	        if(sword.collisionBox.intersects(m.collisionBox)) {
				if(sword.hasSword && sword.isAttacking ) {
	        			m.health -=1;	
				}
			}     
	    }
		
        if(sword.collisionBox.intersects(man.collisionBox)) {
			if(sword.hasSword && sword.isAttacking) {
        			man.health -=1;	
        			System.out.println("You hit the man");
        			sword.isAttacking=false;
			}
		}
	}	
	
}
