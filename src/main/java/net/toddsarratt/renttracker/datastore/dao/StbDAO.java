package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.entity.Stb;

public interface StbDAO extends GenericDAO<Stb, Long> {
	@Override
	Long create(Stb newInstance);

	@Override
	Stb find(Long id);

	@Override
	void update(Stb transientObject);

	@Override
	void delete(Stb persistentObject);
}