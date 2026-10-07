package pvz.logic.gameobjects;

public class Zombie {
  private int resistance=5;
	private int damage=1;
	private int velocity=1;
	private int cycle=2;
	
	private Position position;
	private Game game;

	public Zombie(Position position, Game game){
		this.position=position;
		this.game=game;
	}
	
	public String getIcon() {
		return "Z";
	}
	
	public boolean isInPosition(Position position) {
		return this.position==position;
	}
	
	public boolean isHorizontallyAligned(Position position) {
		return this.position.isHorizontallyAligned(position);
	}
	
	public boolean isVerticallyAligned(Position position) {
		return this.position.isVerticallyAligned(position);
	}
	
	public void receiveAttack(int damage) {
		this.resistance-=damage;
	}
	
	public void update() {
		
	}
	
	public boolean isAlive() {
		return resistance>0;
	}
}
