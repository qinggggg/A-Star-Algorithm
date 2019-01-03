package pathfinding.map.heuristic;

import pathfinding.map.WeightedPoint;

/**
 * Manhattan distance heuristic
 */
public class HeuristicManhattan extends HeuristicScheme
{
    @Override
    public float distance(WeightedPoint one, WeightedPoint two)
    {
        return Math.abs(two.getCol() - one.getCol()) + Math.abs(two.getRow() - one.getRow());
    }

    @Override
    public String getLabel()
    {
        return "Manhattan";
    }

 
}