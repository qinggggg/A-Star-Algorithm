package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pathfinding.data.structure.StructureSelector;
import pathfinding.map.TileMap;
import pathfinding.map.WeightedPoint;
import pathfinding.map.heuristic.HeuristicScheme;
import pathfinding.map.neighbor.NeighborSelector;

//Uses the A* path finding algorithm to determine the optimal path between two points on a map

public class PathFinder
{
    
    //The state of the algorithm: running, completed with a result, completed with no result
     
    private StatusEnum status;

    
    //The map: rows x columns, false = traversable, true = wall
  
    private TileMap map;

  
    //Tail of path being built by running this algorithm
    
    private WeightedPoint tail;

 
    //current point
    
    private WeightedPoint cursor;

    
    private int countContains;
	private int countInsert;
    private int countDelete;
    private int countDecrease;
    private int countInsertClosed;
	private int countCheckClosed;
    /**
     * Points that have yet to be visited stored as a min-heap based on the point cost (Note that this "set" is actually
     * a heap, so a manual test for whether the point being added should be used prior to calling push().) The heap is
     * used for the open set to quickly return the point with the minimum cost.
     */
//    private Heap<WeightedPoint> openSet;
    private StructureSelector<?> openSet;    
        
    private Set<WeightedPoint> closedSet;

  
    //The means for determining distances 
    private HeuristicScheme heuristic;

  
     //The means for walking directions
    private NeighborSelector neighborSelector;

    private boolean checkOpen;
    private boolean checkClosed;

    private int maxOpen;
//    private boolean bestfirst;

	public void setCountContains(int countContains) {
		this.countContains = countContains;
	}

	public void setCountInsert(int countInsert) {
		this.countInsert = countInsert;
	}

	public void setCountDelete(int countDelete) {
		this.countDelete = countDelete;
	}

	public void setCountDecrease(int countDecrease) {
		this.countDecrease = countDecrease;
	}

	public void setCountInsertClosed(int countInsertClosed) {
		this.countInsertClosed = countInsertClosed;
	}

	public void setCountCheckClosed(int countCheckClosed) {
		this.countCheckClosed = countCheckClosed;
	}
    
    public int getCountContains() {
		return countContains;
	}

	public int getCountInsert() {
		return countInsert;
	}

	public int getCountDelete() {
		return countDelete;
	}

	public int getCountDecrease() {
		return countDecrease;
	}
	
	public int getCountInsertClosed() {
		return countInsertClosed;
	}

	public int getCountCheckClosed() {
		return countCheckClosed;
	}

    
    public int getMaxOpen() {
		return maxOpen;
	}

	public void setMaxOpen(int maxOpen) {
		this.maxOpen = maxOpen;
	}

	//Whether the initial step of the algorithm has been taken, 
    //used to set the tail node and push the start onto the open set
    private boolean initialStep;
    
    //count the time while using different data structure
    long startTime;
    long endTime;
    long totalTime;

    public TileMap getMap()
    {
        return map;
    }

    public WeightedPoint getStart()
    {
        return map.getStart();
    }

    public WeightedPoint getGoal()
    {
        return map.getGoal();
    }

    public WeightedPoint getCursor()
    {
        return cursor;
    }

    
    //Get the seed that generated the current map
     
    public int getSeed()
    {
        return map.getSeed();
    }

    public long getTime() {
    	return endTime - startTime;
    }
    
    
    public void setOpenSet(StructureSelector<?> structure) {
    	 this.openSet = structure;
    }
    
    public StructureSelector<?> getOpenSet() {
    	return openSet;
    }
    

    public Set<WeightedPoint> getClosedSet()
    {
        return closedSet;
    }

    public void setHeuristic(HeuristicScheme heuristic)
    {
        this.heuristic = heuristic;
    }
    
    public HeuristicScheme getHeuristic() {
    	return heuristic;
    }

    public void setNeighborSelector(NeighborSelector neighborSelector)
    {
        this.neighborSelector = neighborSelector;
    }

    
    public NeighborSelector getNeighbor() {
    	return neighborSelector;
    }

