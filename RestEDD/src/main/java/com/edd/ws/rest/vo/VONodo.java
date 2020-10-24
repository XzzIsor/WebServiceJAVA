package com.edd.ws.rest.vo;

public class VONodo<T> {
	private VONodo izq;   
	private VONodo der;     
	private int  factorEquilibrio;
	private T elemento; 
	public VONodo(T elemento) {
		this(elemento, null, null );
	}
	public VONodo( T elemento, VONodo izq, VONodo der )
	{
		this.elemento  = elemento;
		this.izq  = izq;
		this.der = der;
		factorEquilibrio  = 0;
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
	public int getFactorEquilibrio() {
		return factorEquilibrio;
	}
	public void setFactorEquilibrio(int factorEquilibrio) {
		this.factorEquilibrio = factorEquilibrio;
	}
	public T getElemento() {
		return elemento;
	}
	public void setElemento(T elemento) {
		this.elemento = elemento;
	}
}
