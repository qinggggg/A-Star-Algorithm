package pathfinding.map.heuristic;

import pathfinding.map.WeightedPoint;

public class HeuristicOctile extends HeuristicScheme{

	@Override
	public float distance(WeightedPoint node1, WeightedPoint node2) {
		// TODO Auto-generated method stub
		//use 1.4 to represent Math.sqrt(2)
		return (float) (Math.max(Math.abs(node2.getCol() - node1.getCol()), Math.abs(node2.getRow() - node1.getRow())) + (1.4 - 1) * (Math.min(Math.abs(node2.getCol() - node1.getCol()), Math.abs(node2.getRow() - node1.getRow()))));
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Octile";
	}

}
