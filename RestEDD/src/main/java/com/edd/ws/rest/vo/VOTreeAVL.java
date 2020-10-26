package com.edd.ws.rest.vo;

import java.util.ArrayList;

public class VOTreeAVL {
	private VONodo raiz;
	private int high;

	public VOTreeAVL() {
		this.raiz = null;
	}

	public int getHigh() {
		return high;
	}
	
	public VONodo getRaiz() {
		return raiz;
	}

	public void insertar(Comparable elemento) {
		raiz = insertar(raiz, null, elemento);
	}

	public static int factorEquilibrio(VONodo arbol) {
		if (arbol == null)
			return 0;
		else
			return (arbol.getFactorEquilibrio() + 1);
	}

	private static int mFE(int alturaIzquierdo, int alturaDerecho) {
		if (alturaIzquierdo > alturaDerecho)
			return alturaIzquierdo;
		else
			return alturaDerecho;
	}

	private VONodo insertar(VONodo arbol, VONodo parent, Comparable elemento) {
		if (arbol == null) {
				arbol = new VONodo(elemento, null, null);
				
		} else if (elemento.compareTo(arbol.getElemento()) < 0) {
			arbol.setIzq(insertar(arbol.getIzq(), arbol, elemento));
			if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == -2) {
				if (elemento.compareTo(arbol.getIzq().getElemento()) < 0)
					arbol = RotacionSimpleDer(arbol);
				else
					arbol = RotacionDobleIzq_Der(arbol);
			}
		} else if (elemento.compareTo(arbol.getElemento()) >= 0) {
			arbol.setDer(insertar(arbol.getDer(), arbol, elemento));
			if (factorEquilibrio(arbol.getDer()) - factorEquilibrio(arbol.getIzq()) == 2) {
				if (elemento.compareTo(arbol.getDer().getElemento()) >= 0)
					arbol = RotacionSimpleIzq(arbol);
				else
					arbol = RotacionDobleDer_Izq(arbol);
			}
		}
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		return arbol;
	}

	private static VONodo RotacionSimpleDer(VONodo arbol) {
		VONodo subArbol = arbol.getIzq();
		arbol.setIzq(subArbol.getDer());
		subArbol.setDer(arbol);
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		subArbol.setFactorEquilibrio(mFE(factorEquilibrio(subArbol.getIzq()), arbol.getFactorEquilibrio()));
		return subArbol;
	}

	private static VONodo RotacionSimpleIzq(VONodo arbol) {
		VONodo subArbol = arbol.getDer();
		arbol.setDer(subArbol.getIzq());
		subArbol.setIzq(arbol);
		arbol.setFactorEquilibrio(mFE(factorEquilibrio(arbol.getIzq()), factorEquilibrio(arbol.getDer())));
		subArbol.setFactorEquilibrio(mFE(factorEquilibrio(subArbol.getDer()), arbol.getFactorEquilibrio()));
		return subArbol;
	}

	private static VONodo RotacionDobleIzq_Der(VONodo arbol) {
		arbol.setIzq(RotacionSimpleIzq(arbol.getIzq()));
		return RotacionSimpleDer(arbol);
	}

	private static VONodo RotacionDobleDer_Izq(VONodo subArbol) {
		subArbol.setDer(RotacionSimpleDer(subArbol.getDer()));
		return RotacionSimpleIzq(subArbol);
	}

	public void imprimirArbol() {
		imprimirArbol(raiz, 0);
	}

	private void imprimirArbol(VONodo aux, int nivel) {
		if (aux != null) {
			imprimirArbol(aux.getIzq(), nivel + 1);
			System.out.println(nivel + " \t" + aux.getElemento());
			imprimirArbol(aux.getDer(), nivel + 1);
		}
	}
	
	
	public void printTree () {
		 ArrayList<String> hola =  new ArrayList<>(); 
		 getTreeByLvl(raiz, 0, hola);
		 printArray(hola);
	}
	
	
	
	public ArrayList<String> getTreeArray () {
		 ArrayList<String> tree =  new ArrayList<>(); 
		 getTreeByLvl(raiz, 0, tree);
		 return tree;
		 
	}
	
	private void getTreeByLvl (VONodo aux, int lvl, ArrayList<String> response) {
		if (aux!= null) {
			try{
				response.set(lvl , aux.getElemento().toString()+" "+ response.get(lvl)); 
			}catch(Exception e) {
				response.add(aux.getElemento().toString()); 
			}
			getTreeByLvl(aux.getDer(), lvl+1, response);
			getTreeByLvl(aux.getIzq(), lvl+1, response);
		}
	}

	public void printArray (ArrayList<String> Tree ) {
		for (int i = 0; i < Tree.size() ; i++) {
			System.out.println("lvl = "  + (i+1) +"  values = "  +  Tree.get(i));
		}
	}
	
	public void assingCodes() {
		assingCodes(raiz, null);
	}
	
	private void assingCodes(VONodo aux, VONodo parent) {
		if(aux == null) {
			return;
		}
		if(parent == null) {
			aux.setCod((int) (Math.random()*1000 + 100));
			aux.setCodParent(0);
			assingCodes(aux.getIzq(), aux);
			assingCodes(aux.getDer(), aux);
		}else {
			aux.setCod((int) (Math.random()*1000 + 100));
			aux.setCodParent(parent.getCod());
			assingCodes(aux.getIzq(), aux);
			assingCodes(aux.getDer(), aux);
		}

	}
	
	public void assingLevels() {
		assingLevels(raiz, 0);
	}
	
	private void assingLevels(VONodo aux, int lvl) {
		if(aux == null) {
			high = lvl + 1;
			return;
		}
		aux.setLevel(lvl);
		lvl = lvl + 1;
		assingLevels(aux.getDer(), lvl);
		assingLevels(aux.getIzq(), lvl);
		
	}
}
