package pathfinding.map.heuristic;

import pathfinding.map.WeightedPoint;

//Calculates distance for use in determining the heuristic for the path finding algorithm
public abstract class HeuristicScheme
{

    public abstract float distance(WeightedPoint one, WeightedPoint two);


    public abstract String getLabel();

    public String toString()
    {
        return getLabel();
    }

    //Get all the heuristic schemes that extend this base abstract class
    public static HeuristicScheme[] getAllHeuristics()
    {
        return new HeuristicScheme[] {new HeuristicManhattan(), new HeuristicEuclidean(), new HeuristicOctile()};
    }
}