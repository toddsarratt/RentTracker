package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.entity.AssetLease;

public interface AssetLeaseDAO extends GenericDAO<AssetLease, Long> {
	@Override
	Long create(AssetLease newInstance);

	@Override
	AssetLease find(Long id);

	@Override
	void update(AssetLease transientObject);

	@Override
	void delete(AssetLease persistentObject);
}