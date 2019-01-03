package pathfinding.data.structure;

import java.util.ArrayList;
import java.util.List;

import pathfinding.map.WeightedPoint;


public abstract class StructureSelector<T>{
	
	//all members stored in the data structure
	public abstract int size();
	
	public abstract boolean isEmpty();
	
	public abstract boolean contains(WeightedPoint node);
	
	//return the node that contained in the structure
	public abstract WeightedPoint containedNode(WeightedPoint node);

	public abstract void insert (WeightedPoint node);
	
	public abstract WeightedPoint deleteMin();
	
	public abstract void decreaseKey(WeightedPoint node);
	
	public abstract int stackLevel();
	
	public abstract WeightedPoint getNode(int i);
	
	public abstract void setComparisonNum(int num);
	
	public abstract void setSwapNum(int num);
	
	public abstract void setContainsNum(int num);
	
	public abstract int getComparisonNum();
	 
	public abstract int getSwapNum();
	 
	public abstract int getContainsNum();
	
	//empty the openSet
	public abstract void clear(); 
	
//	public abstract int getIndex(WeightedPoint node);

	public abstract String getLabel();
	
	public String toString()
	    {
	        return getLabel();
	    }
	
	public abstract List<WeightedPoint> getList();
	    
	
	 @SuppressWarnings("rawtypes")
	public static StructureSelector[] getAllDataStructures(){
			return new StructureSelector[] {
											
											
//											new UnsortedArray(), 
											new UnsortedLIFO(), 
//											new MultiHeapLIFO(),
											new MinHeapLIFO(),
											new MinHeap(), 
											new MultiArray()
//											new MinHeap()};
//											new MultiStackHeap(), new UnsortedArray()
											};
		}
}
