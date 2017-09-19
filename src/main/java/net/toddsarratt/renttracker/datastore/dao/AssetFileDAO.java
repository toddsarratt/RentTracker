package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.AssetFileDTO;
import net.toddsarratt.renttracker.entity.Asset;

import java.io.IOException;
import java.nio.file.Path;

public class AssetFileDAO extends GenericFileDAO<Asset, Long> implements AssetDAO {

	private Path filePath;

	public AssetFileDAO(Path filePath) {
		this.filePath = filePath;
	}

	@Override
	public Long create(Asset newInstance) {
		try {
			Long lastId = getIdFromSeqFile(filePath);
			newInstance.setId(lastId + 1L);
			AssetFileDTO dto = new AssetFileDTO(newInstance);
			save(dto, filePath);
		} catch (IOException ioe) {
			// Log error
			return null;
		}
		return newInstance.getId();
	}

	@Override
	public Asset find(Long id) {
		/*
		Get connection
		Search by ID
		Return ASSET
		 */
		return null;
	}

	@Override
	public void update(Asset transientObject) {
		/*
		Get connection
		Find record
		Rewrite record
		 */
	}

	@Override
	public void delete(Asset persistentObject) {
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