    public void setCheckOpen(boolean checkOpen)
    {
        this.checkOpen = checkOpen;
    }

    public void setCheckClosed(boolean checkClosed) {
    	this.checkClosed = checkClosed;
    }
    
    //Construct the PathFinder 
	public PathFinder(TileMap map)
	{
	    reset(map);
	}

    
    //Reset the PathFinder using the original map
    public void reset()
    {
        reset(this.map, this.heuristic, this.neighborSelector, this.openSet);
    }

    //Reset the PathFinder with a redefined map, start, and goal, but leaving the selected heuristic and neighbor selector, data structure
    public void reset(TileMap map)
    {
        reset(map, this.heuristic, this.neighborSelector, this.openSet);
    }

    public void reset(StructureSelector<?> structure) {
    	reset(this.map, this.heuristic, this.neighborSelector, structure);
    }
    
    //Reset the PathFinder
    public void reset(TileMap map, HeuristicScheme heuristic, NeighborSelector neighborSelector, StructureSelector<?> structure)
    {
        this.map = map;

        this.cursor = null;

        this.heuristic = heuristic;
        this.neighborSelector = neighborSelector;

        this.initialStep = true;

        this.status = StatusEnum.RUNNING;

        this.openSet = structure;
        this.closedSet = new HashSet<WeightedPoint>();
    }

    //Run through the next step in the algorithm. Each step represents investigating another possible point on the path
    public void step()
    {
        status = stepInternal();
    }

