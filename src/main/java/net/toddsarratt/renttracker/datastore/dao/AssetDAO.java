package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.entity.Asset;

public interface AssetDAO extends GenericDAO<Asset, Long> {
	Long create();

	Asset find();

	boolean update();

	boolean delete();
}
