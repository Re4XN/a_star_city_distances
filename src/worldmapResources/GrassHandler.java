package worldmapResources;

public class GrassHandler implements TerrainHandler {

	@Override
	public void setCost(Pixel pixel) {
		pixel.setCost(pixel.getCost() + 3);
	}

}
