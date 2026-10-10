package pvz.logic;

import java.util.Random;

import pvz.control.Level;
import pvz.utils.Position;
import pvz.logic.gameobjects.SunflowerList;
import pvz.logic.gameobjects.PeashooterList;
import pvz.logic.ZombiesManager;
import pvz.logic.gameobjects.Sunflower;
import pvz.logic.gameobjects.Peashooter;

public class Game {
	
	public static final int NUM_ROWS = 4;
	public static final int NUM_COLS = 8;
	public static final int INITIAL_COINS=50;
	
	private SunflowerList sunflowers;
	private PeashooterList peashooters;
	private ZombiesManager zombies;
	private int cycles=0;
	private int coins= INITIAL_COINS;
	private Level level;
	private long seed;
	private Random rand;
	private boolean quit;

	public Game(long seed, Level level) {
		// TODO Auto-generated constructor stub
		this.seed=seed;
		this.level=level;
		reset();
	}

	public String positionToString(Position position) {
		// TODO Auto-generated method stub
		String ret="";
		
		if(!zombies.isEmpty(position)) {
			ret= zombies.iconInPosition(position);
		} 
		else if(!sunflowers.isEmpty(position)) {
			ret=sunflowers.iconInPosition(position);
		} 
		else if(!peashooters.isEmpty(position)) {
			ret=peashooters.iconInPosition(position);
		}

		return ret;
	}
	
	public boolean checkGameObject(String objectName) {
		return objectName.equalsIgnoreCase(Sunflower.shortName())||
				objectName.equalsIgnoreCase(Sunflower.longName()) ||
				objectName.equalsIgnoreCase(Peashooter.shortName())||
				objectName.equalsIgnoreCase(Peashooter.longName());
	}
	
	public int getCycles() {
		
		return cycles;
	}
		
	public int getCoins() {
			
		return coins;
	}
	
	public int getRemainingZombies() {
		return zombies.getRemainingZombies();
	}
	
	public boolean playerWins() {
		return zombies.allZombiesWereKilled();
	}
	
	public boolean playerQuits() {
		return quit;
	}

	public void quit() {
		quit=true;
	}
	
	public boolean hasGameFinished() {
		return playerWins() || zombies.doZombiesReachedTheHouse() ||
				playerQuits();
	}
	
	public void update() {
		cycles++;
		zombies.update();
		sunflowers.update();
		peashooters.update();
		//no acabado, falta aparecer los zombies y etc

	}
	
	public void reset() {
		cycles=0;
		coins=INITIAL_COINS;
		sunflowers= new SunflowerList();
		peashooters= new PeashooterList();
		rand= new Random(seed);
		zombies= new ZombiesManager(this, level, rand);
		quit=false;
		
		
	}
	
	public void addGameObject(String plantType, Position position) {
		
		if (checkGameObject(plantType) && isInsideBoard(position) &&
				isEmpty(position)) {
			
			if( plantType.equalsIgnoreCase(Sunflower.shortName())||
					plantType.equalsIgnoreCase(Sunflower.longName()) ) {
				if(coins>=Sunflower.cost) {
					Sunflower s= new Sunflower(position, this);
					sunflowers.add(s);
					coins-= Sunflower.cost;
				}
			}
			else if( plantType.equalsIgnoreCase(Peashooter.shortName())||
					plantType.equalsIgnoreCase(Peashooter.longName()) ) {
				if(coins>=Peashooter.cost) {
					Peashooter p= new Peashooter(position, this);
					peashooters.add(p);
					coins-= Peashooter.cost;
				}
		    }
			
		}
	}
	
	public void generateCoins(int amount) {
		coins += amount;
	}
	
	public void attackZombie(Position p, int damage) {
		zombies.damageZombie(p, damage);
	}
	
	public void attackPlant(Position p, int damage) {
		if(!sunflowers.isEmpty(p)) {
			sunflowers.receiveDamage(p, damage);
		}
		else if (!peashooters.isEmpty(p)) {
			peashooters.receiveDamage(p, damage);
		}
	}
	
	public boolean isEmpty(Position p) {
	
		return zombies.isEmpty(p) &&
			sunflowers.isEmpty(p) &&
			peashooters.isEmpty(p);
	}
	
	public Position newZombiesPositon(int row) {
		
		Position p=new Position(row, NUM_COLS-1);
		return p;
	}
	
	public boolean isInsideBoard(Position p) {
		
		return p.row()>=0 &&
			p.row()<NUM_ROWS &&
			p.column()>=0 &&
			p.column()<NUM_COLS;
	}