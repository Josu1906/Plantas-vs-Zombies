package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.utils.Position;


public class Peashooter {
	
	private int cost = 50;
	private int strenght = 3;
	private int damage = 1;
	private	Position position;
	private Game game;
	
	public Peashooter(Position position, Game gamee) {
		
		this.position = position;
		game = gamee;
	}
	
	public void update() {
		
		//game.generateCoins(1); peashooter ataca, no genera coins
		
		game.attackZombie(position, damage);

	}
	
	

	public static Object getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void receiveDamage(int damage) {
		this.strenght -= damage;
	}
	
	public boolean isAlive() {
		boolean ishe = true;
		if(this.strenght < 1) ishe = false;
		
		return ishe;
	}
	
	public boolean isInPosition(Position position) {
		
		boolean isIn = false;
		
		if(position.row() == this.position.row() && position.column() == this.position.column()) {
			isIn = true;
		}
		
		return isIn;
		//return this.position.equals(position);

	}

	public String getIcon() {
		return "P[0" + strenght + "]";
	}

}
