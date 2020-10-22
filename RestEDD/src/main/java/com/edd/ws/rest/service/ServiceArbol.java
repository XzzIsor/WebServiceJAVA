package com.edd.ws.rest.service;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import com.edd.ws.rest.vo.VONodo;

@Path("/tree")
public class ServiceArbol {
	
	private VONodo raiz;

	public ServiceArbol() {
	}

	public VONodo getRaiz() {
		return raiz;
	}

	public void setRaiz(VONodo raiz) {
		this.raiz = raiz;
	}

	
	@POST
	@Path("/insert")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public void insertar(Comparable elemento) {
		if (raiz == null)
			raiz = new VONodo(elemento);
		else
			raiz.insertarNodo(raiz, elemento, raiz, false);
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

	public void preorden() {
		System.out.print("Preorden:");
		preorden(raiz);
		System.out.println();
	}

	private void preorden(VONodo aux) {
		if (aux != null) {
			System.out.print(aux.getElemento() + ", ");
			preorden(aux.getIzq());
			preorden(aux.getDer());
		}
	}

	public void inorden() {
		System.out.print("Inorden:");
		inorden(raiz);
		System.out.println();
	}

	private void inorden(VONodo aux) {
		if (aux != null) {
			inorden(aux.getIzq());
			System.out.print(aux.getElemento() + ", ");
			inorden(aux.getDer());
		}
	}

	public void postorden() {
		System.out.print("Postorden:");
		postorden(raiz);
		System.out.println();
	}

	private void postorden(VONodo aux) {
		if (aux != null) {
			postorden(aux.getIzq());
			postorden(aux.getDer());
			System.out.print(aux.getElemento() + ", ");
		}
	}

	public void recorridos() {
		preorden();
		inorden();
		postorden();
	}

	public int numeroNodos() {
		return numeroNodos(raiz);
	}

	private int numeroNodos(VONodo aux) {
		if (aux == null)
			return 0;
		else
			return 1 + numeroNodos(aux.getIzq()) + numeroNodos(aux.getDer());

	}

	public void mayorElemento() {
		System.out.println("\n Mayor elemento " + mayorElemento(raiz).getElemento());
	}

	private VONodo mayorElemento(VONodo aux) {
		if (aux.getDer() != null) {
			return mayorElemento(aux.getDer());
		}
		return aux;
	}

	public void menorElemento() {
		System.out.println("\n Menor elemento " + menorElemento(raiz).getElemento());
	}

	private VONodo menorElemento(VONodo aux) {
		if (aux.getIzq() != null) {
			return menorElemento(aux.getIzq());
		}
		return aux;
	}

	public void nodosNivel(int n) {

		nodosNivel(raiz, n, 0);
	}

	private void nodosNivel(VONodo aux, int n, int i) {
		if (aux != null) {
			if (n == i) {
				System.out.println(aux.getElemento() + "  ");
			}
			nodosNivel(aux.getIzq(), n, i + 1);
			nodosNivel(aux.getDer(), n, i + 1);
		}
	}

	public int altura() {
		return altura(raiz);
	}

	public int altura(VONodo aux) {
		if (aux == null)
			return 0;
		else {
			int pIzq = altura(aux.getIzq());
			int pDer = altura(aux.getDer());
			return pIzq > pDer ? pIzq + 1 : pDer + 1;
		}
	}

	public int alturav2() {
		return alturav2(raiz);
	}

	private int alturav2(VONodo aux) {
		if (aux == null)
			return 0;
		return 1 + Math.max(altura(aux.getIzq()), altura(aux.getDer()));
	}

	public void mostrarArbol() {
		ArrayList<String> filas = new ArrayList<String>();

		if (raiz != null) {
			mostrarArbol(raiz, 0, filas);
		}

		String Spaces = "";

		for (int i = 0; i < filas.size(); i++)
			Spaces += "  ";

		for (String si : filas) {
			for (int i = 0; i < si.length(); i++) {
				System.out.print(Spaces + si.charAt(i) + Spaces + " ");
				Spaces = Spaces.substring(Spaces.length() / 2, Spaces.length());
			}
			System.out.println("");
		}
	}

	private void mostrarArbol(VONodo aux, int nivel, ArrayList<String> Filas) {

		try {
			Filas.get(nivel);
		} catch (Exception e) {
			Filas.add("");
		}

		if (aux != null) {

			Filas.set(nivel, Filas.get(nivel) + aux.getElemento());
			mostrarArbol(aux.getIzq(), nivel + 1, Filas);
			mostrarArbol(aux.getDer(), nivel + 1, Filas);
		}

	}

	@POST
	@Path("/nodepath")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public ArrayList<VONodo> NodePath(Comparable nodoValue, Comparable nodoValue2) {

		if (nodoValue == nodoValue2)
			return null;

		VONodo mainNodo = ComunPath(raiz, nodoValue, nodoValue2);
		ArrayList<VONodo> path1 = new ArrayList<VONodo>();
		ArrayList<VONodo> path2 = new ArrayList<VONodo>();
		if (!individualPath(path1, mainNodo, nodoValue)) {
			System.out.println("El dato " + nodoValue + "  no se encontró :(");
			return null;
		}

		if (!individualPath(path2, mainNodo, nodoValue2)) {
			System.out.println("El dato " + nodoValue2 + "  no se encontró :(");
			return null;
		}
		return nodoValue.compareTo(nodoValue2) < 0 ? concat(path1, path2) : concat(path2, path1);
	}

	private VONodo ComunPath(VONodo aux, Comparable NodoValue, Comparable NodoValue2) {

		if (NodoValue.compareTo(aux.getElemento()) == 0 || NodoValue2.compareTo(aux.getElemento()) == 0)
			return aux;

		if (NodoValue.compareTo(aux.getElemento()) > 0 && NodoValue2.compareTo(aux.getElemento()) > 0) {
			return ComunPath(aux.getDer(), NodoValue, NodoValue2);

		}
		if (NodoValue.compareTo(aux.getElemento()) < 0 && NodoValue2.compareTo(aux.getElemento()) < 0)
			return ComunPath(aux.getIzq(), NodoValue, NodoValue2);

		return aux;
	}

	private boolean individualPath(ArrayList<VONodo> path, VONodo aux, Comparable NodoValue) {
		int compareResult = NodoValue.compareTo(aux.getElemento());
		path.add(aux);
		if (compareResult == 0) {
			return true;
		}
		if (compareResult < 0) {
			if (!DoesItisNull(aux.getIzq()))
				return individualPath(path, aux.getIzq(), NodoValue);
			else
				return false;
		}
		if (compareResult > 0) {
			if (!DoesItisNull(aux.getDer()))
				return individualPath(path, aux.getDer(), NodoValue);
			else
				return false;
		}

		return false;
	}

	private ArrayList<VONodo> concat(ArrayList<VONodo> path1, ArrayList<VONodo> path2) {
		ArrayList<VONodo> TotalPath = new ArrayList<>();
		for (int i = path1.size() - 1; i > 0; i--)
			TotalPath.add(path1.get(i));
		for (int i = 0; i < path2.size(); i++)
			TotalPath.add(path2.get(i));
		return TotalPath;
	}

	private boolean DoesItisNull(VONodo aux) {
		if (aux == null)
			return true;
		return false;
	}
	
	public String showPath(ArrayList<VONodo> path) {
		String stringPath = "";
		for(VONodo node: path) {
			stringPath += node.getElemento();
		}
		return stringPath;
				
	}
}
