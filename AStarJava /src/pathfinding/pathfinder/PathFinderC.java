package pathfinding.pathfinder;

import java.util.List;

import pathfinding.StatusEnum;
import pathfinding.map.TileMap;
import pathfinding.map.WeightedPoint;


public class PathFinderC extends PathFinderSelector{
	
	//there is no necessary to check whether the inserting node is already in the open set
	//even there are duplicated nodes in the open set, just leave it and insert the new node
	//the duplicated node will be examined from the closed set when exploring it
	//which means, for every exploring node, we have to check whether it is in the closedSet,
	//if it is already in the closedSet, just delete the node and get another current node from the open set;
	//if not, insert it in the closed set
	
	public PathFinderC(TileMap map) {
		super(map);
	}

	@Override
	public StatusEnum stepInternal() {
		// TODO Auto-generated method stub
		if(initialStep) {
			startTime = System.currentTimeMillis();
			this.tail = map.getStart();
			this.openSet.insert(map.getStart());
//			startTime = System.currentTimeMillis();
			initialStep = false;
		}
		
		if(status != StatusEnum.RUNNING)
			return status;
		
		cursor = openSet.deleteMin();
				if(cursor == null) {
			endTime = System.currentTimeMillis();
			return StatusEnum.COMPLETED_NOT_FOUND;
		}
		
		while (closedSet.contains(cursor) || !map.isTraversable(cursor)) {
			cursor = openSet.deleteMin();
			if(cursor == null) {
				endTime = System.currentTimeMillis();
				return StatusEnum.COMPLETED_NOT_FOUND;
			}
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
				wp.setActualCost(cursor.getActualCost() + heuristic.distance(cursor, wp));
				wp.setgCost(cursor.getgCost() + heuristic.distance(cursor, wp));
				wp.sethCost(heuristic.distance(wp, map.getGoal()));
				wp.setPrev(cursor);
			}
		}
		
		return StatusEnum.RUNNING;
	}


}
