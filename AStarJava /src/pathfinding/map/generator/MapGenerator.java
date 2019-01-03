package pathfinding.map.generator;

import java.util.Random;

import pathfinding.map.WeightedPoint;


public class MapGenerator 
{
    
	private double percent = 0.0;
	
    public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	public void addObstacles(Random rnd, boolean[][] map, WeightedPoint start, WeightedPoint goal, double percent)
    {
        // The base generator class adds no non-border obstacles
    	this.percent = percent;
    	int pointCount = (int) (map.length * map[0].length * percent);
        int r, c;
        for (int i = 0; i < pointCount; i++)
        {
            r = rnd.nextInt(Math.max(1, map.length));
            c = rnd.nextInt(Math.max(1, map[0].length));
            if ((c != start.getCol() || r != start.getRow()) && (c != goal.getCol() ||  r != goal.getRow()) && (map[r][c] == false))
            {
                map[r][c] = true;
            }else {
            	i--;
            }
            
        }
    }



	//Get a random traversable point on the map
    public WeightedPoint generatePoint(Random rnd, boolean[][] map)
    {
        int r = rnd.nextInt(map.length - 2) + 1;
        int c = rnd.nextInt(map[0].length - 2) + 1;
        map[r][c] = false;
        return new WeightedPoint(r, c);
    }


    /**
     * Get all the map generation schemes that extend this base class
     * 
     * @return heuristicSchemes
     */
//    public static GenerationScheme[] getAllGenerators()
//    {
//        return new GenerationScheme[] {
//                                       new GeneratorRandom(), 
//                                       
//                                      };
        //new GeneratorLines(),new GeneratorPerfectMaze(),new MapGenerator(), 
         
//    }

}
