package worldmapResources;

public class Pixel {

	private int x = 0;
	private int y = 0;
	
	private Terrain terrain = Terrain.UNKNOWN_TERRAIN;
	
	private int cost = Integer.MAX_VALUE;
	private double heuristic = 0;
	private double f = 0;
		
	private Pixel parentPixel = null;
	
	public Pixel(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Pixel getNextPixel(int[] direction) {
		return new Pixel(this.getX() + direction[0], this.getY() + direction[1]);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")[" + cost + ", " + heuristic + ", " + terrain + "]";
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Terrain getTerrain() {
		return this.terrain;
	}
	
	public int getCost() {
		return this.cost;
	}
	
	public double getHeuristic() {
		return this.heuristic;
	}
	
	public Pixel getParentPixel() {
		return this.parentPixel;
	}
	
	public double getF() {
		return this.f;
	}
	
	public void setParentPixel(Pixel parentPixel) {
		this.parentPixel = parentPixel;
	}
	
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public void setTerrain(Terrain terrain) {
		this.terrain = terrain;
	}
	
	public void setY(int y) {
		this.y = y;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void calculateF(Pixel goal) {
		this.f = this.heuristic + this.cost;
	}
	
	public void calculateHeuristic(Pixel goal) {
		this.heuristic = Math.sqrt(Math.pow((this.getX() - goal.getX()), 2) + Math.pow((this.getY() - goal.getY()), 2));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pixel other = (Pixel) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public boolean outOfBounds(Worldmap wmap) {
		return (this.getX() < 0 || this.getY() < 0 || this.getX() > (wmap.getWorldmap().getWidth() - 1)
				|| this.getY() > (wmap.getWorldmap().getHeight() - 1));
	}
	
}
