package com.mycompany.myapp;

public interface IIterator {
	public boolean hasNext();
	public GameObject getNext();
	public int length();
	public GameObject get(int index);

}
