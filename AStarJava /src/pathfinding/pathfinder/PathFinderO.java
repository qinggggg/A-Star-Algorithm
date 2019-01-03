package pathfinding.pathfinder;

import java.util.List;

import pathfinding.StatusEnum;
import pathfinding.map.TileMap;
import pathfinding.map.WeightedPoint;

public class PathFinderO extends PathFinderSelector{

	//when insert a new member in the open set, check whether it is already contained in the open set
	//if it is, find the duplicated node, compare their f values, it the new node has a smaller f value,
	//update the smaller f value of the duplicated node in the open set, and set the current node as its previous node;
	//if not, insert it
	//when delete the minimum element from the open set, check whether it is the goal
	//if it is, the path is found
	//if not, add it in the closed set
	
	
	public PathFinderO(TileMap map) {
		super(map);
		// TODO Auto-generated constructor stub
	}

	@Override
	public StatusEnum stepInternal() {
		// TODO Auto-generated method stub
		if(initialStep) {
			this.tail = map.getStart();
			this.openSet.insert(map.getStart());
			startTime = System.currentTimeMillis();
			initialStep = false;
		}
		if(status != StatusEnum.RUNNING)
			return status;
		
		cursor = openSet.deleteMin();
		if(cursor == null) {
			endTime = System.currentTimeMillis();
			return StatusEnum.COMPLETED_NOT_FOUND;
		}
		
		if(cursor.equals(map.getGoal())) {
			endTime = System.currentTimeMillis();
			tail = cursor;
			return StatusEnum.COMPLETED_FOUND;
		}
		
		closedSet.add(cursor);
		
		List<WeightedPoint> neighbors = neighborSelector.getNeighbors(map, cursor, heuristic);
		
		for(WeightedPoint wp:neighbors) {
			if(map.isTraversable(wp.getRow(), wp.getCol()) && !closedSet.contains(wp)) {
				if(openSet.contains(wp)) {
					WeightedPoint containedPoint = openSet.containedNode(wp);
					float temporateG = cursor.getgCost() + heuristic.distance(cursor, wp);
					if(temporateG < containedPoint.getgCost()) {
						containedPoint.setActualCost(cursor.getActualCost() + heuristic.distance(cursor, wp));
						containedPoint.setgCost(temporateG);
						containedPoint.sethCost(heuristic.distance(wp, map.getGoal()));
						containedPoint.setPrev(cursor);
						openSet.decreaseKey(containedPoint);
					}
				}
			}else {
				wp.setActualCost(cursor.getActualCost() + heuristic.distance(cursor, wp));
				wp.setgCost(cursor.getgCost() + heuristic.distance(cursor, wp));
				wp.sethCost(heuristic.distance(wp, map.getGoal()));
				openSet.insert(wp);
				wp.setPrev(cursor);
			}
		}
		
		
		return StatusEnum.RUNNING;
	}



}
