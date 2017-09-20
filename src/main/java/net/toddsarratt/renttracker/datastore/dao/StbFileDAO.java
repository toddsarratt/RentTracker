package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.StbFileDTO;
import net.toddsarratt.renttracker.entity.Stb;

import java.io.IOException;

public class StbFileDAO extends GenericFileDAO<Stb, Long> {

	private static StbFileDAO instance = null;

	/**
	 * Do not allow instantiation of this singleton
	 */
	private StbFileDAO() {
	}

	public static StbFileDAO getInstance() {
		if (instance == null) {
			instance = new StbFileDAO();
		}
		return instance;
	}

	@Override
	public Long create(Stb newInstance) {
		try {
			Long lastId = getIdFromSeqFile(this.getFilePath());
			newInstance.setId(lastId + 1L);
			StbFileDTO dto = new StbFileDTO(newInstance);
			save(dto, this.getFilePath());
		} catch (IOException ioe) {
			// Log error
			return null;
		}
		return newInstance.getId();
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

	public Stb findByName(String name) {
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
}
