package com.edd.ws.rest.vo;

public class VONodo<T> {
	private T elemento;
	private VONodo izq;
	private VONodo der;

	public VONodo(T elemento) {
		this.elemento = elemento;
		izq = der = null;
	}

	public T getElemento() {
		return elemento;
	}

	public void setElemento(T elemento) {
		this.elemento = elemento;
	}

	public VONodo getIzq() {
		return izq;
	}

	public void setIzq(VONodo izq) {
		this.izq = izq;
	}

	public VONodo getDer() {
		return der;
	}

	public void setDer(VONodo der) {
		this.der = der;
	}

	public void insertarNodo(VONodo aux, Comparable elemento, VONodo anterior, boolean derecha) {
		if (aux == null) {
			aux = new VONodo(elemento);
			if(derecha) {
				anterior.setDer(aux);
			}else {
				anterior.setIzq(aux);
			}
			return; 
		}
		else if (elemento.compareTo(aux.getElemento()) < 0)
			insertarNodo(aux.getIzq(), elemento, aux, false);
		else if (elemento.compareTo(aux.getElemento()) > 0)
			insertarNodo(aux.getDer(), elemento, aux, true);
	}
}
