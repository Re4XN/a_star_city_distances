package main;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import aStar.AStar;
import worldmapResources.Pixel;
import worldmapResources.Worldmap;

public class Main {

	public static void main(String[] args) {
		
		double pixelDistance = Double.parseDouble(args[2]);
		
		String[] startAux = args[0].replaceAll("[()]", "").split(",");
		String[] goalAux = args[1].replaceAll("[()]", "").split(",");
		
		Pixel start = new Pixel(Integer.parseInt(startAux[0]), Integer.parseInt(startAux[1]));
		Pixel goal = new Pixel(Integer.parseInt(goalAux[0]), Integer.parseInt(goalAux[1]));
		
		String file = args[3];
		
		for(String s : args) {
			System.out.println(s);
		}
		
		Worldmap map = null;
		
		try {
			map = new Worldmap(new File(file));
			map.setPixelDistance(pixelDistance);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AStar crawler = new AStar(start, goal, map);
		List<Pixel> path = crawler.search();
		
		for(Pixel pixel : path) {
			System.out.println(pixel);
			map.getWorldmap().setRGB(pixel.getX(), pixel.getY(), new Color(255, 0, 110).getRGB());
		}
		
		File altMapImage = new File("new_map.png");
		
		try {
			ImageIO.write(map.getWorldmap(), "png", altMapImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(path.size() * map.getPixelDistance() + " km");
	}

}
