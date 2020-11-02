package com.edd.ws.rest.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.SupportedValuesAttribute;
import javax.print.attribute.standard.JobStateReasons;
import javax.swing.event.DocumentEvent.ElementChange;

public class index {

	Archivo path;
	String type;
	Nodo root = new Nodo<>();
	
	public index() {
		root = null;
	}
	
	public index(Archivo path) {
		this.path = path;
		type = path.getType();
		initialize();
	}
	
	public Archivo getPath() {
		return path;
	}
	public void setPath(Archivo path) {
		this.path = path;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Nodo getRoot() {
		return root;
	}
	public void setRoot(Nodo root) {
		this.root = root;
	}
	
	private void initialize() {
		if (type.equals("1")){
			initializeWithString();
		}else {
			initializeWithInteger();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeWithInteger() {
		root.setElement(path.getNodes().get(0));
		root.setParent(null);
		for(int i = 1; i < path.getNodes().size(); i++) {
			String[] nodes = path.getNodes().get(i).split("-");
			
			try {
				Integer.parseInt(nodes[1]);
				fillSons(root, nodes[0], nodes[1]);
			} catch (Exception e) {
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initializeWithString() {
		root.setElement(path.getNodes().get(0));
		root.setParent(null);
		for(int i = 1; i < path.getNodes().size(); i++) {
			String[] nodes = path.getNodes().get(i).split("-");
			
			try {
				Integer.parseInt(nodes[1]);
			} catch (Exception e) {
				fillSons(root, nodes[0], nodes[1]);
			}
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void fillSons(Nodo aux, Comparable place, Comparable newValue) {
		if (aux == null) {
			return;
		}
		if (place.compareTo(aux.getElement()) == 0) {
			aux.addSon(newValue);
			Nodo son = aux.findNode(newValue);
			son.setParent(place);
		}
		if (!aux.getSons().isEmpty()) {
			ArrayList<Nodo> auxSons = aux.getSons();
			for (int j = 0; j < auxSons.size(); j++) {
				fillSons((Nodo) aux.getSons().get(j), place, newValue);
			}
		}

	}
	
	
	private void showListaNode (ArrayList<Nodo> list) {
		for (int i = 0; i < list.size(); i++) {
			Nodo node = (Nodo)list.get(i); 
			System.out.print(" [" + node.getElement() + "] ");
		}
	}
	
	public void pruneTree(int lvl ) {
		if(lvl == 0) {
			System.out.println("The action could not be performed, because it would eliminate the whole tree");
			return;
		}
		ArrayList<Nodo> aux = new ArrayList<>();
		ArrayList<Nodo> response = new ArrayList();
	    TreeLevel(0, lvl-1, root, response);
	    TreeLevel(0, lvl, root, aux);
	    
	    if (aux.isEmpty()) {
	    	System.out.println("You chose a lvl that is not in the tree");
	    	return; 
	    }
	    
	    for (int i = 0; i < response.size() ; i++) {
	    	response.get(i).removeSons();
	    }
	    showTree();
	    return;
	    
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public void traverseBranches () {
		
		Nodo copyRoot  = root;
		ArrayList<Nodo> response = new ArrayList(); 
		
		if(root.getSons().isEmpty()) {
			System.out.println("The tree don't have branches");
			return;
			
		}
		
		while (response.size() != 1) {
			response = new ArrayList(); 
			FirstWay(copyRoot,response );
			if (response.size() != 1) {
				showListaNode(response);
			}
			cleanLonelyBranch(response);
			System.out.println();
		}
		initialize();
	}
	
	private void cleanLonelyBranch(ArrayList<Nodo> Nodolist) {
		
		Nodo LonelyBranch = null; 
		int LonelyBranchIndex = -1; 

		for (int i = 0; i < Nodolist.size() ; i++) {
			Nodo actualNode = Nodolist.get(i);
			if ((actualNode.getSons().size() == 1 || actualNode.getSons().size()  == 0) && LonelyBranch == null) {
				LonelyBranch = actualNode; 
				LonelyBranchIndex = i; 
			}
			if (actualNode.getSons().size() > 1) {
				LonelyBranchIndex = -1; 
				LonelyBranch = null;
			}
		}
		try {
			Nodolist.get(LonelyBranchIndex-1).removeSon(LonelyBranch);
		} catch (Exception e) {
			root.removeSons();
		}
		
	}
	
	public void FirstWay (Nodo aux, ArrayList<Nodo> response) {
		if (aux == null) {		
			return;
		}
		response.add(aux);
		if (aux.getSons().isEmpty()) {
			return; 
		}
		FirstWay((Nodo)aux.getSons().get(0), response);
	}

	private void TreeLevel (int lvl, int searchlvl,  Nodo aux, ArrayList<Nodo> response) {
		if (aux == null) {
			return;
		}
		if (lvl == searchlvl) {
			response.add(aux);
		}
		
		if (!aux.getSons().isEmpty()) {
			for (int i = 0; i < aux.getSons().size() ; i ++) {
				TreeLevel(lvl+1, searchlvl, (Nodo)aux.getSons().get(i), response);
			}
		}
	}

	public void showNodesByLevel(int lvl) {
		ArrayList<Nodo> nodes = new ArrayList<>();
		TreeLevel(0, lvl, root, nodes);
		if(lvl == 0) {
			System.out.println("Level of the root and the node is: "+ root.getElement());
			return;
		}
		if(nodes.isEmpty()) {
			System.out.println("The level doesn't exists in the tree");
			return;
		}
		
		System.out.println("showing lvl = " + lvl);
		System.out.println("amount = " + nodes.size());
		showListaNode(nodes);
		System.out.println();
	}
	
	//this shows the tree by width
	public void showTree() {
		int counter = 0;
		ArrayList<Nodo> lvls =new ArrayList(); 
		TreeLevel(0, counter,  root, lvls );
		showListaNode(lvls);
		
		while (!lvls.isEmpty()) {
			lvls =new ArrayList(); 
			counter = counter + 1;
			TreeLevel(0, counter,  root, lvls );
			showListaNode(lvls);
		}
		System.out.println("\n");

	}
	
	private void showListString(ArrayList<String> list) {
		for(int i = 0; i < list.size(); i++) {
			System.out.print("["+list.get(i) + "] ");
		}
		System.out.println("\n");
	}
	
	public void showSons(Comparable element){
		ArrayList<String> info = new ArrayList<>();
		showSons(root, element, info);
		if(info.isEmpty()) {
			System.out.println("The node "+ element+" hasn't sons or doesn't exists");
		}else {
			System.out.print("The node "+ element +" has "+ info.size() + " sons and they are: ");
			showListString(info);
		}
	}
	
	private void showSons(Nodo aux, Comparable element, ArrayList<String> sonsString) {
		ArrayList<Nodo> sons = new ArrayList<>();
		if(aux == null) 
			return;
				
		for (int j = 0; j < aux.getSons().size(); j++) {
			if(element.compareTo(aux.getElement().toString()) == 0) {
				sons = aux.getSons();
				for(int i = 0; i < sons.size(); i++) {
					sonsString.add(sons.get(i).getElement().toString());
				}
				return;
			}
			showSons((Nodo) aux.getSons().get(j), element, sonsString);
		}
	}
	
	public void showLeaves() {
		ArrayList<String> leafs = new ArrayList<>();
		showLeaves(root, leafs);
		
		if(leafs.isEmpty()) {
			System.out.println("Te tree doesn't exists");
		}else {
			System.out.print("The tree's leaves are: ");
			showListString(leafs);
		}
		
	} 
	
	private void showLeaves(Nodo aux, ArrayList<String> leafs) {
		if(aux == null) 
			return;
		
		
		if(aux.getSons().isEmpty()) {
			leafs.add(aux.getElement().toString());
		}
		
		for(int i = 0; i < aux.getSons().size(); i++) {
			showLeaves((Nodo) aux.getSons().get(i), leafs);
		}
		
	}
	
	public void showSiblings(Comparable element) {
		ArrayList<String> siblings = new ArrayList<String>();
		showSiblings(root, null, element, siblings);
		
		if(element == root.getElement()) {
			System.out.println("The element is the root. Doesn't has siblings");
			return;
		}
		if(siblings.isEmpty()) {
			System.out.println("The node " +element+ " hasn't sons or doesn't exists");
		}else {
			System.out.print("The node "+ element + " has " + siblings.size() +" siblings and they are: ");
			showListString(siblings);
		}
		
		
	}
	
	private void showSiblings(Nodo aux, Nodo father, Comparable element, ArrayList<String> siblings) {
		ArrayList<Nodo> list = new ArrayList<>();
		if(aux == null) 
			return;
		
		if(element.compareTo(root.getElement().toString()) == 0) {
			return;
		}

		for(int j = 0; j < aux.getSons().size()+1; j++) {	
			if(element.compareTo(aux.getElement().toString()) == 0) {
				list = father.getSons();
				for(int i = 0; i < list.size(); i++) {
					if(!list.get(i).getElement().toString().equals(element)) {
						siblings.add(list.get(i).getElement().toString());
					}
				}
				return;
			}
			showSiblings((Nodo) aux.getSons().get(j), aux, element, siblings);
		}

	}
	
	
	

}
