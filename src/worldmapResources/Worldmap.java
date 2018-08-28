package worldmapResources;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Worldmap {
	
	private static final Map<Color, TerrainHandler> DISPATCHER = populateMap();
	
	private BufferedImage worldmap = null;
	
	private double pixelDistance = 8.2372; 

	public Worldmap(File mapImage) throws IOException {
		worldmap = ImageIO.read(mapImage);
	}

	public void setPixelTerrain(Pixel pixel) {
		Color pixelColor = new Color(worldmap.getRGB(pixel.getX(), pixel.getY()));
		
		if(DISPATCHER.containsKey(pixelColor)) {
			DISPATCHER.get(pixelColor).setCost(pixel);
		} else {
			pixel.setCost(Double.MAX_VALUE);
		}
	}
	
	public BufferedImage getWorldmap() {
		return this.worldmap;
	}

	public double getPixelDistance() {
		return pixelDistance;
	}

	public void setPixelDistance(double pixelDistance) {
		this.pixelDistance = pixelDistance;
	}
	
	private static Map<Color, TerrainHandler> populateMap() {
		Map<Color, TerrainHandler> dispatcher = new HashMap<>();
		
		dispatcher.put(new Color(255, 0, 0), new CityHandler());
		dispatcher.put(new Color(0, 127, 14), new GrassHandler());
		dispatcher.put(new Color(219, 170, 107), new RoadHandler());
		dispatcher.put(new Color(0, 148, 255), new RiverHandler());
		dispatcher.put(new Color(127, 51, 0), new MountainHandler());
		
		return dispatcher;
	}
	
}
