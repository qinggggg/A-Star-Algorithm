package pathfinding.map.generator;

import java.util.Random;

import pathfinding.map.TileMap;
import pathfinding.map.WeightedPoint;

//Randomly generates a tilemap

public class MapManager
{

	//The stored seed for map generation, initialized to some random value and incremented when the generate button is pressed
    private int mapSeed = new Random().nextInt(10000);
    
    //The single permitted instance of this class
    private static final MapManager onlyOne = new MapManager();

    private int height = 30;
    private int width = 30;

    public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	//The random number generator used to produce the varience in the tile maps
    private Random rnd;
    
    private double percent; 

    //Holds the implementation of the map generator scheme that is currently being used
    private MapGenerator generator = new MapGenerator();

    public MapManager()
    {
        this.rnd = new Random(mapSeed);
    }

    //Get the one instance of the MapManager class
    public MapManager getInstance()
    {
        return onlyOne;
    }

    public void setGenerator(MapGenerator generator)
    {
        this.generator = generator;
    }

    //Generate a random TileMap
    public TileMap generate(boolean increment, int height, int width, double percent)
    {
        if (increment)
        {
            mapSeed++;
        }

        return generate(mapSeed, height, width, percent);
    }

    //Generate a random TileMap
    public TileMap generate(int seed, int height, int width, double percent)
    {
        rnd = new Random(seed);
        this.mapSeed = seed;
        this.height = height;
        this.width = width;
        this.percent = percent;
        boolean[][] map = new boolean[height][width];
        
        WeightedPoint start = generator.generatePoint(rnd, map);
        WeightedPoint goal = generator.generatePoint(rnd, map);

        // Generate random blocks on the map
        generator.addObstacles(rnd, map, start, goal, percent);

        // Put a border on all maps
//        generator.addBorder(map);

        return new TileMap(map, start, goal, seed);
    }

	public TileMap generate(boolean increment) {
		// TODO Auto-generated method stub
		 if (increment)
	        {
	            mapSeed++;
	        }

		 return generate(mapSeed, this.height, this.width, this.percent);
	}
}
