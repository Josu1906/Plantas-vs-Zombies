package pvz.logic.gameobjects;
import pvz.logic.Game;
import pvz.utils.Position;


public class Sunflower {
	
	private int cost = 20;
	private int strenght = 1;
	private int damage = 0;
	private	Position position;
	private Game game;
	
	public Sunflower(int row, int col, Game gamee) {
		
		position = new Position(row, col);
		game = gamee;
	}
	
	public void coinsGenerator() {
		game.generateCoins(3.33);
	}
	
	

	public static Object getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

}
