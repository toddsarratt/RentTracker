package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.StbFileDTO;
import net.toddsarratt.renttracker.entity.Stb;

import java.io.IOException;
import java.nio.file.Path;

public class StbFileDAO extends GenericFileDAO<Stb, Long> implements StbDAO {

	private Path filePath;

	public StbFileDAO(Path filePath) {
		this.filePath = filePath;
	}

	@Override
	public Long create(Stb newInstance) {
		try {
			Long lastId = getIdFromSeqFile(filePath);
			newInstance.setId(lastId + 1L);
			StbFileDTO dto = new StbFileDTO(newInstance);
			save(dto, filePath);
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

	public Path getFilePath() {
		return filePath;
	}

	public void setFilePath(Path filePath) {
		this.filePath = filePath;
	}
}
