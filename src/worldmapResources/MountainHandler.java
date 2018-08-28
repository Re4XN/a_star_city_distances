package worldmapResources;

public class MountainHandler implements TerrainHandler {

	@Override
	public void setCost(Pixel pixel) {
		pixel.setCost(pixel.getCost() + 15);
	}

}
