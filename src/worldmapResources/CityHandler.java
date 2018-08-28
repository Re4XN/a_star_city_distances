package worldmapResources;

public class CityHandler implements TerrainHandler {

	@Override
	public void setCost(Pixel pixel) {
		pixel.setCost(pixel.getCost() + 0.5);
	}

}
