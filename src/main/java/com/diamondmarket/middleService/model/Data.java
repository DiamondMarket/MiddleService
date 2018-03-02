package com.diamondmarket.middleService.model;

public class Data<T> {

	private T t;
	
	public Data(T t) {
		super();
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "Data [t=" + t + "]";
	}

}