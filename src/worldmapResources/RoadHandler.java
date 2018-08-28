package worldmapResources;

public class RoadHandler implements TerrainHandler {

	@Override
	public void setCost(Pixel pixel) {
		pixel.setCost(pixel.getCost() + 0.5);
	}

}
