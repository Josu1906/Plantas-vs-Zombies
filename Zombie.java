package pvz.logic.gameobjects;

import pvz.logic.Game;
import pvz.utils.Position;

public class Zombie {
    private int strenght=5;
	private int damage=1;
	private int cyclesAlive=0;
	
	private Position position;
	private Game game;

	public Zombie(Position position, Game game){
		this.position=position;
		this.game=game;
	}
	
	public String getIcon() {
		return "Z[0" + strenght + "]";
	}
	
	public boolean isInPosition(Position position) {
		return this.position.equals(position);
	}
	
	public boolean isHorizontallyAligned(Position position) {
		return this.position.isHorizontallyAligned(position);
	}
	
	public boolean isVerticallyAligned(Position position) {
		return this.position.isVerticallyAligned(position);
	}
	
	public void receiveAttack(int damage) {
		this.strenght-=damage;
	}
	
	public void update() {
		
		cyclesAlive++;
		Position pos_delante=this.position.left();
		if(game.isEmpty(pos_delante)) {
			if(cyclesAlive%2==0) {
				position=pos_delante;
			}
		}
		else{
			game.attackPlant(pos_delante, damage);
		}
	}
	
	public boolean isAlive() {
		return strenght>0;
	}
	
	public int getCol() {
		return position.column();
	}
}
