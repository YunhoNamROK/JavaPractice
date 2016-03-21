package game;

public class Location {
	
	private int x,y;
	public void move(int dx, int dy){
		x += dx;
		y += dy;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

}
