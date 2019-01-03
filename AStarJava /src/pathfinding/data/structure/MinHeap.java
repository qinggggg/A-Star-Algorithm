package pathfinding.data.structure;

import java.util.ArrayList;
import java.util.List;

import pathfinding.map.WeightedPoint;

public class MinHeap extends StructureSelector{

	private ArrayList<WeightedPoint> heap;
	private int containedNodeIndex;
	private int countComparison;
	private int countContains;
	private int countSwap;
	
	public MinHeap() {
		heap = new ArrayList<WeightedPoint>();
	}
	
	private int PARENT(int i){
		if(i == 0){
			return 0;
		}	
		return (i-1)/2;
	}
	
	private int LEFT(int i){
		return (2*i + 1);
	}
	
	private int RIGHT(int i){
		return (2*i + 2);
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return heap.isEmpty();
	}

	@Override
	public boolean contains(WeightedPoint node) {
		// TODO Auto-generated method stub
		for(int i=0; i<heap.size(); i++){
			countContains ++;
			if(node.equals(heap.get(i))) {
				containedNodeIndex = i;
				return true;
			}
		}
		return false;
	}

	@Override
	public WeightedPoint containedNode(WeightedPoint node) {
		// TODO Auto-generated method stub
//		for(int i=0; i<heap.size(); i++){
//			if(node.equals(heap.get(i)))
//				return heap.get(i);
//		}
//		return null;
		return heap.get(containedNodeIndex);
	}

	//heapify the tree from the button
	private void bubbleUp(int i){
		countComparison ++;
		if(i>0 && heap.get(PARENT(i)).getTotalCost() >= heap.get(i).getTotalCost()){
			swap(i, PARENT(i));
			bubbleUp(PARENT(i));
		}
				
	}
	
	//heapify the tree from the top
	private void bubbleDown(int i){
		countComparison ++;
		int left = LEFT(i);
		int right = RIGHT(i);
		int smallest = i;
		if(left < size() && heap.get(left).getTotalCost() <= heap.get(i).getTotalCost()){
			smallest = left;
		}
		if(right < size() && heap.get(right).getTotalCost() <= heap.get(i).getTotalCost() && heap.get(right).getTotalCost() <= heap.get(left).getTotalCost()){
			smallest = right;
		}
		if(smallest != i){
			swap(i, smallest);
			bubbleDown(smallest);
		}				
	}
	
	private void swap(int i, int j) {
		countSwap ++;
		WeightedPoint temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
	

	@Override
	public void insert(WeightedPoint node) {
		// TODO Auto-generated method stub
		heap.add(node);
		bubbleUp(heap.size()-1);
	}

	@Override
	public WeightedPoint deleteMin() {
		// TODO Auto-generated method stub
		try{
			
			if(heap.size() == 0)
				throw new Exception("Index out of range (min-heap underflow)");
			WeightedPoint deleted = heap.get(0);
			heap.set(0, heap.get(heap.size()-1));
			heap.remove(heap.size()-1);
			bubbleDown(0);
			return deleted;
			
		}catch (Exception ex){
			System.out.println(ex);
			return null;
		}
	}


	@Override
	public void decreaseKey(WeightedPoint node) {
		// TODO Auto-generated method stub
//		for(int i=0; i<heap.size(); i++){
//			if(node.equals(heap.get(i))) {
//				bubbleUp(i);
//			}
//		}
		bubbleUp(containedNodeIndex);
	}

	@Override
	public int stackLevel() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		heap = new ArrayList<WeightedPoint>();
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Min-Heap";
	}

	@Override
	public List getList() {
		// TODO Auto-generated method stub
		List<WeightedPoint> output = new ArrayList<WeightedPoint>();
	    for (int i = 0; i < size(); i++)
	    	output.add(heap.get(i));
	    return output;  
	}



	@Override
	public WeightedPoint getNode(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getComparisonNum() {
		// TODO Auto-generated method stub
		return countComparison;
	}

	@Override
	public int getSwapNum() {
		// TODO Auto-generated method stub
		return countSwap;
	}

	@Override
	public int getContainsNum() {
		// TODO Auto-generated method stub
		return countContains;
	}

	@Override
	public void setComparisonNum(int num) {
		// TODO Auto-generated method stub
		this.countComparison = num;
	}

	@Override
	public void setSwapNum(int num) {
		// TODO Auto-generated method stub
		this.countSwap = num;
	}

	@Override
	public void setContainsNum(int num) {
		// TODO Auto-generated method stub
		this.countContains = num;
	}



}
