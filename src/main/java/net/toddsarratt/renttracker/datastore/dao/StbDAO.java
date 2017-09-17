package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.entity.Stb;

public interface StbDAO extends GenericDAO<Stb, Long> {
	Long create();

	Stb find();

	boolean update();

	boolean delete();
}