    //Run through the next step in the algorithm. Each step represents investigating another possible point on the path
    private StatusEnum stepInternal()
    {
        if (initialStep)
        {
        	
        	
            this.tail = map.getStart();
            this.openSet.insert(map.getStart());
            countInsert ++;
            setMaxOpen(openSet.size());
            startTime = System.currentTimeMillis();
//            startTime = System.nanoTime();
            initialStep = false;
        }

        if (status != StatusEnum.RUNNING)
            return status;

        cursor = openSet.deleteMin(); // Pull the cursor off the open set min-heap
        countDelete ++;

        if (cursor == null)
        {
            // The open set was empty, so although we have not reached the goal, there are no more points to investigate
        	endTime = System.currentTimeMillis();
//        	endTime = System.nanoTime();
        	return StatusEnum.COMPLETED_NOT_FOUND;
        }

     
        // The goal has been reached, the path is complete
        if (cursor.equals(map.getGoal()))
        {
        	endTime = System.currentTimeMillis();
//        	endTime = System.nanoTime();
            tail = cursor; // Set the member tail to be used in the reconstruction done in getPath()
            System.out.println( "Time:" + (endTime - startTime) + ", Comparisons:" + openSet.getComparisonNum() + ", Swap:" + openSet.getSwapNum() + ", Contains:" + openSet.getContainsNum() +  ", CheckClose:" + countCheckClosed);
            System.out.println( "Insert Close:" + countInsertClosed + ", Insert: " + countInsert + ", Delete:" + countDelete +  ", Contains:" + countContains + ", Decrease:" + countDecrease +
            		", total:" + (openSet.getComparisonNum() + openSet.getContainsNum() + openSet.getSwapNum() + countCheckClosed + countInsertClosed) );
            System.out.println("MaxOpen:" + getMaxOpen() + ", MaxClose:" + closedSet.size());
            System.out.println();
            return StatusEnum.COMPLETED_FOUND;
        }

       

        // Link the neighbors to the cursor (for backtracking the path when the goal is reached) and calculate their weight
        if (checkOpen) {
        	
        	//when insert a new member in the open set, check whether it is already contained in the open set
        	//if it is, find the duplicated node, compare their f values, it the new node has a smaller f value,
        	//update the smaller f value of the duplicated node in the open set, and set the current node as its previous node;
        	//if not, insert it
        	//when delete the minimum element from the open set, check whether it is the goal
        	//if it is, the path is found
        	//if not, add it in the closed set
        	        	
        	 // Add the cursor point to the closed set
            closedSet.add(cursor);
            countInsertClosed ++;

            // Get the list of neighboring points
            List<WeightedPoint> neighbors = neighborSelector.getNeighbors(map, cursor, heuristic);
        	for (WeightedPoint wp : neighbors)
            {
        		countCheckClosed ++;
                if (map.isTraversable(wp.getRow(), wp.getCol()) && !closedSet.contains(wp))
                {
                	countContains ++;
                    if(openSet.contains(wp)){
               
                    	WeightedPoint containedNode = openSet.containedNode(wp);
                    	float temporateG = cursor.getgCost() + heuristic.distance(cursor, wp);
                   	 	if(temporateG < containedNode.getgCost()) {
                   	 		//delete the contained node and add as a new node
                   	 		containedNode.setActualCost(cursor.getActualCost() + heuristic.distance(cursor, wp));
                   	 		containedNode.setgCost(temporateG);
                   	 		containedNode.sethCost(heuristic.distance(wp, map.getGoal()));
                   	 		containedNode.setPrev(cursor);
                   	 		openSet.decreaseKey(containedNode);  
                   	 		countDecrease ++;

                   	 	}
                   	 		
                    }else {
                    	wp.setActualCost(cursor.getActualCost() + heuristic.distance(cursor, wp));
                        wp.setgCost(cursor.getgCost() + heuristic.distance(cursor, wp));
                        wp.sethCost(heuristic.distance(wp, map.getGoal()));
                        openSet.insert(wp);
                        wp.setPrev(cursor);
                        countInsert ++;
                        
                    }
                  }
                    
               }
        	if(openSet.size()>getMaxOpen()) {
        		setMaxOpen(openSet.size());
        	}
        }else if(checkClosed) {
        	//there is no necessary to check whether the inserting node is already in the open set
        	//even there are duplicated nodes in the open set, just leave it and insert the node
        	//the duplicated node will be examined from the closed set when exploring it
        	//which means, for every exploring node, we have to check whether it is in the closedSet,
        	//if it is already in the closedSet, just delete the node and get another current node from the open set;
        	//if not, insert it in the closed set
        	
        	countCheckClosed ++;
        	while (closedSet.contains(cursor) || !map.isTraversable(cursor)) {
    			cursor = openSet.deleteMin();
    			countDelete ++;
    			if(cursor == null) {
    				endTime = System.currentTimeMillis();
    				return StatusEnum.COMPLETED_NOT_FOUND;
    			}
    		}
        	
        	closedSet.add(cursor);
        	countInsertClosed ++;
        	
        	List<WeightedPoint> neighbors = neighborSelector.getNeighbors(map, cursor, heuristic);
    		
    		for(WeightedPoint wp:neighbors) {
    			countCheckClosed ++;
    			if(map.isTraversable(wp.getRow(), wp.getCol()) && !closedSet.contains(wp)) {
    				wp.setActualCost(cursor.getActualCost() + heuristic.distance(cursor, wp));
                    wp.setgCost(cursor.getgCost() + heuristic.distance(cursor, wp));
                    wp.sethCost(heuristic.distance(wp, map.getGoal()));
                    openSet.insert(wp);
                    wp.setPrev(cursor);
                    countInsert ++;
    			}
    		}
    		if(openSet.size()>getMaxOpen()) {
        		setMaxOpen(openSet.size());
        	}
        }
                
        return StatusEnum.RUNNING;
    }

    //Continue to call step until the algorithm is complete
    public StatusEnum solve()
    {
        while (status == StatusEnum.RUNNING)
        {
            step();
        }
        return status;
    }

    //Get the path finder algorithm status
    public StatusEnum getStatus()
    {
        return status;
    }

    //Constructs and returns a List version of the tail linked list generated by the algorithm
    public List<WeightedPoint> getPath()
    {
        return getPath(this.tail);
    }

    //Constructs and returns a List version of the linked list generated by the algorithm from the specified cursor point
    public List<WeightedPoint> getPath(WeightedPoint cursor)
    {
        List<WeightedPoint> path = new ArrayList<WeightedPoint>();
        while (cursor != null)
        {
            path.add(path.size(), cursor);
            cursor = cursor.getPrev();
        }
        return path;
    }

}
