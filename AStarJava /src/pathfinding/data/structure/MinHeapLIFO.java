package pathfinding.data.structure;

import java.util.ArrayList;
import java.util.List;

import pathfinding.map.WeightedPoint;

public class MinHeapLIFO extends StructureSelector{
	
	public static void main(String []args) {
		MinHeapLIFO heap = new MinHeapLIFO();
		
		
		WeightedPoint node1 = new WeightedPoint(1,1);
		WeightedPoint node2 = new WeightedPoint(2,2);
		WeightedPoint node3 = new WeightedPoint(5,6);
		WeightedPoint node4 = new WeightedPoint(7,2);
		WeightedPoint node5 = new WeightedPoint(3,1);
		WeightedPoint node6 = new WeightedPoint(4,2);
		WeightedPoint node7 = new WeightedPoint(4,2);
		WeightedPoint node8 = new WeightedPoint(6,2);
		
		node1.setgCost(2);
		node1.sethCost(5);
		
		node2.setgCost(1);
		node2.sethCost(6);
		
		node3.setgCost(5);
		node3.sethCost(2);
		
		node4.setgCost(4);
		node4.sethCost(3);
		
		node5.setgCost(2);
		node5.sethCost(7);
		
		node6.setgCost(3);
		node6.sethCost(5);
		
		node7.setgCost(3);
		node7.sethCost(4);
//		
//		heap.insert(node1);
//		heap.insert(node2);
//		heap.insert(node3);
		heap.contains(node4);
		heap.insert(node4);
		heap.contains(node5);
		heap.insert(node5);
		heap.contains(node6);
		heap.insert(node6);
		heap.deleteMin();
//		heap.insert(node7);
		
		System.out.println(heap.getComparisonNum());
		System.out.println(heap.getSwapNum());
		System.out.println(heap.getContainsNum());
//		
	}

	private ArrayList<WeightedPoint> heap;
//	private HashMap<WeightedPoint, Integer> hashHeap;
	private int containedNodeIndex;
	private int countComparison;
	private int countContains;
	private int countSwap;

	
	public MinHeapLIFO() {
		heap = new ArrayList<WeightedPoint>();
//		hashHeap = new HashMap<WeightedPoint, Integer>();
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
//		return heap.contains(node);
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

	@Override
	public void insert(WeightedPoint node) {
		// TODO Auto-generated method stub
		//for LIFO
		ArrayList<WeightedPoint> temp = new ArrayList<WeightedPoint>();
		temp.add(node);
		for(int i=0; i<heap.size(); i++) {
			temp.add(heap.get(i));
		}
		heap = temp;
		bubbleDown(0);
//		heap.add(node);
//		node.setIndex1(heap.size()-1);
//		bubbleUp(heap.size()-1);
	}


	private void bubbleDown(int i) {
		
		int left = LEFT(i);
		int right = RIGHT(i);
		int smallest = i;
		countComparison ++;
		if(left < heap.size() && heap.get(left).getTotalCost() < heap.get(i).getTotalCost()) {
			smallest = left;
//			heap.get(i).setIndex1(left);
//			heap.get(left).setIndex1(i);
		}
		if(right < heap.size() && heap.get(right).getTotalCost() < heap.get(i).getTotalCost() && heap.get(right).getTotalCost()<heap.get(left).getTotalCost())
		{
			smallest = right;
//			heap.get(i).setIndex1(right);
//			heap.get(right).setIndex1(i);
		}
		if(smallest != i) {
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
	public WeightedPoint deleteMin() {
		// TODO Auto-generated method stub
		try{
			
			if(heap.size() == 0)
				throw new Exception("Index out of range (min-heap underflow)");
			WeightedPoint deleted = heap.get(0);
			heap.set(0, heap.get(heap.size()-1));
			heap.remove(heap.size()-1);
//			updatedPoint.add(heap.get(0));
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
//				break;
//			}
//		}
		bubbleUp(containedNodeIndex);
	}

	
	//heapify the tree from the button
	private void bubbleUp(int i){
		countComparison ++;
		if(i>0 && heap.get(PARENT(i)).getTotalCost() > heap.get(i).getTotalCost()){
			swap(i, PARENT(i));
			bubbleUp(PARENT(i));
		}
				
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
		return "Min-Heap LIFO";
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
//		return heap.get(i);
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
