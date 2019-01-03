package pathfinding.data.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import pathfinding.map.WeightedPoint;

public class MultiArray<T> extends StructureSelector{

	//test
			public static void main(String args[]){
				
//				ArrayList<ArrayList<Integer>> text = new ArrayList<ArrayList<Integer>>();
//				ArrayList<Integer> array = new ArrayList<Integer>();
//				ArrayList<Integer> array1 = new ArrayList<Integer>();
//				array.add(7);
//				array.add(7);
//				array1.add(8);
//				array1.add(9);
//				text.add(array);
//				text.add(array1);
//				text.get(1).remove(0);
//				System.out.println("array heap size: " + text.size() + "  " + text.get(1).size());
				
				
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
				
				MultiArray<WeightedPoint> array = new MultiArray<WeightedPoint>();
				array.insert(node1);
				array.insert(node2);
				array.insert(node3);
				array.insert(node4);
				
				array.insert(node6);
				array.insert(node5);
				array.get(1);
				
				System.out.println(array.stackLevel());
				if(array.contains(node7)) {
					System.out.println("Yeah");
					node6.setgCost(1);
					node6.sethCost(1);			
					array.decreaseKey(node6);

				}
			
				System.out.println(array.get(1).get(0));
				System.out.println(array.stackLevel());
//				System.out.println(array.get(1).get(0));
//				array.deleteNode(node3);
//				array.deleteMin();
//				System.out.println(array.stackLevel());
//				System.out.print(array.getList());
//				array.getList();
//				array.clear();
				
			}
			
	
	

	private ArrayList<ArrayList<WeightedPoint>> mheap;	
	private WeightedPoint getContained;
	private int containedIndex1, containedIndex2;
	
	private int countComparison;
	private int countContains;
	private int countSwap;
	
	public MultiArray() {
		mheap = new ArrayList<ArrayList<WeightedPoint>>();
	}
	
	
	private ArrayList<WeightedPoint> get(int i) {
		// TODO Auto-generated method stub
		return mheap.get(i);
	}

	//parent stack index of node i on the heap
	private int PARENT(int i) {
		if(i == 0){
			return 0;
		}	
		return (i-1)/2;		
	}
			
	//left child stack index of node i on the heap
	private int LEFT(int i){
		return (2*i + 1);
	}
			
	//right child stack index of node i on the heap
	private int RIGHT(int i){
		return (2*i + 2);
	}
		
	@Override
	public int size() {
		// TODO Auto-generated method stub
		int count = 0;
		if(!mheap.isEmpty()) {
			for(int i=0; i<mheap.size(); i++) {
				count += mheap.get(i).size();
			}
		}
		return count;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return mheap.isEmpty();
	}

	@Override
	public boolean contains(WeightedPoint node) {
		// TODO Auto-generated method stub
		if(node == null){
			for(int i=0; i<mheap.size(); i++){
				for(int j=0; j<mheap.get(i).size(); j++){
					if(mheap.get(i).get(j) == null)
						return true;
				}
				
			}
		}else{
			for(int i=0; i<mheap.size(); i++){
				for(int j=0; j<mheap.get(i).size(); j++){
					countContains ++;
					if(node.equals(mheap.get(i).get(j))) {
						getContained = mheap.get(i).get(j);
						containedIndex1 = i;
						containedIndex2 = j;
						return true;
					}
						
				}
				
			}
		}
		return false;
		
	}

	@Override
	public WeightedPoint containedNode(WeightedPoint node) {
		// TODO Auto-generated method stub
//		for(int i=0; i<mheap.size(); i++){
//			for(int j=0; j<mheap.get(i).size(); j++){
//				if(node.equals(mheap.get(i).get(j)))
//					return mheap.get(i).get(j);
//			}
//			
//		}
		return mheap.get(containedIndex1).get(containedIndex2);
	}
	
	//heapify the tree from the bottom
	private void bubbleUp(int i) {
	// TODO Auto-generated method stub
		countComparison ++;
		if(i>0 && mheap.get(PARENT(i)).get(0).getTotalCost() > mheap.get(i).get(0).getTotalCost()){
			swap(i, PARENT(i));
			bubbleUp(PARENT(i));
		}
	}

	private void swap(int x, int y) {
		countSwap ++;
		ArrayList<WeightedPoint> temp = mheap.get(x);
		mheap.set(x, mheap.get(y));
		mheap.set(y, temp);
	}
		
