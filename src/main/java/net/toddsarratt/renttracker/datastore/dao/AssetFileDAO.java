package net.toddsarratt.renttracker.datastore.dao;

import net.toddsarratt.renttracker.datastore.dto.AssetFileDTO;
import net.toddsarratt.renttracker.entity.Asset;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class AssetFileDAO extends GenericFileDAO<Asset, Long> implements AssetDAO {

	private static AssetFileDAO instance;
	private static Path filePath;

	/**
	 * Do not allow instantiation of this singleton
	 */
	private AssetFileDAO() {
	}

	public static AssetFileDAO getInstance() {
		if (instance == null) {
			instance = new AssetFileDAO();
		}
		return instance;
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

	public Asset findByTitle(String title) throws IOException {
		Asset foundAsset = null;

		File dir = new File(getFilePath().toString());
		File[] files = dir.listFiles();
		if (files != null) {
			for (File file : files) {
				if (!file.getName().contains("seq")) {
					System.out.println("Checking file: \'" + file.getName() + "\' for Asset title: \'" + title + "\'");
					List<String> fileContents = Files.readAllLines(file.toPath());
					AssetFileDTO dto = AssetFileDTO.fromFileRead(fileContents.get(0));
					if (dto != null) {
						if (dto.getTitle().equals(title)) {
							foundAsset = new Asset();
							foundAsset.setId(dto.getId());
							foundAsset.setTitle(dto.getTitle());
							break;
						}
					}
				}
			}
		}
		// If no Asset is found this will return null
		return foundAsset;
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
