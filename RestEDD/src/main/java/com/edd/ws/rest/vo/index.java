package com.edd.ws.rest.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.SupportedValuesAttribute;
import javax.print.attribute.standard.JobStateReasons;
import javax.swing.event.DocumentEvent.ElementChange;

public class index {

	Archivo path = new Archivo();
	String type = "";
	Nodo root = new Nodo<>();
	
	public index() {
	}
	
	public index(Archivo path) {
		this.path = path;
		type = path.getType();
		initialize();
	}
	
	public index(String path) {
		Fichero file = new Fichero(path);
		Archivo archive = new Archivo();
		archive.setType(file.getText().get(0));
		for(int i = 1; i < file.getText().size(); i++) {
			archive.getNodes().add(file.getText().get(i));
		}
		this.path = archive;
		type = archive.getType();
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
		if (type.equals("0")){
			initializeWithString();
		}else{
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
	
	public void pruneTree(String lvl ) {
		int lvlAux = Integer.parseInt(lvl);
		if(lvlAux == 0) {
			return;
		}
		ArrayList<Nodo> aux = new ArrayList<>();
		ArrayList<Nodo> response = new ArrayList();
	    TreeLevel(0, lvlAux-1, root, response);
	    TreeLevel(0, lvlAux, root, aux);
	    
	    if (aux.isEmpty()) {
	    	return; 
	    }
	    
	    for (int i = 0; i < response.size() ; i++) {
	    	response.get(i).removeSons();
	    }
	    
	    return;
	    
	}	
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	public String traverseBranches () {
		String responseString ="";
		Nodo copyRoot  = root;
		ArrayList<Nodo> response = new ArrayList(); 
		
		if(root.getSons().isEmpty()) {
			return responseString;
		}
		
		while (response.size() != 1) {
			response = new ArrayList(); 
			FirstWay(copyRoot,response );
			if (response.size() != 1) {
				for(int i = 0; i < response.size(); i++) {
					responseString = responseString + "[ "+ response.get(i).getElement().toString() +" ] ";
				}
				responseString = responseString + "\n";
			}
			cleanLonelyBranch(response);
		}
		return responseString;
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

	public String showNodesByLevel(String lvl) {
		ArrayList<Nodo> nodes = new ArrayList<>();
		String response = "";
		int lvlAux = Integer.parseInt(lvl);
		TreeLevel(0, lvlAux, root, nodes);
		
		if(nodes.isEmpty()) {
			response = "That level doesn't exists in the tree";
			return response;
		}
		for(int i = 0; i < nodes.size(); i++) {
			response = response + "[ "+ nodes.get(i).getElement().toString()+ "] ";
		}
		return response;
		
	}
	
	//this shows the tree by width
	public String showTree() {
		int counter = 0;
		String response = "";
		ArrayList<Nodo> lvls =new ArrayList(); 
		TreeLevel(0, counter,  root, lvls );
		for(int i = 0; i < lvls.size(); i++) {
			response = response + "[ "+ lvls.get(i).getElement().toString()+ "] ";
		}
		
		
		while (!lvls.isEmpty()) {
			lvls =new ArrayList(); 
			counter = counter + 1;
			TreeLevel(0, counter,  root, lvls );
			for(int i = 0; i < lvls.size(); i++) {
				response = response + "[ "+ lvls.get(i).getElement().toString()+ "] ";
			}
		}
		
		return response;
	}
	
	
	public String showSons(Comparable element){
		ArrayList<String> info = new ArrayList<>();
		String response = "";
		showSons(root, element, info);
		if(info.size() != 0) {
			for(int i = 0; i < info.size(); i++) {
				response = response + "[ "+info.get(i) +" ] ";
			}
			return response;
		}
		response = "The node " + element + " hasn't sons or doesn't exists";
		return response;

	}
	
	private void showSons(Nodo aux, Comparable element, ArrayList<String> sonsString) {
		ArrayList<Nodo> sons = new ArrayList<>();
		if(aux == null) 
			return;
				
		for (int j = 0; j < aux.getSons().size(); j++) {
			if(aux.getElement().toString().equals(element.toString())) {
				sons = aux.getSons();
				for(int i = 0; i < sons.size(); i++) {
					sonsString.add(sons.get(i).getElement().toString());
				}
				return;
			}
			showSons((Nodo) aux.getSons().get(j), element, sonsString);
		}
	}
	
	public String showLeaves() {
		String response = "";
		ArrayList<String> leaves = new ArrayList<>();
		showLeaves(root, leaves);
		
		if(leaves.isEmpty()) {
			response = "The tree doesn't exists";
			return response;
		}
		for(String data : leaves) {
			response = response + "[ "+ data +" ] ";
		}
		return response;
		
		
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
	
	public String showSiblings(Comparable element) {
			String response = "";
			ArrayList<String> siblings = new ArrayList<>();
			showSiblings(root, null, element, siblings );
			if(siblings.isEmpty()) {
				response = "The node hasn't siblings or doesn't exists";
				return response;
			}
			for(String data : siblings) {
				response = response + "[ " + data + " ] ";
			}
			return response;
		
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
