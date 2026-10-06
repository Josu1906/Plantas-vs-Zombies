package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.logic.gameobjects.Peashooter;
import pvz.utils.Position;

public class PeashooterList {
	
	private Peashooter peashooter_list[];
	private int cont;
	
	
	public boolean isEmpty(Position position) {
		int i = 0;
		boolean empty = true;
		
		while(i < cont && empty) {
			if(peashooter_list[i].isInPosition(position)) {
				empty = false;
			}
			
			i++;
		}
		
		return empty;
	}
	
	public void removeDead() {
		for(int i = 0; i < cont; i++) {
			
			if(!peashooter_list[i].isAlive()) {
				
				for(int j = i; j < cont - 1; j++) {
					peashooter_list[j] = peashooter_list[j + 1];
				}
				
				peashooter_list[cont - 1] = null;
				cont--;
				
			}
			
		}
		
		
	}
	
	public void update() {
		
		removeDead();
		
		for(int j = 0; j < cont ; j++) {
			peashooter_list[j].update();
		}
	}
	
	public void add(Peashooter peashooter) {
		peashooter_list[cont] = peashooter;
		cont++;
		
	}
		public String iconInPosition(Position position) {
			
			boolean encontrado = false;
			int i = 0;
			String ret = null;
			
			while(i < cont && !peashooter_list[i].isInPosition(position)) i++;
			
			if(peashooter_list[i].isInPosition(position)){
				
				ret = peashooter_list[i].getIcon();
			}
			
			return ret;
			
	}

}

