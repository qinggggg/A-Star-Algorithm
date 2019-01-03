package pathfinding.data.structure;

import java.util.ArrayList;
import java.util.List;

import pathfinding.map.WeightedPoint;


public class UnsortedLIFO<T> extends StructureSelector<Object> {
	public static void main(String args[]){
		
		//test the UnsortedArray
		WeightedPoint node1 = new WeightedPoint(1,1);
		WeightedPoint node2 = new WeightedPoint(2,2);
		WeightedPoint node3 = new WeightedPoint(5,6);
		WeightedPoint node4 = new WeightedPoint(7,2);
		
		node1.setgCost(8);
		node1.sethCost(10);
		
		node2.setgCost(1);
		node2.sethCost(6);
		
		node3.setgCost(1);
		node3.sethCost(1);
		
		node4.setgCost(3);
		node4.sethCost(5);
		
		UnsortedLIFO<WeightedPoint> array = new UnsortedLIFO<WeightedPoint>();
		array.insert(node1);
		array.insert(node2);
		array.insert(node3);
		array.insert(node4);
		
		array.deleteMin();


		
//		array.insert(node2);
	}

	private ArrayList<WeightedPoint> unsArray;
	private int countComparison;
	private int countContains;
	private int countSwap;
	
	public UnsortedLIFO(){
		unsArray = new ArrayList<WeightedPoint>();
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return unsArray.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return unsArray.isEmpty();
	}

	@Override
	public boolean contains(WeightedPoint node) {
		// TODO Auto-generated method stub
		if (node == null)
        {
            for (int i = 0; i < unsArray.size(); i++)
                if (unsArray.get(i) == null)
                    return true;
        }
        else
        {
            for (int i = 0; i < unsArray.size(); i++) {
            	countContains ++;
            	if (node.equals(unsArray.get(i)))
                    return true;
            }
                
        }
        return false;
	}

	@Override
	public WeightedPoint containedNode(WeightedPoint node) {
		// TODO Auto-generated method stub
		for (int i = 0; i < unsArray.size(); i++)
            if (node.equals(unsArray.get(i)))
                return unsArray.get(i);
		return null;
	}
	
	

	@Override
	public void decreaseKey(WeightedPoint node) {
		// TODO Auto-generated method stub
//		for (int i = 0; i < unsArray.size(); i++)
//            if (node.equals(unsArray.get(i))) {
//            	unsArray.remove(i);
//            	break;
//            }
	}


	//insert an element
	@Override
	public void insert(WeightedPoint node) {
		// TODO Auto-generated method stub
		unsArray.add(node);
	}

	 //delete the minimum element
	@Override
	public WeightedPoint deleteMin() {
		try{
			if(unsArray.isEmpty())
				throw new Exception("Index out of range (unsorted array underflow)");			
			// TODO Auto-generated method stub
			WeightedPoint node = unsArray.get(0);
			int minIndex = 0;
			for(int i=unsArray.size()-1; i>=0; i--){
				countComparison ++;
				if(unsArray.get(i).getTotalCost() < node.getTotalCost()){
					minIndex = i;
					node = unsArray.get(i);
				}
			}
			WeightedPoint delete = unsArray.remove(minIndex);
			return delete;
		}catch (Exception ex){
			System.out.println(ex);
			return null;

		}
		
	}



	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return "Unsorted FILO";
	}

	
//	protected WeightedPoint get(int i) {
//		// TODO Auto-generated method stub
//		if (i < unsArray.size())
//            return unsArray.get(i);
//        return null;
//	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		unsArray = new ArrayList<WeightedPoint>();
	}

	@Override
	public List<WeightedPoint> getList() {
		// TODO Auto-generated method stub
		List<WeightedPoint> output = new ArrayList<WeightedPoint>();
	    for (int i = 0; i < size(); i++)
	    	output.add(unsArray.get(i));
	    return output; 
	}

	@Override
	public int stackLevel() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public WeightedPoint getNode(int i) {
		// TODO Auto-generated method stub
		return unsArray.get(i);
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
