package com.mycompany.myapp;

public interface ICollider {
	boolean collidesWith(GameObject otherObject);
	void handleCollision(GameObject otherObject);
}
