package worldmapResources;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Worldmap {
	
	private BufferedImage worldmap = null;
	
	private double pixelDistance = 8.2372; 

	public Worldmap(File mapImage) throws IOException {
		worldmap = ImageIO.read(mapImage);
	}
	
	public void setPixelTerrain(Pixel pixel) {
		
		Color pixelColor = new Color(worldmap.getRGB(pixel.getX(), pixel.getY()));
		
		if(isCity(pixelColor)) {
			pixel.setCost(pixel.getCost() + 0.5);
		} else if (isRoad(pixelColor)) {
			pixel.setCost(pixel.getCost() + 0.5);
		} else if (isGrass(pixelColor)) {
			pixel.setCost(pixel.getCost() + 3);
		} else if (isRiver(pixelColor)) {
			pixel.setCost(pixel.getCost() + 10);
		} else if (isMountain(pixelColor)) {
			pixel.setCost(pixel.getCost() + 15);
		} else {
			pixel.setCost(Double.MAX_VALUE);
		}
	}
	
	private boolean isCity(Color pixelColor) {
		return pixelColor.getRed() == 255 && pixelColor.getGreen() == 0 && pixelColor.getBlue() == 0;
	}
	
	private boolean isGrass(Color pixelColor) {
		return pixelColor.getRed() == 0 && pixelColor.getGreen() == 127 && pixelColor.getBlue() == 14;
	}
	
	private boolean isRoad(Color pixelColor) {
		return pixelColor.getRed() == 219 && pixelColor.getGreen() == 170 && pixelColor.getBlue() == 107;
	}
	
	private boolean isRiver(Color pixelColor) {
		return pixelColor.getRed() == 0 && pixelColor.getGreen() == 148 && pixelColor.getBlue() == 255;
	}
	
	private boolean isMountain(Color pixelColor) {
		return pixelColor.getRed() == 127 && pixelColor.getGreen() == 51 && pixelColor.getBlue() == 0;
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
	
}
