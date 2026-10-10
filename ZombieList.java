package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.logic.gameobjects.Zombie;
import pvz.utils.Position;

public class ZombieList {
	private Zombie zombie_list[];
	private int cont;
	
	public ZombieList() {
		this.zombie_list=new Zombie[100];
		cont=0;
	}
	
	public int size() {
		return cont;
	}
	
	public String iconInPosition(Position p) {
		
		int i=0;
		String ret="";
		
		while((i<cont)&&(!zombie_list[i].isInPosition(p))) {
			i++;
		}
		
		if(i<cont) {
			ret=zombie_list[i].getIcon();
		}
		
		return ret;
	}
	
	public void add(Zombie z) {
		zombie_list[cont]=z;
		cont++;
	}
	
	/*public boolean damage(Position p, int damage) {
		int i=0;
		boolean ret=false;
		
		while((i<cont)&&(!zombie_list[i].isInPosition(p))) {
			i++;
		}
		
		if(i<cont) {
			ret=true;
			zombie_list[i].receiveAttack(damage);
		}
		
		return ret;
	}*/
	
	public boolean damage(Position p, int damage) {
		int i=0;
		int colMasDerecha=Game.NUM_COLS;
		int zombieMasDerecha=-1;
		
		while(i<cont){
			if(zombie_list[i].isHorizontallyAligned(p) && 
					zombie_list[i].getCol()<colMasDerecha &&
					zombie_list[i].getCol()> p.column() &&
					zombie_list[i].isAlive() ) {
				colMasDerecha=zombie_list[i].getCol();
				zombieMasDerecha=i;
			}
			i++;
		}
		if (zombieMasDerecha!=-1) {
			zombie_list[zombieMasDerecha].receiveAttack(damage);
		}
		
		return zombieMasDerecha!=-1;
	}
	
	public boolean isEmpty(Position position) {
		int i = 0;
		boolean empty = true;
		
		while(i < cont && empty) {
			if(zombie_list[i].isInPosition(position)) {
				empty = false;
			}
			
			i++;
		}
		
		return empty;
	}
	
	
	public void removeDead() {
		for(int i = 0; i < cont; i++) {
			
			if(!zombie_list[i].isAlive()) {
				
				for(int j = i; j < cont - 1; j++) {
					zombie_list[j] = zombie_list[j + 1];
				}
				
				zombie_list[cont - 1] = null;
				cont--;
				i--;
			}
		}
	}
	
	public void update() {
		
		removeDead();
		
		for(int j = 0; j < cont ; j++) {
			zombie_list[j].update();
		}
	}
	
	public boolean anyInColumn(int column) {
		boolean encontrado = false;
		int i = 0;
		Position p=new Position(0,column);
		
		while((i < cont) && (!encontrado)) {
			if(zombie_list[i].isVerticallyAligned(p)) {
				encontrado=true;
			}
			else i++;
		}
		return encontrado;
	}
}
