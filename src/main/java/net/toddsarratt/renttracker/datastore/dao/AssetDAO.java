package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.entity.Asset;

public interface AssetDAO extends GenericDAO<Asset, Long> {
	@Override
	Long create(Asset newInstance);

	@Override
	Asset find(Long id);

	@Override
	void update(Asset transientObject);

	@Override
	void delete(Asset persistentObject);
}