package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.entity.AssetLease;

public interface AssetLeaseDAO extends GenericDAO<AssetLease, Long> {
	Long create();

	AssetLease find();

	boolean update();

	boolean delete();
}
