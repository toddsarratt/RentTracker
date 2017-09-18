package net.toddsarratt.renttracker.datastore.dao;

import java.io.Serializable;

public abstract class GenericFileDAO<T, ID extends Serializable> {

	public void getConnection() {
		/*
		Figure out how to support CRUD operations against file system
		 */
	}
}
