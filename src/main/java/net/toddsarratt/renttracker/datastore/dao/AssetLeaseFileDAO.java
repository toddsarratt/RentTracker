package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.AssetLeaseFileDTO;
import net.toddsarratt.renttracker.entity.AssetLease;

import java.io.IOException;
import java.nio.file.Path;

public class AssetLeaseFileDAO extends GenericFileDAO<AssetLease, Long> implements AssetLeaseDAO {

	private Path filePath;

	public AssetLeaseFileDAO(Path filePath) {
		this.filePath = filePath;
	}

	@Override
	public Long create(AssetLease newInstance) {
		try {
			Long lastId = getIdFromSeqFile(filePath);
			newInstance.setId(lastId + 1L);
			AssetLeaseFileDTO dto = new AssetLeaseFileDTO(newInstance);
			save(dto, filePath);
		} catch (IOException ioe) {
			// Log error
			return null;
		}
		return newInstance.getId();
	}

	@Override
	public AssetLease find(Long id) {
		/*
		Get connection
		Search by ID
		Return AssetLease
		 */
		return null;
	}

	@Override
	public void update(AssetLease transientObject) {
		/*
		Get connection
		Find record
		Rewrite record
		 */
	}

	@Override
	public void delete(AssetLease persistentObject) {
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
