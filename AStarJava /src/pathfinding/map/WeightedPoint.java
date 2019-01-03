package pathfinding.map;

//A point comprised of a row and a column with an associated cost comprised of a to cost and a from cost for use in an
//A* search algorithm
 
public class WeightedPoint implements Comparable<WeightedPoint>
{

    private int row;

    private int col;

    //The cost from a starting point to have reached this point (g)
    private float gCost;

    //The estimated cost to go from this point to a goal (h)

    private float hCost;

    //The actual cost for every search
    private float actualCost;

    //A link to the previous point in a linked list of points tracing the path from this point back to the start
    private WeightedPoint prev;

    
    //a weighted point without assigning a from cost
    public WeightedPoint(int row, int col)
    {
        this(row, col, 0);
    }

    //a weighted point
    public WeightedPoint(int row, int col, int fromCost)
    {
        this.row = row;
        this.col = col;
        this.gCost = fromCost;
    }

    public int getRow()
    {
        return row;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public int getCol()
    {
        return col;
    }

    public void setCol(int col)
    {
        this.col = col;
    }

    public float getTotalCost()
    {
        return gCost + hCost;
    }

    public float getgCost()
    {
        return gCost;
    }

    public void setgCost(float fromCost)
    {
        this.gCost = fromCost;
    }

    public float gethCost()
    {
        return hCost;
    }

    public void sethCost(float toCost)
    {
        this.hCost = toCost;
    }

    public float getActualCost() {
		return actualCost;
	}

	public void setActualCost(float actualCost) {
		this.actualCost = actualCost;
	}

	//Get the link to the previous point in a linked list of points tracing the path from this point back to the start
    public WeightedPoint getPrev()
    {
        return prev;
    }

    public void setPrev(WeightedPoint prev)
    {
        this.prev = prev;
    }

    @Override
    public int compareTo(WeightedPoint other)
    {
        if (other == null || this.getTotalCost() > other.getTotalCost())
            return 1;
        else if (this.getTotalCost() < other.getTotalCost())
            return -1;
        return 0;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + col;
        result = prime * result + row;
        return result;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;
        else if (other instanceof WeightedPoint)
        {
            WeightedPoint otherWp = (WeightedPoint)other;
            return (otherWp.row == row && otherWp.col == col);
        }
        return false;
    }

    public String toString()
    {
        return "(r" + row + ", c" + col + ")";
    }

    //Returns a String starting with the specified label and the weighted point toString method in a null-safe manner
    public static String toLabeledString(String label, WeightedPoint wp)
    {
        return label + ": " + (wp == null ? "" : wp.toString());
    }

	
}
