package pathfinding.map.neighbor;

import java.util.ArrayList;
import java.util.List;

import pathfinding.map.TileMap;
import pathfinding.map.WeightedPoint;
import pathfinding.map.heuristic.HeuristicScheme;

/**
 * Neighbor selector that returns adjacent tiles excluding diagonal moves: N, S, E, W
 */
public class NeighborFourDirections extends NeighborSelector
{
    @Override
    public List<WeightedPoint> getNeighbors(TileMap map, WeightedPoint cursor, HeuristicScheme distanceCalculator)
    {
        List<WeightedPoint> neighbors = new ArrayList<WeightedPoint>();

        // If the neighbor is not out of bounds and that the neighbor is traversable, then add it to the list
        if (cursor.getRow() > 0 && map.isTraversable(cursor.getRow() - 1, cursor.getCol()) )
        {
            neighbors.add( new WeightedPoint(cursor.getRow() - 1, cursor.getCol()) ); // North
        }
        if (cursor.getCol() < map.getCols() - 1 && map.isTraversable(cursor.getRow(), cursor.getCol() + 1) )
        {
            neighbors.add( new WeightedPoint(cursor.getRow(), cursor.getCol() + 1) ); // East
        }
        if (cursor.getRow() < map.getRows() - 1 && map.isTraversable(cursor.getRow() + 1, cursor.getCol()))
        {
            neighbors.add( new WeightedPoint(cursor.getRow() + 1, cursor.getCol()) ); // South
        }
        if (cursor.getCol() > 0 && map.isTraversable(cursor.getRow(), cursor.getCol() - 1) )
        {
            neighbors.add( new WeightedPoint(cursor.getRow(), cursor.getCol() - 1) ); // West
        }

        return neighbors;
    }

    @Override
    public String getLabel()
    {
        return "4-directional";
    }


}
