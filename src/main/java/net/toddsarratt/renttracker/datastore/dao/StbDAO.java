package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.entity.Stb;

public interface StbDAO extends GenericDAO<Stb, Long> {
	@Override
	default Long create(Stb newInstance) {
		return null;
	}

	@Override
	default Stb find(Long id) {
		return null;
	}

	@Override
	default void update(Stb transientObject) {

	}

	@Override
	default void delete(Stb persistentObject) {

	}
}
