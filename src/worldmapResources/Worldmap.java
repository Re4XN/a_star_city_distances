package worldmapResources;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Worldmap {
	
	private static final Map<Color, Double> TERRAIN_COST = new HashMap<>();
	
	static {
		TERRAIN_COST.put(new Color(255, 0, 0), 0.5);		//city
		TERRAIN_COST.put(new Color(219, 170, 107), 0.5);	//road
		TERRAIN_COST.put(new Color(0, 127, 14), 3d);		//grass
		TERRAIN_COST.put(new Color(0, 148, 255), 10d);		//river or sea
		TERRAIN_COST.put(new Color(127, 51, 0), 15d);		//mountain
	}
	
	private BufferedImage worldmap = null;

	public Worldmap() { }

	public void setPixelTerrain(Pixel pixel) {
	    Color pixelColor = new Color(worldmap.getRGB(pixel.getX(), pixel.getY()));

	    Double cost = TERRAIN_COST.getOrDefault(pixelColor, Double.MAX_VALUE);
	    
	    pixel.addCost(cost);
	}
	
	public BufferedImage getWorldmap() {
		return this.worldmap;
	}
	
	public void setWorldmap(File mapImage) throws IOException {
		worldmap = ImageIO.read(mapImage);
	}
	
}
