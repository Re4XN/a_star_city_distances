package worldmapResources;

public class RiverHandler implements TerrainHandler {

	@Override
	public void setCost(Pixel pixel) {
		pixel.setCost(pixel.getCost() + 10);
	}

}
