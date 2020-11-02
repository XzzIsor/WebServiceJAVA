package com.edd.ws.rest.vo;

import java.util.ArrayList;

public class Nodo <T> {
	private T parent;
	private T element;
	private ArrayList<Nodo> sons = new ArrayList<>();
	
	public T getElement() {
		return element;
	}
	public void setElement(T element) {
		this.element = element;
	}

	public ArrayList<Nodo> getSons() {
		return sons;
	}
	public void setSons(ArrayList<Nodo> sons) {
		this.sons = sons;
	}
	public T getParent() {
		return parent;
	}
	public void setParent(T parent) {
		this.parent = parent;
	}
	
	public void addSon(T element) {
		Nodo<T> node = new Nodo<T>();
		node.setElement(element);
		sons.add(node);
		sort(sons, 0, sons.size()-1);
	}
	
	public void removeSons() {
		sons = new ArrayList<>();
	}
	
	public void removeSon(int index) {
		sons.remove(index);
	}
	
	public void removeSon(Nodo node) {
		for(int i = 0; i < sons.size(); i++) {
			if(sons.get(i).equals(node)) {
				sons.remove(i);
			}
		}
	}
	
	public Nodo findNode(T element) {
		for(int i = 0; i < sons.size(); i++) {
			if(sons.get(i).getElement().equals(element)) {
				return sons.get(i);
			}
		}
		return null;
	}
	
	/* low  --> Starting index,  high  --> Ending index */
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	int partition(ArrayList<Nodo> arr, int low, int high) 
	    { 
		 	//Comparable pivot = (Comparable) arr[high].getElement();  
	        int i = (low-1); // index of smaller element 
	        for (int j=low; j<high; j++) 
	        { 
	            // If current element is smaller than the pivot 
	        	
	        	Comparable arrJ = (Comparable) arr.get(j).getElement(); 
	            if (arrJ.compareTo(arr.get(high).getElement()) <= 0) 
	            { 
	                i++; 
	                // swap arr[i] and arr[j] 
	                Nodo temp = arr.get(i); 
	                arr.set(i,  arr.get(j));  
	                arr.set(j, temp);  
	            } 
	        } 
	  
	        // swap arr[i+1] and arr[high] (or pivot) 
	        Nodo temp = arr.get(i+1);
	        arr.set(i+1, arr.get(high));
	        arr.set(high, temp);
	        return i+1; 
	    } 
	  
	  
	    /* The main function that implements QuickSort() 
	      arr[] --> Array to be sorted, 
	      low  --> Starting index, 
	      high  --> Ending index */
	    void sort(ArrayList<Nodo> arr, int low, int high) 
	    { 
	        if (low < high) 
	        { 
	            /* pi is partitioning index, arr[pi] is  
	              now at right place */
	            int pi = partition(arr, low, high); 
	  
	            // Recursively sort elements before 
	            // partition and after partition 
	            sort(arr, low, pi-1); 
	            sort(arr, pi+1, high); 
	        } 
	    } 	    
	    
	    static void printArray(Nodo arr[]) 
	    { 
	        int n = arr.length; 
	        for (int i=0; i<n; ++i) 
	            System.out.print(arr[i].getElement().toString()+" "); 
	    }
		
	  
	
	
}
