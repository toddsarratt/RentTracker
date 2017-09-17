package net.toddsarratt.renttracker.datastore.dao;

abstract class DAOFactory {

	public StbDAO getStbDAO() {
		return new StbDAO();
	}
}