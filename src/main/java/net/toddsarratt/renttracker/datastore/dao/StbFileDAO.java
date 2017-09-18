package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.entity.Stb;

public class StbFileDAO implements StbDAO {
	@Override
	public Long create(Stb newInstance) {
		/*
		Get connection
		Get next ID
		Write to STB table
		Write new ID to counter
		Return ID
		 */
		return null;
	}

	@Override
	public Stb find(Long id) {
		/*
		Get connection
		Search by ID
		Return STB
		 */
		return null;
	}

	@Override
	public void update(Stb transientObject) {
		/*
		Get connection
		Find record
		Rewrite record
		 */
	}

	@Override
	public void delete(Stb persistentObject) {
		/*
		Get connection
		Find record
		Delete record
		 */
	}

	private void getConnection() {
		/*
		Provide access to filesystem
		 */
	}
}