	//heapify the tree from the top
	private void bubbleDown(int i) {
		// TODO Auto-generated method stub
		countComparison ++;
		int left = LEFT(i);
		int right = RIGHT(i);
		int smallest = i;
		if(left < mheap.size() && mheap.get(left).get(0).getTotalCost() < mheap.get(i).get(0).getTotalCost()){
			smallest = left;
		}
		if(right < mheap.size() && mheap.get(right).get(0).getTotalCost() < mheap.get(i).get(0).getTotalCost() && 
				mheap.get(right).get(0).getTotalCost()<mheap.get(left).get(0).getTotalCost()){
			smallest = right;
		}
		if(smallest != i){
			swap(i, smallest);
			bubbleDown(smallest);
		}
	}
	
	
	//bubble up the tree for the specific elements from a stack on the whole heap
	//when decrease the key
	public void bubbleUp(int i, int j) {
		countComparison ++;
//		int count = 0;
		if(i>=0 && j>=0) {
			WeightedPoint decreasedKey = mheap.get(i).get(j);
			//as there are at most two different stacks on the heap, and decrease vale must be smaller
			//when there are two stacks, it must be in the second stack
			countComparison ++;
			if(i == 1) {
				mheap.get(i-1).add(decreasedKey);
				mheap.get(i).remove(j);
				if(mheap.get(i).isEmpty()) {
					mheap.remove(i);
				}
			}else if( i==0 ){
				ArrayList<WeightedPoint> stack = new ArrayList<WeightedPoint>();
				stack.add(decreasedKey);
				mheap.get(i).remove(j);
				if (mheap.get(i).isEmpty()) {
					mheap.get(i).add(decreasedKey);
				}else {
					mheap.add(stack);
					bubbleUp(mheap.size()-1);
				}
			}
//			for(int k=i-1; k>=0; k--) {
//				if(mheap.get(k).get(0).getTotalCost() == decreasedKey.getTotalCost()) {
////					System.out.println("Move to new Array and delete the old one");
//					mheap.get(k).add(decreasedKey);
//					mheap.get(i).remove(j);
//					//when the removed element is the only elements on the stack
//					//move the last stack on the heap to the location, and bubbleDown form the location
//					//(the process is same as deleting the root of the heap)
//					if(mheap.get(i).isEmpty()) {
//						if(i != mheap.size()-1) {
//							mheap.set(i, mheap.get(mheap.size()-1));
//							System.out.println("size of the heap: " + mheap.size());
//							mheap.remove(mheap.size()-1);
//							System.out.println(mheap.size());
//							bubbleDown(i);
//						}else {
//							System.out.println("Removed the last array on the heap!");
//							mheap.remove(i);
//						}	
//					}
//					
//					count = 1;
//					break;
//				}
//			
//			}
//			if(count == 0) {
//				//when the decreaedKey is a new value on the heap
//				//create a new stack node on the heap
//				System.out.println("Created new array!");
//				ArrayList<WeightedPoint> stack = new ArrayList<WeightedPoint>();
//				stack.add(decreasedKey);
//				mheap.get(i).remove(j);
//				
//				//after the movement, if the stack is empty, added the new stack on this location again, 
//				//bubbleUp the tree from the location
//				//since once the f value changed, it always changes to smaller one
//				if(mheap.get(i).isEmpty()) {
//						mheap.set(i, stack);
////						mheap.set(i, mheap.get(mheap.size()-1));
//						System.out.println("size of the heap: " + mheap.size());
////						mheap.remove(mheap.size()-1);
//						System.out.println(mheap.size());
//						bubbleUp(i);
//			
//				}else {
//					mheap.add(stack);
//					bubbleUp(mheap.size()-1);
//				}
//				
//			}
		}
			
	}
	
	@Override
	public void insert(WeightedPoint node) {
		// TODO Auto-generated method stub
		//check whether the value of the add element is on the heap
		Integer index = null;
		for(int i=0; i<mheap.size(); i++){
			countComparison ++;
			if(node.getTotalCost() == mheap.get(i).get(0).getTotalCost()){
				index = i;
				break;
			}
		}
		//if the value already exists, add the node in the stack
		if(index != null){
			ArrayList <WeightedPoint> stack = mheap.get(index);
			stack.add(node);
		}else{
			//when the add element is a new value on the heap
			//create a new stack node on the heap
			ArrayList<WeightedPoint> stack = new ArrayList<WeightedPoint>();
			stack.add(node);
			mheap.add(stack);
			bubbleUp(mheap.size()-1);
		}
	}

	@Override
	public WeightedPoint deleteMin() {
		// TODO Auto-generated method stub
		//delete the top element from the first stack
		//after the deletion, if the stack is not empty, leave it
		//if the stack is empty, then heapify the heap
		try{
			if(mheap.size() == 0)
				throw new Exception("Index out of range (multi-stack heap underflow)");
			ArrayList<WeightedPoint> stack = mheap.get(0);
			WeightedPoint delete = stack.remove(stack.size()-1);;
			if(stack.isEmpty()){
				mheap.set(0, mheap.get(mheap.size()-1));
				mheap.remove(mheap.size()-1);
				bubbleDown(0);
			}
			return delete;
		}catch (Exception ex){
			System.out.println(ex);
			return null;
		}
	}

	@Override
	public void decreaseKey(WeightedPoint node) {
		// TODO Auto-generated method stub
//		int flag = 0;
//		for(int i=0; i<mheap.size(); i++){
//			for(int j=0; j<mheap.get(i).size(); j++){
//				
//				if(node.equals(mheap.get(i).get(j))) {
//					bubbleUp(i, j);
//					flag = 1;
//					break;
//				}
//					
//			}
//			if(flag == 1) {
//				break;
//			}
//		}
		bubbleUp(containedIndex1, containedIndex2);
	}


	
	@Override
	public int stackLevel() {
		// TODO Auto-generated method stub
		return mheap.size();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		mheap = new ArrayList<ArrayList<WeightedPoint>>();
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Multi-Array";
	}

	@Override
	public List getList() {
		// TODO Auto-generated method stub
		ArrayList<WeightedPoint> output = new ArrayList<WeightedPoint>();
	    for (int i = 0; i < mheap.size(); i++) {
	    	for (int j=0; j<mheap.get(i).size(); j++) {
	    		output.add(mheap.get(i).get(j));
	    	}
	    }
	    return output; 
	}


	@Override
	public WeightedPoint getNode(int i) {
		// TODO Auto-generated method stub
		return mheap.get(i).get(0);
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
