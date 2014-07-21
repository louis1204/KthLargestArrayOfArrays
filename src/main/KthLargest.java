package main;

import java.util.ArrayList;

public class KthLargest {
	
	int findKthLargest(int k, ArrayList<ArrayList<Integer>> list)
	{
		ArrayList<Integer> merged = this.mergeSort(list);
		return merged.get(merged.size()-k);
	}
	
	private ArrayList<Integer> mergeSort(ArrayList<ArrayList<Integer>> list)
	{
		
		// Base Case: when list size only has one list, return it
		if (list.size() == 1) {
			return list.get(0);
		}

		// Recursive case: when the list size > 1 continually break down the list
		// merge the lists and return it
		int middle = list.size() / 2;
		
		// Divide and conquer
		ArrayList<ArrayList<Integer>> lowList = new ArrayList<ArrayList<Integer>>(list.subList(0, middle));
		ArrayList<ArrayList<Integer>> highList = new ArrayList<ArrayList<Integer>>(list.subList(middle, list.size()));

		ArrayList<Integer> mergedLowList = mergeSort(lowList);
		ArrayList<Integer> mergedHighList = mergeSort(highList);

		// Starting from the base case, merge the lists together
		return merge(mergedLowList, mergedHighList);
	}
	
	private ArrayList<Integer> merge(ArrayList<Integer> list1, ArrayList<Integer> list2)
	{
		ArrayList<Integer> merged = new ArrayList<Integer>();
		
		int j = 0;
		int i = 0; 
		
		while(i < list1.size())
		{
			// We added all of list 2 so just add list 1
			if(j == list2.size())
			{
				merged.add(list1.get(i));
				i++;
			}
			// Add the lower one
			else if(list1.get(i) <= list2.get(j))
			{
				merged.add(list1.get(i));
				i++;
			}
			else
			{
				merged.add(list2.get(j));
				j++;
			}
		}
		
		// We added all of list 1 so just add list 2
		while(j < list2.size())
		{
			merged.add(list2.get(j));
			j++;
		}
		
		return merged;
	}
	
	public static void main(String[] arg)
	{
		ArrayList<Integer> array1 = new ArrayList<Integer>();
		array1.add(1);
		array1.add(3);
		array1.add(5);
		
		ArrayList<Integer> array2 = new ArrayList<Integer>();
		array2.add(2);
		array2.add(4);
		array2.add(6);
		
		ArrayList<ArrayList<Integer>> arrays = new ArrayList<ArrayList<Integer>>();
		arrays.add(array1);
		arrays.add(array2);
		
		KthLargest main = new KthLargest();
		System.out.println(main.findKthLargest(1, arrays));
	}
}
