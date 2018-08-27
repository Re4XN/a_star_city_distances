package aStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import worldmapResources.Pixel;
import worldmapResources.Worldmap;

public class AStar {
	
	public class PixelComparator implements Comparator<Pixel> {
		@Override
		public int compare(Pixel current, Pixel next) {
			if(current.getF() > next.getF())
				return 1;
			if(current.getF() < next.getF())
				return -1;
			return 0;
		}
	}

	private Pixel start = null;
	private Pixel goal = null;
	
	private Worldmap worldmap = null;
	
	public AStar(Pixel start, Pixel goal, Worldmap worldmap) {
		this.start = start;
		this.goal = goal;
		this.worldmap = worldmap;
	}
	
	private boolean isFinished(Pixel start, Pixel goal) {
		return start.equals(goal);
	}
	
	private List<Pixel> getPath(Pixel current) {

		List<Pixel> path;
		
		if(current.getParentPixel() == null)
			return Collections.emptyList();
		
		path = new ArrayList<>(getPath(current.getParentPixel()));
		path.add(current);
		
		return path;
		
	}
	
	public List<Pixel> search() {
		
		this.worldmap.setPixelTerrain(start);
		this.worldmap.setPixelTerrain(goal);
		
		int[][] directions = new int[][] {
			new int[] {1, 0},
			new int[] {1, 1},
			new int[] {0, 1},
			new int[] {-1, -1},
			new int[] {-1, 0},
			new int[] {-1, 1},
			new int[] {0, -1},
			new int[] {1, -1}
		};
		
		Set<Pixel> closedPixels = new HashSet<>();
		
		Queue<Pixel> openPixels = new PriorityQueue<>(new PixelComparator());
		this.start.calculateHeuristic(this.goal);
		this.start.calculateF(this.goal);
		openPixels.add(this.start);
		
		while(!openPixels.isEmpty()) {
			Pixel current = openPixels.poll();
			this.worldmap.setPixelTerrain(current);

			System.out.println("WHILE LOOP: " + current.getCost());
			
			if(isFinished(current, this.goal))
				return getPath(current);
			
			closedPixels.add(current);
			
			for(int[] direction : directions) {
				Pixel next = current.getNextPixel(direction);
				
				if(next.outOfBounds(worldmap))
					continue;
				
				if(closedPixels.contains(next))
					continue;
				
				this.worldmap.setPixelTerrain(next);
				
				System.out.println("FOR LOOP:" + current.getCost() + " + " + next.getCost());
				next.setCost(current.getCost() + next.getCost());
				
				next.calculateHeuristic(this.goal);
				next.setParentPixel(current);
				next.calculateF(this.goal);
				
				if(!openPixels.contains(next))
					openPixels.add(next);
			}
		}
		return null;
	}
}
