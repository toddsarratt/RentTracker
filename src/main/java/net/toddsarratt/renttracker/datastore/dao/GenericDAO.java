package net.toddsarratt.renttracker.datastore.dao;

import java.io.Serializable;

/**
 * Generic DAO with CRUD operations.
 * Ref: https://www.ibm.com/developerworks/library/j-genericdao/index.html
 *
 * @param <T>  persistent object
 * @param <PK> primary key
 */
public interface GenericDAO<T, PK extends Serializable> {
	/* CRUD operations */

	/**
	 * Create operation
	 */
	PK create(T newInstance);

	/**
	 * Read operation
	 */
	T find(PK id);

	/**
	 * Update operation
	 */
	void update(T transientObject);

	/**
	 * Delete operation
	 */
	void delete(T persistentObject);
}
