package pvz.utils;

import java.util.Objects;

public class Position {
	
	private int row;
	private int col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int row() {
		// TODO Auto-generated method stub
		return row;
	}
	
	public int column() {
		// TODO Auto-generated method stub
		return col;
	}
	
	boolean isHorinzontallyAligned(Position pos) {
		return this.row==pos.row;
	}
	
	boolean isHorinzontallyAligned(Position pos) {
		return this.col==pos.col;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(col, row);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		return col == other.col && row == other.row;
	}
	
	public Position left() {
		Position pos = new Position(row, col-1);
		return pos;
	}
}
