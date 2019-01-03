package pathfinding.map.neighbor;

import java.util.ArrayList;
import java.util.List;

import pathfinding.map.TileMap;
import pathfinding.map.WeightedPoint;
import pathfinding.map.heuristic.HeuristicScheme;

/**
 * Neighbor selector that returns all eight adjacent tiles: N, S, E, W, NE, SE, NW, SW
 */
public class NeighborEightDirections extends NeighborSelector
{
    @Override
    public List<WeightedPoint> getNeighbors(TileMap map, WeightedPoint cursor, HeuristicScheme distanceCalculator)
    {
    	List<WeightedPoint> neighbors = new ArrayList<WeightedPoint>();
    	
    	if(cursor.getRow()>0 && map.isTraversable(cursor.getRow()-1, cursor.getCol())) {
    		 //north
    		neighbors.add(new WeightedPoint(cursor.getRow()-1, cursor.getCol()));
    		
    	}
    	if(cursor.getRow()>0 && cursor.getCol()>0 && map.isTraversable(cursor.getRow()-1, cursor.getCol()-1)) {
    		//northwest
    		neighbors.add(new WeightedPoint(cursor.getRow()-1, cursor.getCol()-1));
    	}
    	if(cursor.getRow()>0 && cursor.getCol()<map.getCols()-1 && map.isTraversable(cursor.getRow()-1, cursor.getCol()+1)) {
    		//northeast
    		neighbors.add(new WeightedPoint(cursor.getRow()-1, cursor.getCol()+1));
    	}
    	if(cursor.getCol()>0 && map.isTraversable(cursor.getRow(), cursor.getCol()-1)) {
    		//west
    		neighbors.add(new WeightedPoint(cursor.getRow(), cursor.getCol()-1));
    	}
    	if(cursor.getCol() < map.getCols()-1 && map.isTraversable(cursor.getRow(), cursor.getCol()+1)) {
    		//east
    		neighbors.add(new WeightedPoint(cursor.getRow(), cursor.getCol()+1));
    	}
    	if(cursor.getRow()<map.getRows()-1 && map.isTraversable(cursor.getRow()+1, cursor.getCol())) {
    		//south
    		neighbors.add(new WeightedPoint(cursor.getRow()+1, cursor.getCol()));
    	}
    	if(cursor.getRow()<map.getRows()-1 && cursor.getCol()>0 && map.isTraversable(cursor.getRow()+1, map.getCols()-1)) {
    		//southwest
    		neighbors.add(new WeightedPoint(cursor.getRow()+1, cursor.getCol()-1));
    	}
    	if(cursor.getRow()<map.getRows()-1 && cursor.getCol()<map.getCols()-1 && map.isTraversable(cursor.getRow()+1, cursor.getCol()+1)) {
    		//southeast
    		neighbors.add(new WeightedPoint(cursor.getRow()+1, cursor.getCol()+1));
    	}
    	

        return neighbors;
    }

    @Override
    public String getLabel()
    {
        return "8-Directional";
    }


}
