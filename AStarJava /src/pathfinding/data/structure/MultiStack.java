package pathfinding.data.structure;

import java.util.ArrayList;
import java.util.List;

import pathfinding.map.WeightedPoint;

public class MultiStack extends StructureSelector<Object>{
	
	public static void main(String []args) {
		MultiStack mStack = new MultiStack();
		WeightedPoint node1 = new WeightedPoint(1,1);
		WeightedPoint node2 = new WeightedPoint(2,2);
		WeightedPoint node3 = new WeightedPoint(5,6);
		WeightedPoint node4 = new WeightedPoint(7,2);
		WeightedPoint node5 = new WeightedPoint(3,1);
		WeightedPoint node6 = new WeightedPoint(4,2);
//		WeightedPoint node7 = new WeightedPoint(4,2);
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
		node5.sethCost(3);
		
		node6.setgCost(2);
		node6.sethCost(5);
		
//		node7.setgCost(3);
//		node7.sethCost(4);
		
		mStack.insert(node1);
		mStack.insert(node2);
		mStack.insert(node3);
		mStack.insert(node4);
		mStack.insert(node5);
		mStack.insert(node6);
//		mStack.insert(node7);
	

		
		System.out.println(mStack.contains(node5));
		System.out.println(mStack.containedNode(node5));
//		System.out.println(mStack.getStack(0) + "  value:" + mStack.getStack(0).get(0).getTotalCost());
//		System.out.println(mStack.getStack(1).isEmpty());
//		System.out.println(mStack.getStack(1) + "  value:" + mStack.getStack(1).get(0).getTotalCost());

		
	}

	private ArrayList<WeightedPoint> mStack0;
	private ArrayList<WeightedPoint> mStack1;
	private int flag;
	private WeightedPoint getContained;
	private int containedIndex1, containedIndex2;
	
	private ArrayList<ArrayList<WeightedPoint>> mStack;
	
	public MultiStack() {
		
		mStack0 = new ArrayList<WeightedPoint>();
		mStack1 = new ArrayList<WeightedPoint>();
		mStack = new ArrayList<ArrayList<WeightedPoint>>();
		mStack.add(mStack0);
		mStack.add(mStack1);
	}
	
	public ArrayList<WeightedPoint> getStack(int i){
		return mStack.get(i);
	}
	
	public void getPriority() {
		
		if(mStack0.isEmpty() && !mStack1.isEmpty())
			flag = 1;
		if(!mStack0.isEmpty() && mStack1.isEmpty())
			flag = 0;
		if(mStack1.get(0).getTotalCost() < mStack0.get(0).getTotalCost()) {
			flag = 1;
		}else {
			flag = 0;
		}
	}
	
	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	public int getFlag() {
		return flag;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mStack0.size() + mStack1.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return mStack0.isEmpty() && mStack1.isEmpty();
	}

	@Override
	public boolean contains(WeightedPoint node) {
		// TODO Auto-generated method stub
		if(!mStack.get(flag).isEmpty() && node.getTotalCost() == mStack.get(flag).get(0).getTotalCost()) {
			for(int i=0; i<mStack.get(flag).size(); i++) {
				if(node.equals(mStack.get(flag).get(i))) {
					getContained = mStack.get(flag).get(i);
					containedIndex1 = flag;
					containedIndex2 = i; 
					return true;
				}
			}
		}
		if(!mStack.get(1-flag).isEmpty() && node.getTotalCost() == mStack.get(1-flag).get(0).getTotalCost()) {
			for(int i=0; i<mStack.get(1-flag).size(); i++) {
				if(node.equals(mStack.get(1-flag).get(i))) {
					getContained = mStack.get(1-flag).get(i);
					containedIndex1 = 1 - flag;
					containedIndex2 = i; 
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public WeightedPoint containedNode(WeightedPoint node) {
		// TODO Auto-generated method stub
		return getContained;
	}

	@Override
	public void insert(WeightedPoint node) {
		// TODO Auto-generated method stub

		if(mStack0.isEmpty() && mStack1.isEmpty()) {
			//when the multi-stack heap is empty
			mStack.get(0).add(node);
			setFlag(0);
		}else if(node.getTotalCost() == mStack.get(flag).get(0).getTotalCost() ) {
			//when the node's f value is same with the prioritized one
			mStack.get(flag).add(node);
		} else{
			//when the node's f value is not same with the flaged one, add it at another stack if it is not empty
			//otherwise, we have to compare their f value
			//if node's f value is smaller, the flag should be set at the nodes's stack
			//else, keep it
			mStack.get(1-flag).add(node);
			if(!mStack.get(1-flag).isEmpty() && mStack.get(1-flag).get(0).getTotalCost() != node.getTotalCost()) {
				System.out.println("Warnning! Warning! The Multi-Stack is exploded!");
			} 
			if(mStack.get(1-flag).size() == 1) {
				if(node.getTotalCost() < mStack.get(flag).get(0).getTotalCost()){
					setFlag(1-flag);
				}
			}
		}
		
	}

	@Override
	public WeightedPoint deleteMin() {
		// TODO Auto-generated method stub
		try{
			if(mStack.isEmpty())
				throw new Exception("Index out of range (multi-stack)");
			ArrayList<WeightedPoint> topStack = mStack.get(flag);
			//delete the last node with the flagged stack as its f value is smaller
			WeightedPoint deleted = topStack.remove(topStack.size()-1);
			//after the deletion, if the stack is empty, set another stack as the flag if it is not empty
			if(topStack.isEmpty() && !mStack.get(1-flag).isEmpty()) {
				setFlag(1-flag);
			}
			return deleted;
		}catch(Exception ex) {
			System.out.println(ex);
			return null;
		}
	}


	@Override
	public void decreaseKey(WeightedPoint node) {
		// TODO Auto-generated method stub
		//when the decrease key happens, the value must be smaller, and there are always at most 2 stacks 
		if(containedIndex1 != flag) {
			// it has to be decrease in the flagged stack
			mStack.get(flag).add(node);
		}else {
			//only when another stack is empty, after the decreasing, reset that stack as flag
			mStack.get(1-flag).add(node);
			setFlag(1-flag);
		}
		mStack.get(containedIndex1).remove(containedIndex2);
	}


	@Override
	public int stackLevel() {
		// TODO Auto-generated method stub
		return mStack.size();
	}

	@Override
	public WeightedPoint getNode(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		mStack = new ArrayList<ArrayList<WeightedPoint>>();
		mStack0 = new ArrayList<WeightedPoint>();
		mStack1 = new ArrayList<WeightedPoint>();
		mStack.add(mStack0);
		mStack.add(mStack1);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Multi-Stack";
	}

	@Override
	public List<WeightedPoint> getList() {
		// TODO Auto-generated method stub
		ArrayList<WeightedPoint> output = new ArrayList<WeightedPoint>();
	    for (int i = 0; i < mStack.size(); i++) {
	    	for (int j=0; j<mStack.get(i).size(); j++) {
	    		output.add(mStack.get(i).get(j));
	    	}
	    }
	    return output; 
	}

	@Override
	public int getComparisonNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSwapNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getContainsNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setComparisonNum(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSwapNum(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setContainsNum(int num) {
		// TODO Auto-generated method stub
		
	}

}
