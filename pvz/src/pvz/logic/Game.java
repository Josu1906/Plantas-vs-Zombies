package pvz.logic;

import pvz.control.Level;
import pvz.utils.Position;

public class Game {
	
	private double coins = 0;

	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;

	public Game(long seed, Level level) {
		// TODO Auto-generated constructor stub
	}

	public String positionToString(Position position) {
		// TODO Auto-generated method stub
		return "";
	}
	
	public void generateCoins(double amount) {
		coins += amount;
	}

}
